package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline

/**
 * タイムライン操作のAPI
 *
 */
@Suppress("PropertyName","VariableNaming")
interface TimelineApi {
    val AVAILABLE_TIMELINES: String
        get() = "timeline/availableTimelines"

    /**
     * 利用可能なタイムライン一覧
     *
     * @return 利用可能なタイムラインのList
     */
    suspend fun availableTimelines(): MultiMResult<List<Timeline>> {
        Logger.debug("Timeline Api", "Not impl timeline api availableTimelines")
        return Err(MultiMError("availableTimelines not implements", null, ErrorType.NOT_IMPL))
    }

    val LISTEN: String
        get() = "timeline/listen"

    /**
     * タイムラインStreamをListenする.
     * タイムラインが更新されるとcallbackが呼び出されます.
     *
     * @param timeline listenするタイムライン
     * @param callback 更新されたときに呼び出されるcallback
     * @receiver
     * @return Listenに成功したらUnit
     */
    suspend fun listen(
        timeline: Timeline,
        callback: (List<Status>) -> Unit
    ): MultiMResult<Unit> {
        Logger.debug("Timeline Api", "Not impl timeline api listen")
        return Err(MultiMError("timeline listen not implements", null, ErrorType.NOT_IMPL))
    }

    val CONNECT: String
        get() = "timeline/connect"

    /**
     * タイムラインに接続する.
     * 基本的にlistenでいい場合が多い.
     *
     * @param timeline 接続するタイムライン
     * @return 成功したらUnit
     */
    suspend fun connect(timeline: Timeline): MultiMResult<Unit> {
        Logger.debug("Timeline Api", "Not impl timeline api connect")
        return Err(MultiMError("timeline connect not implements", null, ErrorType.NOT_IMPL))
    }

    val DISCONNECT: String
        get() = "timeline/disconnect"

    /**
     * タイムラインから切断する
     *
     * @param timeline 切断するタイムライン
     * @param force 強制的に切断する. 実装依存
     * @return 成功したらUnit
     */
    suspend fun disconnect(timeline: Timeline, force: Boolean = false): MultiMResult<Unit> {
        Logger.debug("Timeline Api", "Not impl timeline api disconnect")
        return Err(MultiMError("timeline disconnect not implements", null, ErrorType.NOT_IMPL))
    }


}

/**
 * 未実装API
 *
 * すべてのAPIが未実装なAPI
 */
object NotImplTimelineApi : TimelineApi
