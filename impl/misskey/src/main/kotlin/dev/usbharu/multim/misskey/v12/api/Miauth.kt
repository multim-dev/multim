package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.MiauthCheckRequest
import dev.usbharu.multim.misskey.v12.model.MiauthCheckResponse
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
        val baseUrl: String = System.getProperty("multim_misskey_instance")
    }
}
