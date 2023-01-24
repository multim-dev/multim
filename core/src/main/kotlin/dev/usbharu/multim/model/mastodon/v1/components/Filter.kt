package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val id: String,
    val title: String,
    val context: Context,
    val expiresAt: LocalDateTime?,
    val filterAction: FilterAction,
    val keywords: List<FilterKeyword>,
    val statuses: List<FilterStatus>
) {
    enum class Context

    enum class FilterAction
}
