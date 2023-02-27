package dev.usbharu.multim.factory

import dev.usbharu.multim.api.*

abstract class MultiMApis(
    var statusApi: StatusApi,
    var accountApi: AccountApi,
    var timelineApi: TimelineApi,
    var emojiApi: EmojiApi,
    var i: IApi
)
