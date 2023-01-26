package dev.usbharu.multim.model

import kotlinx.datetime.Instant

abstract class Poll(
    val options: List<Option>,
    val multiple:Boolean,
    val expiredAt: Instant,
    val votersCount:Int?,
    val voted:Boolean
)

abstract class PollForPost(
    val options: List<OptionForPost>
    val multiple: Boolean,
    val expiredAt: Instant,
)
