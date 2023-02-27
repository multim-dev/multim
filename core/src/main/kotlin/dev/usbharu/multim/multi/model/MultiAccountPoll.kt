package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Poll
import dev.usbharu.multim.model.PollForPost
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountPoll(override val innerData: Poll, override val hashCode: Int) :
    MultiAccountData<Poll>,
    Poll(
        innerData.options,
        innerData.multiple,
        innerData.expiredAt,
        innerData.votersCount,
        innerData.voted
    )

class MultiAccountPollForPost(override val innerData: PollForPost, override val hashCode: Int) :
    MultiAccountData<PollForPost>,
    PollForPost(innerData.options, innerData.multiple, innerData.expiredAt)
