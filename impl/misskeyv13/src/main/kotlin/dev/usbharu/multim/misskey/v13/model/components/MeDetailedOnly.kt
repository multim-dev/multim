package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.serialization.Serializable

@Serializable
class Integrations

@Serializable
class SecurityKey

@Serializable
data class MeDetailedOnly(
    val avatarId: String?,
    val bannerId: String?,
    val injectFeaturedNote: Boolean? = null,
    val receiveAnnouncementEmail: Boolean? = null,
    val alwaysMarkNsfw: Boolean?,
    val autoSensitive: Boolean?,
    val carefulBot: Boolean?,
    val autoAcceptFollowed: Boolean?,
    val noCrawle: Boolean?,
    val isExplorable: Boolean,
    val isDeleted: Boolean,
    val hideOnlineStatus: Boolean,
    val hasUnreadSpecifiedNotes: Boolean,
    val hasUnreadMentions: Boolean,
    val hasUnreadAnnouncement: Boolean,
    val hasUnreadAntenna: Boolean,
    val hasUnreadChannel: Boolean,
    val hasUnreadMessagingMessage: Boolean,
    val hasUnreadNotification: Boolean,
    val hasPendingReceivedFollowRequest: Boolean,
    val integrations: Integrations,
    val mutedWords: List<List<String>>,
    val mutedInstances: List<String>,
    val mutingNotificationTypes: List<String>,
    val emailNotificationTypes: List<String>,
    val email: String? = null,
    val emailVerified: Boolean? = null,
    val securityKeysList: List<SecurityKey>
)
