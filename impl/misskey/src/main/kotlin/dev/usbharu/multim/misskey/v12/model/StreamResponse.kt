package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MessagingMessage
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.Notification
import dev.usbharu.multim.misskey.v12.model.components.User
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class StreamResponse {
    @SerialName("channel")
    @Serializable
    data class ChannelResponse(
        val body: ChannelBody
    ) : StreamResponse() {
        @Serializable
        sealed class ChannelBody {

            @SerialName("note")
            @Serializable
            data class NoteBody(
                val id: String,
                val body: Note
            ) : ChannelBody()

            @SerialName("notification")
            @Serializable
            data class NotificationBody(
                val id: String,
                val body: Notification
            ) : ChannelBody()

            @SerialName("mention")
            @Serializable
            data class MentionBody(
                val id: String,
                val body: Note
            ) : ChannelBody()

            @SerialName("reply")
            @Serializable
            data class ReplyBody(
                val id: String,
                val body: Note
            ) : ChannelBody()

            @SerialName("renote")
            @Serializable
            data class RenoteBody(
                val id: String,
                val body: Note
            ) : ChannelBody()

            @SerialName("follow")
            @Serializable
            data class FollowBody(
                val id: String,
                val body: User
            ) : ChannelBody()

            @SerialName("followed")
            @Serializable
            data class FollowedBody(
                val id: String,
                val body: User
            ) : ChannelBody()

            @SerialName("unfollow")
            @Serializable
            data class UnfollowBody(
                val id: String,
                val body: User
            ) : ChannelBody()

            @SerialName("messagingMessage")
            @Serializable
            data class MessagingMessageBody(
                val id: String,
                val body: MessagingMessage
            ) : ChannelBody()

            @SerialName("readAllNotifications")
            @Serializable
            data class ReadAllNotificationsBody(
                val id: String
            ) : ChannelBody()

            @SerialName("unreadNotification")
            @Serializable
            data class UnreadNotification(
                val id: String
            ) : ChannelBody()

            @SerialName("unreadMention")
            @Serializable
            data class UnreadMentionBody(
                val id: String
            ) : ChannelBody()

            @SerialName("readAllUnreadMentions")
            @Serializable
            data class ReadAllUnreadMentionsBody(
                val id: String
            ) : ChannelBody()

            @SerialName("unreadSpecifiedNote")
            @Serializable
            data class UnreadSpecifiedNoteBody(
                val id: String
            ) : ChannelBody()

            @SerialName("readAllUnreadSpecifiedNotes")
            @Serializable
            data class ReadAllUnreadSpecifiedNotesBody(
                val id: String
            ) : ChannelBody()

            @SerialName("unreadMessagingMessage")
            @Serializable
            data class UnreadMessagingMessageBody(
                val id: String
            ) : ChannelBody()

            @SerialName("readAllMessagingMessage")
            @Serializable
            data class ReadAllMessagingMessageBody(
                val id: String
            ) : ChannelBody()
        }
    }

    @SerialName("noteUpdated")
    @Serializable
    data class NoteUpdatedResponse(
        val body: NoteUpdatedBody
    ) {
        @Serializable
        sealed class NoteUpdatedBody {
            @SerialName("reacted")
            @Serializable
            data class Reacted(
                val id: String,
                val body: ReactedBody
            ) : NoteUpdatedBody() {
                @Serializable
                data class ReactedBody(
                    val reaction: String,
                    val userId: String
                )
            }

            @SerialName("pollVoted")
            @Serializable
            data class PollVoted(
                val id: String,
                val body: PollVotedBody
            ) : NoteUpdatedBody() {
                @Serializable
                data class PollVotedBody(
                    val choice: Int,
                    val userId: String
                )
            }

            @SerialName("deleted")
            @Serializable
            data class Deleted(
                val deletedAt: Instant
            )
        }

    }
}
