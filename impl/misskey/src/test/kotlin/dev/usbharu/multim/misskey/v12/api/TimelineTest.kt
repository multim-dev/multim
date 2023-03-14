package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.NotesCreateRequest
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest.Body
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest.Body.Channel.GLOBAL_TIMELINE
import dev.usbharu.multim.misskey.v12.model.StreamRequest.DisconnectRequest
import dev.usbharu.multim.misskey.v12.model.StreamResponse.ChannelResponse.ChannelBody.NoteBody
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.model.SingleTokenAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
@Suppress("EmptyFunctionBlock")
class TimelineTestE2E {


    val client = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        MultiM.httpClientWithJson.config {}
    )
    val timeline = Timeline(client)
    val notes = Notes(client)

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Test
    fun connectChannelTest() = runBlocking<Unit> {

        val streamNotes = mutableListOf<Note>()
        val createdNotes = mutableListOf<Note>()
        client.streaming.connect()
        delay(1000)
        val uuid = UUID.randomUUID().toString()
        timeline.connectChannel(ConnectRequest(Body(uuid, GLOBAL_TIMELINE))) { channelBody ->
            if (channelBody is NoteBody) {
                if (channelBody.body.tags?.contains(uuid) == true) {
                    streamNotes.add(channelBody.body)
                }
            }
        }
        delay(1000)
        repeat(10) {
            notes.create(NotesCreateRequest(text = "このノートはMultiMのテストで作成され、Streaming APIのテストで使用されます。#$uuid "))
                .failOnError().createdNote.let { it1 ->
                    createdNotes.add(
                        it1
                    )
                }
        }
        delay(2000)
        timeline.disconnectChannel(DisconnectRequest(DisconnectRequest.Body(uuid)))
        assertEquals(
            createdNotes.sortedBy { note -> note.createdAt },
            streamNotes.sortedBy { note -> note.createdAt })

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
