package dev.usbharu.multim.misskey.v13.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class StreamRequest {

    @SerialName("connect")
    @Serializable
    data class ConnectRequest(
        val body: Body
    ) : StreamRequest() {
        @Serializable
        data class Body(
            val id: String,
            val channel: Channel
        ) {
            @Serializable
            enum class Channel {
                @SerialName("globalTimeline")
                GLOBAL_TIMELINE,

                @SerialName("homeTimeline")
                HOME_TIMELINE,

                @SerialName("hybridTimeline")
                HYBRID_TIMELINE,

                @SerialName("localTimeline")
                LOCAL_TIMELINE,

                @SerialName("main")
                MAIN
            }
        }

    }

    @SerialName("channel")
    @Serializable
    data class ChannelRequest(
        val body: Body
    ) : StreamRequest() {
        @Serializable
        sealed class Body(
            val id: String
        ) {
            // todo 送るメッセージ
        }
    }

    @SerialName("disconnect")
    @Serializable
    data class DisconnectRequest(
        val body: Body
    ) : StreamRequest() {
        @Serializable
        data class Body(
            val id: String
        )
    }
}
