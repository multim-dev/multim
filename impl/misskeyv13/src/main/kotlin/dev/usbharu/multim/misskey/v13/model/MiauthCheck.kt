package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.User
import kotlinx.serialization.Serializable

@Serializable
data class MiauthCheckResponse(
    val ok: Boolean,
    val token: String? = null,
    val user: User? = null
)

data class MiauthCheckRequest(
    val sessionId: String
)
