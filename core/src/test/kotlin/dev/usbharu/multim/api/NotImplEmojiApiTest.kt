package dev.usbharu.multim.api

import dev.usbharu.multim.TestUtil.failOnSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotImplEmojiApiTest {

    @Test
    fun get() = runTest {
        NotImplEmojiApi.get("dgeB0b").failOnSuccess()
    }

    @Test
    fun findByName() = runTest {
        NotImplEmojiApi.findByName("KR4y61").failOnSuccess()
    }
}
