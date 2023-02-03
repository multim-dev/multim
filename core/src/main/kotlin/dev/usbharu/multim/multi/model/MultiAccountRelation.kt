package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Relation
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountRelation(override val innerData: Relation, override val hashCode: Int) :
    MultiAccountData<Relation>,
    Relation(
        innerData.myself,
        innerData.other,
        innerData.following,
        innerData.follower,
        innerData.mute,
        innerData.block
    ) {
}
