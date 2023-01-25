package dev.usbharu.multim.mastodon.model.components

import kotlinx.serialization.Serializable

@Serializable
class FilterResult(
    val filter: Filter,
    val keywordMatches: List<String>?,
    val statusMatches: String?
)
