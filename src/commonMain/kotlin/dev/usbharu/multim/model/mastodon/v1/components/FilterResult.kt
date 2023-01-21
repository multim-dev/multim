package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.serialization.Serializable

@Serializable
class FilterResult(
    val filter: Filter,
    val keywordMatches: List<String>?,
    val statusMatches: String?
)
