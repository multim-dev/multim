package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.IIRequest
import dev.usbharu.multim.misskey.v13.model.IIResponse

@Suppress("MemberNameEqualsClassName")
class I(val misskeyApiClient: MisskeyApiClient) {
    suspend fun i(iiRequest: IIRequest = IIRequest()): MultiMResult<IIResponse> {
        return misskeyApiClient.post<IIRequest, IIResponse>(iiRequest, "api/i").mapMultiMError()
    }
}
