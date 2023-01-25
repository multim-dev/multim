package dev.usbharu.multim.misskey.v12.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class FederationInstance(
    val id: String,
    val caughtAt: Instant,
    val host: String,
    val usersCount: Int,
    val noteCount: Int,
    val followingCount: Int,
    val followersCount: Int,
    val latestRequestSentAt: Instant?,
    val lastCommunicatedAt: Instant,
    val isNotResponding: Boolean,
    val isSuspended: Boolean,
    val isBlocked: Boolean,
    val softwareName: String?,
    val softwareVersion: String?,
    val openRegistrations: Boolean?,
    val name: String?,
    val description: String?,
    val maintainerName: String?,
    val maintainerEmail: String?,
    val iconUrl: String?,
    val faviconUrl: String?,
    val themeColor: String?,
    val infoUpdatedAt: Instant?
)
