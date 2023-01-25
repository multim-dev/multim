package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Account
import kotlinx.serialization.Serializable

@Serializable
data class StatusesRebloggedByGetIdRequest(
    val id: String
)
//todo pagination apiに対応する
typealias StatusesRebloggedByGetIdResponse = List<Account>
