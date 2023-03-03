package dev.usbharu.multim.api

import dev.usbharu.multim.TestUtil.failOnSuccess
import dev.usbharu.multim.model.Timeline
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotImplTimelineApiTest {
    private val timeline = object : Timeline("") {}

    @Test
    fun availableTimelines() = runTest {
        NotImplTimelineApi.availableTimelines().failOnSuccess()
    }

    @Test
    fun listen() = runTest {
        NotImplTimelineApi.listen(timeline) {}.failOnSuccess()
    }

    @Test
    fun connect() = runTest {
        NotImplTimelineApi.connect(timeline).failOnSuccess()
    }

    @Test
    fun disconnect() = runTest {
        NotImplTimelineApi.disconnect(timeline).failOnSuccess()
    }
}
