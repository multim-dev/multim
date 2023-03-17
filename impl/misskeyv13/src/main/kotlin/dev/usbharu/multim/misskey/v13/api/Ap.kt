package dev.usbharu.multim.misskey.v13.api

import com.github.michaelbull.result.mapError
import dev.usbharu.multim.error.*
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.ApShowRequest
import dev.usbharu.multim.misskey.v13.model.ApShowResponse
import io.ktor.client.plugins.*

class Ap(val client: MisskeyApiClient) {
    suspend fun show(apShowRequest: ApShowRequest): MultiMResult<ApShowResponse> {
        return client.post<ApShowRequest, ApShowResponse>(apShowRequest, "api/ap/show").mapError {
            val multiMError: MultiMError = when (it) {
                is HttpClientServerError -> MultiMHttpError(it.throwable as ServerResponseException)
                is HttpClientClientError -> MultiMHttpError(it.throwable as ClientRequestException)
                else -> MultiMError("Api Client Error", it.throwable, ErrorType.UNKNOWN)
            }
            multiMError
        }
    }
}
