package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.MetaRequest
import dev.usbharu.multim.model.SingleTokenAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MetaTestE2E {

    private val misskeyApiClient = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskeyv13_token")),
        System.getProperty("multim_misskeyv13_instance"),
        MultiM.httpClientWithJson.config {}
    )

    @Test
    fun meta() = runTest {
        Meta(misskeyApiClient).meta(MetaRequest()).failOnError()
    }
}
