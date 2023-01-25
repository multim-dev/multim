package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Account
import kotlinx.serialization.Serializable

@Serializable
data class StatusesRebloggedByGetIdRequest(
    val id: String
)
//todo pagination apiに対応する
typealias StatusesRebloggedByGetIdResponse = List<Account>
