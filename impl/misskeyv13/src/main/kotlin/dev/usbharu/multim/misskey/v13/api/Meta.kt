package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.MetaRequest
import dev.usbharu.multim.misskey.v13.model.MetaResponse

@Suppress("MemberNameEqualsClassName")
class Meta(val client: MisskeyApiClient) {
    suspend fun meta(metaRequest: MetaRequest): MultiMResult<MetaResponse> {
        return client.post<MetaRequest, MetaResponse>(metaRequest, "api/meta")
            .mapMultiMError()
    }
}
