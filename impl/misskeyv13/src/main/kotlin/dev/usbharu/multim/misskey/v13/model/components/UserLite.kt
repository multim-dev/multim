package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLite(
    val id: String,
    val name: String?,
    val username: String,
    val host: String? = null,
    val avatarUrl: String? = null,
    val avatarBlurhash: String? = null,
    val avatarColor: String? = null,
    val isAdmin: Boolean = false,
    val isModerator: Boolean = false,
    val isBot: Boolean? = null,
    val isCat: Boolean? = null,
    val emojis: Map<String,String> = emptyMap(),
    val onlineStatus: String = "online"
//    val onlineStatus: OnlineStatus
) : User()

enum class OnlineStatus(val string: String) {
    @SerialName("unknown")
    UNKNOWN("unknown"),
    @SerialName("online")
    ONLINE("online"),
    @SerialName("active")
    ACTIVE("active"),
    @SerialName("offline")
    OFFLINE("offline")
}
