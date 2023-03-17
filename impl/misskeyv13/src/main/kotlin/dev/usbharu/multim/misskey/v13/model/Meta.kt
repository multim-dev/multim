package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.Emoji
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
    val ads: List<Ad> = listOf(),
    val requireSetup: Boolean,
    val enableEmail: Boolean,
    val enableServiceWorker: Boolean,
    val translatorAvailable: Boolean,
    val proxyAccountName: String? = null,
    val policies: Policies
) {
    @Serializable
    data class Ad(val place: String, val url: String, val imageUrl: String)

    @Serializable
    data class Policies(
        val gtlAvailable: Boolean = true,
        val ltlAvailable: Boolean = true,
        val canPublicNote: Boolean = true,
        val canInvite: Boolean = true,
        val canManageCustomEmojis: Boolean = false,
        val canHideAds: Boolean = false,
        val driveCapacityMb: Long = 100,
        val pinLimit: Long = 5,
        val antennaLimit: Long = 5,
        val wordMuteLimit: Long = 200,
        val webhookLimit:Long = 3,
        val clipLimit:Long = 10,
        val noteEachClipsLimit:Long = 200,
        val userListLimit:Long = 10,
        val userEachUserListsLimit:Long = 50,
        val rateLimitFactor:Double = 1.0
    )
}
