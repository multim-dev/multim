package dev.usbharu.multim.api.common

import dev.usbharu.multim.model.misskey.v12.components.Note
import dev.usbharu.multim.model.misskey.v12.components.UserLite
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object TestUtil {
    fun createMockHttpClient(
        content: String = "",
        contentType: String = "application/json",
        statusCode: HttpStatusCode = HttpStatusCode.OK
    ): HttpClient {
        return HttpClient(MockEngine {
            respond(
                content = content,
                status = statusCode,
                headers = headersOf(HttpHeaders.ContentType, contentType)
            )
        }) {
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
}
