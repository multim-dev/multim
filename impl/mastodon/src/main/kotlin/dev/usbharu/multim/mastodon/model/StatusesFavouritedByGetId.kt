package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Account
import kotlinx.serialization.Serializable

@Serializable
data class StatusesFavouritedByGetIdRequest(
    val id: String
)

typealias StatusesFavouritedByGetIdResponse = List<Account>
