package dev.usbharu.multim.api

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import dev.usbharu.multim.TestUtil.failOnSuccess
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

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
