package dev.usbharu.multim.api

import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline

interface TimelineApi {
    suspend fun availableTimelines(): List<Timeline>

    suspend fun listen(timeline: Timeline,callback: (List<Status>) -> Unit)

    // todo 返り値を詳細にする
    suspend fun connect(timeline: Timeline): Boolean

    /**
     * Disconnect
     *
     * @param timeline 切断するタイムライン
     * @param force 強制的に切断し、もし受信しても無視するように要求する。
     * @return
     */
    suspend fun disconnect(timeline: Timeline, force: Boolean = false): Boolean


}
