package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.api.PlatformApis
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient

class MisskeyApis(misskeyApiClient: MisskeyApiClient) : PlatformApis(misskeyApiClient) {
    val notes = Notes(misskeyApiClient)
    val ap = Ap(misskeyApiClient)
    val miauth = Miauth(misskeyApiClient)
    val timeline = Timeline(misskeyApiClient)
    val following = Following(misskeyApiClient)
    val users = Users(misskeyApiClient)
    val meta = Meta(misskeyApiClient)
}
