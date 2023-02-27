package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline

interface TimelineApi {
    val AVAILABLE_TIMELINES:String
        get() = "timeline/availableTimelines"
    suspend fun availableTimelines(): Result<List<Timeline>,MultiMError>

    val LISTEN:String
        get() = "timeline/listen"
    suspend fun listen(timeline: Timeline,callback: (List<Status>) -> Unit):Result<Unit,MultiMError>

    val CONNECT:String
        get() = "timeline/connect"
    // todo 返り値を詳細にする
    suspend fun connect(timeline: Timeline): Result<Unit,MultiMError>
    val DISCONNECT:String
        get() = "timeline/disconnect"

    /**
     * Disconnect
     *
     * @param timeline 切断するタイムライン
     * @param force 強制的に切断し、もし受信しても無視するように要求する。
     * @return
     */
    suspend fun disconnect(timeline: Timeline, force: Boolean = false): Result<Unit,MultiMError>


}

object NotImplTimelineApi : TimelineApi {
    override suspend fun availableTimelines(): Result<List<Timeline>, MultiMError> {
        Logger.debug("Timeline Api","Not impl timeline api availableTimelines")
        return Err(MultiMError("availableTimelines not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun listen(
        timeline: Timeline,
        callback: (List<Status>) -> Unit
    ): Result<Unit, MultiMError> {
        Logger.debug("Timeline Api","Not impl timeline api listen")
        return Err(MultiMError("timeline listen not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun connect(timeline: Timeline): Result<Unit, MultiMError> {
        Logger.debug("Timeline Api","Not impl timeline api connect")
        return Err(MultiMError("timeline connect not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun disconnect(timeline: Timeline, force: Boolean): Result<Unit, MultiMError> {
        Logger.debug("Timeline Api","Not impl timeline api disconnect")
        return Err(MultiMError("timeline disconnect not implements",null,ErrorType.NOT_IMPL))
    }
}
