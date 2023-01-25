package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.UserDetailedNotMe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApShowRequest(
    val uri: String
) : MisskeyNeedAuth()


@Serializable
sealed class ApShowResponse {
    @SerialName("User")
    @Serializable
    data class TypeUser(
        @SerialName("object") val user: UserDetailedNotMe
    ) : ApShowResponse()

    @SerialName("Note")
    @Serializable
    data class TypeNote(
        @SerialName("object") val note: Note
    ) : ApShowResponse()
}
