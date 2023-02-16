package dev.usbharu.multim.factory

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.api.TimelineApi

abstract class MultiMApis(
    var statusApi: StatusApi,
    var accountApi: AccountApi,
    var timelineApi: TimelineApi,
    var emojiApi: EmojiApi
)
