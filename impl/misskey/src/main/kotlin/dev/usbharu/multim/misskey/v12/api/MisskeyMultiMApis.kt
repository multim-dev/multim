package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.factory.MultiMApis

class MisskeyMultiMApis(statusApi: StatusApi, accountApi: AccountApi,timelineApi: TimelineApi,emojiApi: EmojiApi) :
    MultiMApis(statusApi, accountApi,timelineApi,emojiApi) {
}
