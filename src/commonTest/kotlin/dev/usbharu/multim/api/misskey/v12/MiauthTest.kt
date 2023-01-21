package dev.usbharu.multim.api.misskey.v12

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

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
