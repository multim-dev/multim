package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Status
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable


@Serializable
data class StatusesPostRequest(
    val status: String,
    val mediaIds: List<String> = listOf(),
    val poll: Poll? = null,
    val inReplyToId: String? = null,
    val sensitive: Boolean? = null,
    val visibility: Visibility? = null,
    val language: String? = null,
    val scheduledAt: Instant? = null
) {
    @Serializable
    data class Poll(
        val multiple: Boolean,
        val hideTotals: Boolean,
        val expiresIn: Int,
        val options: List<String>
    )

    enum class Visibility
}

//todo scheduledStatusに対応する
typealias StatusesPostResponse = Status
