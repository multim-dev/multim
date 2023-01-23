package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
class Page(
    val id: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val title: String,
    val name: String,
    val summary: String?,
    val content: List<Content>,
    val variables: List<Variable>,
    val userId: String,
    val user: UserLite
)

@Serializable
class Variable

@Serializable
class Content
