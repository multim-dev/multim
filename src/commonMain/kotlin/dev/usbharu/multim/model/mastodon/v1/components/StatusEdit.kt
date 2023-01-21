package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class StatusEdit(
    val content: String,
    val spoilerText: String,
    val sensitive: Boolean,
    val createdAt: LocalDateTime,
    val account: Account,
    val poll: Poll?,
    val mediaAttachments: List<MediaAttachment>,
    val emojis: List<CustomEmoji>
) {
    @Serializable
    data class Poll(
        val options: List<Option>,
    ) {
        @Serializable
        data class Option(
            val title: String
        )
    }
}
