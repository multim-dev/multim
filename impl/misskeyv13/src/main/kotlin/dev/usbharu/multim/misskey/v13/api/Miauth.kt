package dev.usbharu.multim.misskey.v13.api

import com.github.michaelbull.result.map
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.MiauthCheckRequest
import dev.usbharu.multim.misskey.v13.model.MiauthCheckResponse
import io.ktor.client.statement.*
import java.util.*


class Miauth(val client: MisskeyApiClient) {

    suspend fun auth(): MultiMResult<String> {

        val body = client.get(client.baseUrl + "/miauth/" + UUID.randomUUID() + "/") {
            url {

                parameters.append("name", "multim-test")
                parameters.append("permission", "read:account,write:notes")
            }
        }.map { it.bodyAsText() }
        return body.mapMultiMError()
    }

    suspend fun check(params: MiauthCheckRequest): MultiMResult<MiauthCheckResponse> {
        return client.postEmpty<MiauthCheckResponse>("", "/api/miauth/${params.sessionId}/check")
            .mapMultiMError()
    }

}
