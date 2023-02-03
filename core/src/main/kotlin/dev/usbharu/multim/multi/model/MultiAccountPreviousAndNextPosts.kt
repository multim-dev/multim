package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.PreviousAndNextPosts
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountPreviousAndNextPosts(
    override val innerData: PreviousAndNextPosts,
    override val hashCode: Int
) : MultiAccountData<PreviousAndNextPosts>, PreviousAndNextPosts(innerData.next, innerData.previous)
