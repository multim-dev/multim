package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.StatusForPost
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountStatus(override val innerData: Status, override val hashCode: Int) :
    MultiAccountData<Status>,
    Status(
        innerData.id,
        innerData.account,
        innerData.content,
        innerData.reactions,
        innerData.myReactions,
        innerData.repostCount,
        innerData.repliesCount,
        innerData.reposted,
        innerData.emojis,
        innerData.tags,
        innerData.language,
        innerData.poll,
        innerData.files,
        innerData.createdAt
    )

class MultiAccountStatusForPost(override val innerData: StatusForPost, override val hashCode: Int) :
    MultiAccountData<StatusForPost>,
    StatusForPost(
        innerData.account,
        innerData.content,
        innerData.poll,
        innerData.files
    )
