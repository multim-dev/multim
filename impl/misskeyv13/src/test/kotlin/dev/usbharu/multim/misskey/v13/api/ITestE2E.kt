package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.model.SingleTokenAuth
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class ITestE2E {

    val misskeyApiClient = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskeyv13_token")),
        System.getProperty("multim_misskeyv13_instance"),
        MultiM.httpClientWithJson.config {}
    )

    @Test
    fun iTest() = runTest {
        val failOnError = I(misskeyApiClient).i().failOnError()
        println(failOnError)
    }
}
