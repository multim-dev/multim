package dev.usbharu.multim.mastodon.model.components

import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val name: String,
    val website: String? = null,
    val vapidKey: String,
    val clientId: String? = null,
    val clientSecret: String? = null
)
