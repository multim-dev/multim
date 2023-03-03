package dev.usbharu.multim

import com.github.michaelbull.result.*
import dev.usbharu.multim.error.MultiMResult
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.fail


object TestUtil {
    val json = Json { ignoreUnknownKeys = true;isLenient = true }
    fun createMockHttpClient(
        content: String = "",
        contentType: String = "application/json",
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        checkAuth: Boolean = true,
        respond: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData = {
            if (checkAuth) {
                if ("i" in json.parseToJsonElement(
                        it.body.toByteArray().decodeToString()
                    ).jsonObject
                ) {

                } else {
                    fail("No auth")
                }
            }
            respond(
                content = content,
                status = statusCode,
                headers = headersOf(HttpHeaders.ContentType, contentType)
            )
        }
    ): HttpClient {
        return HttpClient(MockEngine(respond)) {
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    fun checkAuth(
        respond: String,
        status: HttpStatusCode = HttpStatusCode.OK,
        headers: Headers = headersOf()
    ): suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData {
        return { httpRequestData: HttpRequestData ->
            val decodeToString = httpRequestData.body.toByteArray().decodeToString()
            println(decodeToString)
            if ("i" in json.parseToJsonElement(decodeToString).jsonObject) {
                respond(respond, status, headers)
            } else {
                fail("Not authed")
//                respondBadRequest()
            }
        }
    }


    fun assertIsOk(result: Result<*, *>) {
        assertInstanceOf(Ok::class.java, result, "resultの型がOkではない")
    }

    inline fun <T, reified R : T> assertIsErr(result: Result<*, T>) {
        assertInstanceOf(Err::class.java, result, "resultの型がErrではない")
        assertInstanceOf(R::class.java, result.getError(), "Errorの型が違う")
    }

    fun <T> MultiMResult<T>.failOnError(): T {
        val get = this.get()
        if (get != null) {
            return get
        }
        val error = this.getError()!!
        fail("Return Error ${error.message}", error.throwable)
    }
}
