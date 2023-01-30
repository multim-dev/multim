package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.Callback
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.common.api.json
import dev.usbharu.multim.misskey.v12.model.StreamRequest
import dev.usbharu.multim.misskey.v12.model.StreamResponse
import dev.usbharu.multim.misskey.v12.model.StreamResponse.ChannelResponse.ChannelBody.*
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString

class Timeline(val client: MisskeyApiClient) {
    fun connectChannel(channelConnectRequest: StreamRequest.ConnectRequest) {
        client.Streaming().send(channelConnectRequest)
    }

    fun connectChannel(
        channelConnectRequest: StreamRequest.ConnectRequest,
        callBack: (StreamResponse.ChannelResponse.ChannelBody) -> Unit
    ) {
        val id = channelConnectRequest.body.id
        client.Streaming().send(channelConnectRequest)
        val callback: Callback = {
            if (it is Frame.Text) {
                val response: StreamResponse.ChannelResponse = json.decodeFromString(it.readText())
                when (val body = response.body) {
                    is NoteBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is FollowBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is FollowedBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is MentionBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is MessagingMessageBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is NotificationBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is ReadAllMessagingMessageBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is ReadAllNotificationsBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is ReadAllUnreadMentionsBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is ReadAllUnreadSpecifiedNotesBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is RenoteBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is ReplyBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is UnfollowBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is UnreadMentionBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is UnreadMessagingMessageBody -> if (id == body.id) {
                        callBack(body)
                    }

                    is UnreadNotification -> if (id == body.id) {
                        callBack(body)
                    }

                    is UnreadSpecifiedNoteBody -> if (id == body.id) {
                        callBack(body)
                    }
                }
            }
        }
        client.Streaming().listen(id, callback)
    }


    fun sendToChannel(channelRequest: StreamRequest.ChannelRequest) {
        client.Streaming().send(channelRequest)
    }

    fun disconnectChannel(channelDisconnectRequest: StreamRequest.DisconnectRequest){
        client.Streaming().send(channelDisconnectRequest)
        client.Streaming().unlisted(channelDisconnectRequest.body.id)
    }
}
