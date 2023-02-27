package dev.usbharu.multim.misskey.v12.api

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.MiauthCheckRequest
import dev.usbharu.multim.misskey.v12.model.MiauthCheckResponse
import io.ktor.client.statement.*
import java.util.*


class Miauth(val client: MisskeyApiClient) {

    suspend fun auth(): Result<String, MultiMError> {

        val body = client.get(client.baseUrl + "/miauth/" + UUID.randomUUID() + "/") {
            url {

                parameters.append("name", "multim-test")
                parameters.append("permission", "read:account,write:notes")
            }
        }.map { it.bodyAsText() }
        return body.mapMultiMError()
    }

    suspend fun check(params: MiauthCheckRequest): Result<MiauthCheckResponse, MultiMError> {
        return client.postEmpty<MiauthCheckResponse>("", "/api/miauth/${params.sessionId}/check")
            .mapMultiMError()
    }

}
