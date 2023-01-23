package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.model.misskey.v12.MiauthCheckRequest
import dev.usbharu.multim.model.misskey.v12.MiauthCheckResponse
import io.ktor.client.statement.*
import java.util.*


class Miauth(val client: MisskeyApiClient) {

    suspend fun auth(): String {

        val body = client.get(baseUrl + "/miauth/" + UUID.randomUUID() + "/") {
            url {

                parameters.append("name", "multim-test")
                parameters.append("permission", "read:account,write:notes")
            }
        }.bodyAsText()
        return body
    }

    suspend fun check(params: MiauthCheckRequest): MiauthCheckResponse {
        return client.post("", "/api/miauth/${params.sessionId}/check")
    }

    companion object {
        val baseUrl: String = "https://misskey.usbharu.dev"
    }
}
