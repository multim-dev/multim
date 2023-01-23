package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class PollsRecommendationRequest(
    val limit: Int = 10,
    val offset: Int = 0
)

typealias PollsRecommendationResponse = List<Note>
