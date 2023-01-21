package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesPutIdRequest(
    val status: String,
    val spoilerText: String,
    val sensitive: Boolean,
    val language: String,
    val mediaIds: List<String>,
    val poll: Poll
) {
    @Serializable
    data class Poll(
        val options: List<String>,
        val expiresIn: List<String>,
        val multiple: Boolean,
        val hideTotals: Boolean
    )
}

typealias StatusesPutIdResponse = Status
