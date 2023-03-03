package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.api.*
import dev.usbharu.multim.factory.MultiMApis

class MisskeyMultiMApis(
    statusApi: StatusApi,
    accountApi: AccountApi,
    timelineApi: TimelineApi,
    emojiApi: EmojiApi,
    iApi: IApi
) :
    MultiMApis(statusApi, accountApi, timelineApi, emojiApi, iApi)
