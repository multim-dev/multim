package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.api.createHttpClient
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.NotesCreateRequest
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest.Body
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest.Body.Channel.GLOBAL_TIMELINE
import dev.usbharu.multim.misskey.v12.model.StreamRequest.DisconnectRequest
import dev.usbharu.multim.misskey.v12.model.StreamResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.*

class TimelineTestE2E {


    val client = MisskeyApiClient(
        System.getProperty("multim_misskey_token"),
        System.getProperty("multim_misskey_instance"),
        createHttpClient()
    )
    val timeline = Timeline(client)
    val notes = Notes(client)

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Test
    fun connectChannelTest() = runBlocking<Unit> {
        client.streaming.connect()
        delay(1000)
        val uuid = UUID.randomUUID().toString()
        timeline.connectChannel(ConnectRequest(Body(uuid, GLOBAL_TIMELINE))) { channelBody ->
            when (channelBody) {
                is StreamResponse.ChannelResponse.ChannelBody.FollowBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.FollowedBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.MentionBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.MessagingMessageBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.NoteBody -> println(channelBody.body.text)
                is StreamResponse.ChannelResponse.ChannelBody.NotificationBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.ReadAllMessagingMessageBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.ReadAllNotificationsBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.ReadAllUnreadMentionsBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.ReadAllUnreadSpecifiedNotesBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.RenoteBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.ReplyBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.UnfollowBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.UnreadMentionBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.UnreadMessagingMessageBody -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.UnreadNotification -> TODO()
                is StreamResponse.ChannelResponse.ChannelBody.UnreadSpecifiedNoteBody -> TODO()
            }
        }
        delay(1000)
        repeat(10) {
            notes.create(NotesCreateRequest(text = "このノートはMultiMのテストで作成され、Streaming APIのテストで使用されます。"))
        }
        delay(1000)
        timeline.disconnectChannel(DisconnectRequest(DisconnectRequest.Body(uuid)))
    }


    @Test
    fun connectChannelCoroutineTest() = runBlocking {
        client.streaming.connect()
        delay(1000)
        timeline.connectChannel(ConnectRequest(Body("aaa", GLOBAL_TIMELINE)))
        delay(1000)
        timeline.disconnectChannel(DisconnectRequest(DisconnectRequest.Body("aaa")))
    }

    @Test
    fun sendToChannelTest() {
    }

    @Test
    fun disconnectChannelTest() {
    }

    @Test
    fun getClientTest() {
    }
}
