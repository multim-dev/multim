package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.Emoji
import kotlinx.serialization.Serializable

@Serializable
data class MetaRequest(val detail: Boolean = true)

@Serializable
data class MetaResponse(
    val maintainerName: String? = null,
    val maintainerEmail: String? = null,
    val version: String,
    val name: String? = null,
    val uri: String,
    val description: String? = null,
    val langs: List<String> = listOf(),
    val tosUrl: String? = null,
    val repositoryUrl: String = "https://github.com/misskey-dev/misskey",
    val feedbackUrl: String = "https://github.com/misskey-dev/misskey/issues/new",
    val defaultDarkTheme: String? = null,
    val defaultLightTheme: String? = null,
    val disableRegistration: Boolean,
    val disableLocalTimeline: Boolean,
    val disableGlobalTimeline: Boolean,
    val driveCapacityPerLocalUserMb: Long, //めちゃくちゃでかい値をセットしている人を観測しているのでLong
    val cacheRemoteFiles: Boolean,
    val emailRequiredForSignup: Boolean,
    val enableHcaptcha: Boolean,
    val hcaptchaSiteKey: String? = null,
    val enableRecaptcha: Boolean,
    val recaptchaSiteKey: String? = null,
    val swPublickey: String? = null,
    val mascotImageUrl: String = "/assets/ai.png",
    val bannerUrl: String? = null,
    val errorImageUrl: String = "https://xn--p31a.moe/aiart/yubitun.png",
    val iconUrl: String? = null,
    val maxNoteTextLength: Long,
    val emojis: List<Emoji>,
    val ads: List<Ad> = listOf(),
    val requireSetup: Boolean,
    val enableEmail: Boolean,
    val enableTwitterIntegration: Boolean,
    val enableGithubIntegration: Boolean,
    val enableDiscordIntegration: Boolean,
    val enableServiceWorker: Boolean,
    val translatorAvailable: Boolean,
    val proxyAccountName: String? = null,
    val features: Features? = null
) {
    @Serializable
    data class Ad(val place: String, val url: String, val imageUrl: String)

    @Serializable
    data class Features(
        val registration: Boolean,
        val localTimeLine: Boolean,
        val globalTimeLine: Boolean,
        val elasticsearch: Boolean,
        val hcaptcha: Boolean,
        val recaptcha: Boolean,
        val objectStorage: Boolean,
        val twitter: Boolean,
        val github: Boolean,
        val discord: Boolean,
        val serviceWorker: Boolean,
        val miauth: Boolean = true
    )
}
