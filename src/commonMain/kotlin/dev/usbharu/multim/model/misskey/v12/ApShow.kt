package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.Note
import dev.usbharu.multim.model.misskey.v12.components.UserDetailedNotMe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApShowRequest(
    val uri: String
)


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
