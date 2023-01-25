package dev.usbharu.multim.mastodon.model.components

import kotlinx.serialization.Serializable

@Serializable
data class FilterStatus(val id: String, val statusId: String)
