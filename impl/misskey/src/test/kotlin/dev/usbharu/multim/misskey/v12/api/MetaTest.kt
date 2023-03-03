package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.MetaRequest
import dev.usbharu.multim.model.SingleTokenAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MetaTest

@OptIn(ExperimentalCoroutinesApi::class)
class MetaTestE2E {

    val misskeyApiClient = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        MultiM.httpClientWithJson.config {}
    )

    @Test
    fun meta() = runTest {
        val meta = Meta(misskeyApiClient).meta(MetaRequest()).failOnError()
        println(meta)
    }
}
