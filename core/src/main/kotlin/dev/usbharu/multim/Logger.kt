package dev.usbharu.multim

import io.github.aakira.napier.Napier

@Suppress("NOTHING_TO_INLINE")
object Logger {

    const val nullString = "null"
    inline fun info(tag: String? = null, message: String? = null) =
        Napier.i(message ?: nullString, null, tag)

    inline fun info(message: String? = null) = Napier.i(message ?: nullString)

    inline fun info(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.i(message ?: nullString, throwable, tag)

    inline fun debug(tag: String? = null, message: String? = null) =
        Napier.d(message ?: nullString, null, tag)

    inline fun debug(message: String? = null) = Napier.d(message ?: nullString)

    inline fun debug(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.d(message ?: nullString, throwable, tag)

    inline fun error(tag: String? = null, message: String? = null) =
        Napier.e(message ?: nullString, null, tag)

    inline fun error(message: String? = null) = Napier.e(message ?: nullString)

    inline fun error(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.e(message ?: nullString, throwable, tag)

    inline fun warn(tag: String? = null, message: String? = null) =
        Napier.w(message ?: nullString, null, tag)

    inline fun warn(message: String? = null) = Napier.w(message ?: nullString)

    inline fun warn(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.w(message ?: nullString, throwable, tag)

    inline fun trace(tag: String? = null, message: String? = null) =
        Napier.v(message ?: nullString, null, tag)

    inline fun trace(message: String? = null) = Napier.v(message ?: nullString)

    inline fun trace(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.v(message ?: nullString, throwable, tag)
}
