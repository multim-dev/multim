package dev.usbharu.multim.model.misskey.v12.components

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
