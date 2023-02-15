package dev.usbharu.multim.misskey.v12.common.api

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.common.MisskeyTimeline
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.NoteConverter.toStatus
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest
import dev.usbharu.multim.misskey.v12.model.StreamRequest.ConnectRequest.Body
import dev.usbharu.multim.misskey.v12.model.StreamRequest.DisconnectRequest
import dev.usbharu.multim.misskey.v12.model.StreamRequest.DisconnectRequest.*
import dev.usbharu.multim.misskey.v12.model.StreamResponse.ChannelResponse.ChannelBody.NoteBody
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline
import java.util.*

class MisskeyTimelineApi(private val misskeyApis: MisskeyApis) : TimelineApi {
    override suspend fun availableTimelines(): Result<List<Timeline>, MultiMError> {
        fun uuid() = UUID.randomUUID().toString()

        return Ok(listOf(
            MisskeyTimeline(uuid(), "homeTimeline", Body.Channel.HOME_TIMELINE),
            MisskeyTimeline(uuid(), "hybridTimeline", Body.Channel.HYBRID_TIMELINE),
            MisskeyTimeline(uuid(), "localTimeline", Body.Channel.LOCAL_TIMELINE),
            MisskeyTimeline(uuid(), "globalTimeline", Body.Channel.GLOBAL_TIMELINE)
        ))
    }

    override suspend fun listen(timeline: Timeline, callback: (List<Status>) -> Unit): Result<Unit, MultiMError> {
        if (timeline is MisskeyTimeline) {
            misskeyApis.timeline.connectChannel(
                ConnectRequest(
                    Body(
                        timeline.id,
                        timeline.channel
                    )
                )
            ) { channelBody ->
                if (channelBody is NoteBody) {
                    callback.invoke(listOf(channelBody.body.toStatus()))
                }
            }
        }
        return Ok(Unit)
    }

    override suspend fun connect(timeline: Timeline): Result<Unit, MultiMError> {
        if (timeline is MisskeyTimeline) {
            misskeyApis.timeline.connectChannel(
                ConnectRequest(
                    Body(
                        timeline.id,
                        timeline.channel
                    )
                )
            )
        }
        return Ok(Unit)
    }

    override suspend fun disconnect(timeline: Timeline, force: Boolean): Result<Unit, MultiMError> {
        if (timeline is MisskeyTimeline) {
            misskeyApis.timeline.disconnectChannel(
                DisconnectRequest(
                    Body(
                        timeline.id
                    )
                )
            )
        }
        return Ok(Unit)
    }
}
