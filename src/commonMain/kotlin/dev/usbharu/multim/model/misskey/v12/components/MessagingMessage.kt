package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class MessagingMessage(
    val id: String,
    val createdAt: Instant,
    val userId: String,
    val text: String?,
    val fileId: String,
    val file: File? = null,
    val recipientId: String?,
    val recipient: UserLite? = null,
    val groupId: String?,
    val group: UserGroup? = null,
    val isRead: Boolean?,
    val reads: List<String>?
)
