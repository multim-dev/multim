package dev.usbharu.multim.api.common.impl.misskey

import dev.usbharu.multim.api.common.PlatformApis
import dev.usbharu.multim.api.misskey.v12.*

class MisskeyApis(misskeyApiClient: MisskeyApiClient) : PlatformApis(misskeyApiClient) {
    val notes = Notes(misskeyApiClient)
    val ap = Ap(misskeyApiClient)
    val miauth = Miauth(misskeyApiClient)
    val timeline = Timeline(misskeyApiClient)
    val following = Following(misskeyApiClient)
    val users = Users(misskeyApiClient)
}
