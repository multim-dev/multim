package dev.usbharu.multim.api

import org.junit.jupiter.api.Test

abstract class TimelineApiTest {

    @Test
    abstract fun availableTimelines()

    @Test
    abstract fun listen()

    @Test
    abstract fun listen_illegalTimeline()

    @Test
    abstract fun connect()

    @Test
    abstract fun connect_illegalTimeline()

    @Test
    abstract fun disconnect()

    @Test
    abstract fun disconnect_illegalTimeline()
}
