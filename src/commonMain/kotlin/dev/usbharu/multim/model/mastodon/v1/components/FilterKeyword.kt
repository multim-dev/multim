package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.serialization.Serializable

@Serializable
data class FilterKeyword(
    val id: String,
    val keyword: String,
    val wholeWord: Boolean
)
