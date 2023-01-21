package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.model.misskey.v12.ApShowRequest
import dev.usbharu.multim.model.misskey.v12.ApShowResponse

class Ap(val client: MisskeyApiClient) {
    suspend fun show(apShowRequest: ApShowRequest): ApShowResponse {
        return client.post(apShowRequest, "/ap/show")
    }
}
