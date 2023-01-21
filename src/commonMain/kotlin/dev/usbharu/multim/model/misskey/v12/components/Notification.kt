package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val id: String,
    val createdAt: LocalDateTime,
    val isRead: Boolean,
    val type: NotificationType,
    val userLite: UserLite? = null,
    val userId: String,
    val note: Note? = null,
    val reaction: String? = null,
    val choice: Int? = null,
    val invitation: Invitation? = null,
    val body: String? = null,
    val header: String? = null,
    val icon: String? = null
)

@Serializable
class Invitation


enum class NotificationType(val string: String) {
    FOLLOW("follow"),
    MENTION("mention"),
    REPLY("reply"),
    RENOTE("renote"),
    QUOTE("quote"),
    REACTION("reaction"),
    POLL_VOTE("pollVote"),
    POLL_ENDED("pollEnded"),
    RECEIVE_FOLLOW_REQUEST("receiveFollowRequest"),
    FOLLOW_REQUEST_ACCEPTED("followRequestAccepted"),
    GROUP_INVITED("groupInvited"),
    APP("app")
}
