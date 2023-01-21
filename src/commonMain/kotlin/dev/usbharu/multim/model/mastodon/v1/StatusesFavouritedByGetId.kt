package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Account
import kotlinx.serialization.Serializable

@Serializable
data class StatusesFavouritedByGetIdRequest(
    val id: String
)

typealias StatusesFavouritedByGetIdResponse = List<Account>
