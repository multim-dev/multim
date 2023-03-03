package dev.usbharu.multim.misskey.v12.api

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MiauthTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun auth() = runTest {
//        println(Miauth("","http://localhost",TestUtil.createMockHttpClient()).auth())
    }

    @Test
    fun check() = runTest {
//        val check =
//            Miauth("","http://localhost", TestUtil.createMockHttpClient(content = Json.encodeToString(MiauthCheckResponse(ok = true)))).check(MiauthCheckRequest(sessionId = UUID.generateUUID()))
//        assertTrue(check.ok)

    }
}
