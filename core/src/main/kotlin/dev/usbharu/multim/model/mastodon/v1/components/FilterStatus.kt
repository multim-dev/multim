package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.serialization.Serializable

@Serializable
data class FilterStatus(val id: String, val statusId: String)
