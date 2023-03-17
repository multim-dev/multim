package dev.usbharu.multim.misskey.v13.model

import kotlinx.serialization.Serializable

@Serializable
data class NotesTranslateRequest(
    val noteId: String,
    val targetLang: String
)


/**
 * Notes translate response
 *
 * 試せないので合ってるかわかりません
 *
 * @property sourceLang
 * @property text
 * @constructor Create empty Notes translate response
 */
@Serializable
data class NotesTranslateResponse(
    val sourceLang: String? = null,
    val text: String? = null
)
