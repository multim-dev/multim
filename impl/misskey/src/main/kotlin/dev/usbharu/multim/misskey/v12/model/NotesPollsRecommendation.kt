package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class PollsRecommendationRequest(
    val limit: Int = 10,
    val offset: Int = 0
)

typealias PollsRecommendationResponse = List<Note>
