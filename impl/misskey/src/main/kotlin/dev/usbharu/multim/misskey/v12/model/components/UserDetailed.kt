package dev.usbharu.multim.misskey.v12.model.components

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

@Serializable(with = UserDetailedSerializer::class)
sealed class UserDetailed : User()

object UserDetailedSerializer :
    JsonContentPolymorphicSerializer<UserDetailed>(UserDetailed::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out UserDetailed> =
        when {
            "url" in element.jsonObject -> MeDetailed.serializer()
            else -> UserDetailedNotMe.serializer()
        }
}
