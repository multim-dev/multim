package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.model.SingleTokenAuth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class ITestE2E {

    val misskeyApiClient = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        MultiM.httpClientWithJson.config {}
    )

    @Test
    fun iTest() = runTest {
        val failOnError = I(misskeyApiClient).i().failOnError()
        println(failOnError)
    }
}
