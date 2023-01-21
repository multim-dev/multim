package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailedNotMeOnly(
    val url: String? = null,
    val uri: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val lastFetchedAt: LocalDateTime? = null,
    val bannerUrl: String? = null,
    val bannerBlurhash: String? = null,
    val bannerColor: String? = null,
    val isLocked: Boolean,
    val isSilenced: Boolean,
    val isSuspended: Boolean,
    val description: String? = null,
    val location: String? = null,
    val birthday: String? = null,
    val lang: String? = null,
    val fields: List<Field>,
    val followersCount: Int,
    val followingCount: Int,
    val notesCount: Int,
    val pinnedNoteIds: List<String>,
    val pinnedNotes: List<Note>,
    val pinnedPageId: String? = null,
    val pinnedPage: Page? = null,
    val publicReactions: Boolean,
    val twoFactorEnabled: Boolean = false,
    val usePasswordLessLogin: Boolean = false,
    val securityKeys: Boolean = false,
    val isFollowing: Boolean? = null,
    val isFollowed: Boolean? = null,
    val hasPendingFollowRequestFromYou: Boolean? = null,
    val hasPendingFollowRequestToYou: Boolean? = null,
    val isBlocking: Boolean? = null,
    val isBlocked: Boolean? = null,
    val isMuted: Boolean? = null
)
