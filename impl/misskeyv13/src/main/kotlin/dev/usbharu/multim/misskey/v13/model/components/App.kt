package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.serialization.Serializable

@Serializable
data class App(
    val id: String,
    val name: String,
    val callbackUrl: String?,
    val permission: List<String>,
    val secret: String? = null,
    val isAuthorized: Boolean? = null
)
