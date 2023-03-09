import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.UserLite
import dev.usbharu.multim.model.SingleTokenAuth
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.mockk.InternalPlatformDsl.toStr
import kotlinx.datetime.Clock
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.assertj.core.api.Fail
import org.junit.jupiter.api.Assertions

object MisskeyTestUtil {

    val json = Json { ignoreUnknownKeys = true;isLenient = true }

    val baseUrl = "https://localhsot/"

    internal fun apiClient(httpClient: HttpClient): MisskeyApiClient {
        return MisskeyApiClient(SingleTokenAuth("cdgj2h71"), baseUrl, httpClient)
    }

    inline fun <reified T> createMockHttpClient(
        content: T,
        checkAuth: Boolean,
        url: String? = null,
        serializer:SerializationStrategy<T>,
        statusCode: HttpStatusCode = HttpStatusCode.OK,
    ): HttpClient {
        return HttpClient(MockEngine {
            if (!checkAuth || "i" in json.parseToJsonElement(it.body.toByteArray().decodeToString()).jsonObject) {
                //ok
            } else {
                Fail.fail("No auth.")
            }
            if (url == null || it.url.toStr() == url) {
                //ok
            }else {
                Fail.fail("Illegal URL expected: $url actual: ${it.url.toStr()}")
            }

            respond(
                content = json.encodeToString(serializer,content),
                status = statusCode,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

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
                    Fail.fail("No auth")
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

            install(ContentNegotiation) {
                json(json)
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
            if ("i" in json.parseToJsonElement(decodeToString).jsonObject) {
                respond(respond, status, headers)
            } else {
                Fail.fail("Not authed")
                respondBadRequest()
            }
        }
    }

    fun createFakeNote(id: String, userId: String, username: String, text: String?): Note {
        return Note(
            id = id,
            userId = userId,
            user = UserLite(id = userId, name = username, username = username),
            text = text,
            createdAt = Clock.System.now()
        )
    }

    fun createFakeNoteToString(
        id: String,
        userId: String,
        username: String,
        text: String?
    ): String {
        return json.encodeToString(createFakeNote(id, userId, username, text))
    }

    fun assertIsOk(result: Result<*, *>) {
        Assertions.assertInstanceOf(Ok::class.java, result, "resultの型がOkではない")
    }

    inline fun <T, reified R : T> assertIsErr(result: Result<*, T>) {
        Assertions.assertInstanceOf(Err::class.java, result, "resultの型がErrではない")
        Assertions.assertInstanceOf(R::class.java, result.getError(), "Errorの型が違う")
    }
}
