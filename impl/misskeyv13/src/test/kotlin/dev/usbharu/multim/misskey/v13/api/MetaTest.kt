package dev.usbharu.multim.misskey.v13.api

import MisskeyTestUtil.apiClient
import MisskeyTestUtil.baseUrl
import MisskeyTestUtil.createMockHttpClient
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v13.model.MetaRequest
import dev.usbharu.multim.misskey.v13.model.MetaResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MetaTest {

    @Test
    fun meta_detailTrue_returnMeta() = runTest {
        val metaResponse = MetaResponse(
            maintainerName = "maintainerName",
            maintainerEmail = "maintainer@example.com",
            version = "12.119.2",
            name = "misskey",
            uri = "https://example.com",
            description = "test instance",
            langs = emptyList(),
            tosUrl = null,
            repositoryUrl = "https://example.com",
            feedbackUrl = "https://example.com",
            defaultDarkTheme = "darkTheme",
            defaultLightTheme = "lightTheme",
            disableRegistration = false,
            cacheRemoteFiles = true,
            emailRequiredForSignup = false,
            enableHcaptcha = false,
            hcaptchaSiteKey = null,
            enableRecaptcha = false,
            recaptchaSiteKey = null,
            swPublickey = null,
            mascotImageUrl = "/assets/ai.png",
            bannerUrl = null,
            errorImageUrl = "https://example.com",
            iconUrl = null,
            maxNoteTextLength = 3000,
            ads = emptyList(),
            requireSetup = false,
            enableEmail = false,
            enableServiceWorker = false,
            translatorAvailable = false,
            proxyAccountName = "proxy",
            policies = MetaResponse.Policies()
        )

        val response = Meta(
            apiClient(
                createMockHttpClient(
                    metaResponse,
                    false,
                    baseUrl + "api/meta",
                    MetaResponse.serializer()
                )
            )
        ).meta(
            MetaRequest()
        ).failOnError()
        assertEquals(metaResponse,response)
    }
}
