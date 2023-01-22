package dev.usbharu.multim.api.common

import dev.usbharu.multim.model.misskey.v12.components.Note
import dev.usbharu.multim.model.misskey.v12.components.UserLite
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlin.test.fail

object TestUtil {
    val json = Json { ignoreUnknownKeys = true;isLenient = true }
    fun createMockHttpClient(
        content: String = "",
        contentType: String = "application/json",
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        respond: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData = {
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

    fun createFakeNote(id: String, userId: String, username: String, text: String?): Note {
        return Note(
            id = id,
            userId = userId,
            user = UserLite(id = userId, name = username),
            text = text
        )
    }

    fun createFakeNoteToString(id: String, userId: String, username: String, text: String?): String {
        return json.encodeToString(createFakeNote(id, userId, username, text))
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
            }
        }
    }
}
