package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class NotesHybridTimelineRequest(
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null,
    val sinceDate: Long? = null,
    val untilDate: Long? = null,
    val includeMyRenotes: Boolean = true,
    val includeRenotedMyNotes: Boolean = true,
    val includeLocalRenotes: Boolean = true,
    val withFiles: Boolean = false
) : MisskeyNeedAuth()


@Serializable(with = NotesHybridTimelineResponseSerializer::class)
data class NotesHybridTimelineResponse(private val content: List<Note>) : List<Note> by content {
    override fun equals(other: Any?): Boolean = content == other
    override fun hashCode(): Int = content.hashCode()
    override fun toString() = content.joinToString(prefix = "[", postfix = "]", separator = ",")
}

@Serializer(forClass = NotesHybridTimelineResponse::class)
object NotesHybridTimelineResponseSerializer : KSerializer<NotesHybridTimelineResponse> {

    private object NotesHybridTimelineResponseDescriptor :
        SerialDescriptor by ListSerializer(Note.serializer()).descriptor {
        override val serialName: String =
            "dev.usbharu.multim.model.misskey.v12.NotesHybridTimeLineResponse"
    }

    override val descriptor: SerialDescriptor =
        NotesHybridTimelineResponseDescriptor


    override fun deserialize(decoder: Decoder): NotesHybridTimelineResponse {
        return NotesHybridTimelineResponse(
            ListSerializer(Note.serializer()).deserialize(
                decoder
            )
        )
    }

    override fun serialize(encoder: Encoder, value: NotesHybridTimelineResponse) {
        ListSerializer(Note.serializer()).serialize(encoder, value)
    }

}
