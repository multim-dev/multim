package dev.usbharu.multim.model

/**
 * Option
 *
 * @property title タイトル
 * @property votes パーセント
 * @property voted 投票したか
 * @constructor Create Option
 */
abstract class Option(
    val title: Content,
    val votes: Float,
    val voted: Boolean
)

abstract class OptionForPost(val title: Content)
