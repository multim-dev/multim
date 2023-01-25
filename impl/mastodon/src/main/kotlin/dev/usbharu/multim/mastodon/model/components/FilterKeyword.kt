package dev.usbharu.multim.mastodon.model.components

import kotlinx.serialization.Serializable

@Serializable
data class FilterKeyword(
    val id: String,
    val keyword: String,
    val wholeWord: Boolean
)
