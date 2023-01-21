package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.User
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
