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
data class NotesTimelineRequest(
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null,
    val sinceDate: Long? = null,
    val untilDate: Long? = null,
    val includeMyRenotes: Boolean? = true,
    val includeRenoteMyNotes: Boolean? = true,
    val includeLocalRenotes: Boolean? = true,
    val withFiles: Boolean? = false
) : MisskeyNeedAuth()

@Serializable(with = NotesTimelineResponseSerializer::class)
data class NotesTimelineResponse(private val content: List<Note>) : List<Note> by content {
    override fun equals(other: Any?): Boolean = content == other
    override fun hashCode(): Int = content.hashCode()
    override fun toString() = content.joinToString(prefix = "[", postfix = "]", separator = ",")
}

@Serializer(forClass = NotesTimelineResponse::class)
object NotesTimelineResponseSerializer : KSerializer<NotesTimelineResponse> {

    private object NotesTimelineResponseDescriptor :
        SerialDescriptor by ListSerializer(Note.serializer()).descriptor {
        override val serialName: String =
            "dev.usbharu.multim.model.misskey.v12.NotesTimeLineResponse"
    }

    override val descriptor: SerialDescriptor = NotesTimelineResponseDescriptor


    override fun deserialize(decoder: Decoder): NotesTimelineResponse {
        return NotesTimelineResponse(ListSerializer(Note.serializer()).deserialize(decoder))
    }

    override fun serialize(encoder: Encoder, value: NotesTimelineResponse) {
        ListSerializer(Note.serializer()).serialize(encoder, value)
    }

}
