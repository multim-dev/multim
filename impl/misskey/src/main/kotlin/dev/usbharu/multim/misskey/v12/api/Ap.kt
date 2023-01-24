package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.ApShowRequest
import dev.usbharu.multim.misskey.v12.model.ApShowResponse

class Ap(val client: MisskeyApiClient) {
    suspend fun show(apShowRequest: ApShowRequest): ApShowResponse {
        return client.post(apShowRequest, "api/ap/show")
    }
}
