package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountProfile(override val innerData: Profile, override val hashCode: Int) :
    MultiAccountData<Profile>,
    Profile(
        innerData.account,
        innerData.isBot,
        innerData.profileContent,
        innerData.followingCount,
        innerData.followersCount,
        innerData.fields
    ) {
    constructor(innerData: Pair<Profile, Int>) : this(innerData.first,innerData.second)
}
