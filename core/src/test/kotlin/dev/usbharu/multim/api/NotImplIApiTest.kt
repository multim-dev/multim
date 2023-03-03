package dev.usbharu.multim.api

import dev.usbharu.multim.TestUtil.failOnSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotImplIApiTest {

    @Test
    fun profile() = runTest {
        NotImplIApi.profile().failOnSuccess()
    }

    @Test
    fun statuses() = runTest {
        NotImplIApi.statuses().failOnSuccess()
    }
}
