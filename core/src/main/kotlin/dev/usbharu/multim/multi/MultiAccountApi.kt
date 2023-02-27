package dev.usbharu.multim.multi

import dev.usbharu.multim.api.*
import dev.usbharu.multim.factory.MultiMApis

class MultiAccountApi(statusApi: StatusApi, accountApi: AccountApi, timelineApi: TimelineApi,emojiApi: EmojiApi,i:IApi) :
    MultiMApis(statusApi, accountApi, timelineApi,emojiApi,i) {
    constructor(apiBase: MultiAccountApiBase) : this(
        MultiAccountStatusApi(apiBase),
        MultiAccountAccountApi(apiBase),
        MultiAccountTimelineApi(apiBase),
        MultiAccountEmojiApi(apiBase),
        MultiAccountIApi(apiBase)
    )
}
