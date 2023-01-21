package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val name: String,
    val website: String? = null,
    val vapidKey: String,
    val clientId: String? = null,
    val clientSecret: String? = null
)
