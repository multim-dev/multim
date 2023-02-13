package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.MultiM.json
import dev.usbharu.multim.misskey.v12.common.api.Callback
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.StreamRequest
import dev.usbharu.multim.misskey.v12.model.StreamResponse
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString

class Timeline(val client: MisskeyApiClient) {
    fun connectChannel(channelConnectRequest: StreamRequest.ConnectRequest) {
        client.Streaming().send(channelConnectRequest as StreamRequest)
    }

    fun connectChannel(
        channelConnectRequest: StreamRequest.ConnectRequest,
        callBack: (StreamResponse.ChannelResponse.ChannelBody) -> Unit
    ) {
        val id = channelConnectRequest.body.id
        client.streaming.send(channelConnectRequest as StreamRequest)
        val callback: Callback = {
            if (it is Frame.Text) {
                val response: StreamResponse.ChannelResponse = json.decodeFromString(it.readText())
                if (id == response.body.id) {
                    callBack(response.body)
                }

            }
        }
        client.streaming.listen(id, callback)
    }


    fun sendToChannel(channelRequest: StreamRequest.ChannelRequest) {
        client.streaming.send(channelRequest)
    }

    fun disconnectChannel(channelDisconnectRequest: StreamRequest.DisconnectRequest) {
        client.streaming.send(channelDisconnectRequest as StreamRequest)
        client.streaming.unlisted(channelDisconnectRequest.body.id)
    }
}
