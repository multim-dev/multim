package dev.usbharu.multim

import dev.usbharu.multim.error.Error
import dev.usbharu.multim.error.ThrowableError
import io.github.aakira.napier.Napier

object Logger {

    const val nullString = "null"
    fun info(tag: String? = null, message: String? = null) =
        Napier.i(message ?: nullString, null, tag)

    fun info(message: String? = null) = Napier.i(message ?: nullString)

    fun info(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.i(message ?: nullString, throwable, tag)

    fun info(tag: String? = null, message: String? = null, error: Error) {
        message?.let { Napier.i(it, null, tag) }
        if (error is ThrowableError) {
            Napier.i(error.message, error.throwable, tag)
        } else {
            Napier.i(error.message, null, tag)
        }
    }

    fun debug(tag: String? = null, message: String? = null) =
        Napier.d(message ?: nullString, null, tag)

    fun debug(message: String? = null) = Napier.d(message ?: nullString)

    fun debug(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.d(message ?: nullString, throwable, tag)

    fun debug(tag: String? = null, message: String? = null, error: Error) {
        message?.let { Napier.d(it, null, tag) }
        if (error is ThrowableError) {
            Napier.d(error.message, error.throwable, tag)
        } else {
            Napier.d(error.message, null, tag)
        }
    }

    fun error(tag: String? = null, message: String? = null) =
        Napier.e(message ?: nullString, null, tag)

    fun error(message: String? = null) = Napier.e(message ?: nullString)

    fun error(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.e(message ?: nullString, throwable, tag)

    fun error(tag: String? = null, message: String? = null, error: Error) {
        message?.let { Napier.e(it, null, tag) }
        if (error is ThrowableError) {
            Napier.e(error.message, error.throwable, tag)
        } else {
            Napier.e(error.message, null, tag)
        }
    }

    fun warn(tag: String? = null, message: String? = null) =
        Napier.w(message ?: nullString, null, tag)

    fun warn(message: String? = null) = Napier.w(message ?: nullString)

    fun warn(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.w(message ?: nullString, throwable, tag)

    fun warn(tag: String? = null, message: String? = null, error: Error) {
        message?.let { Napier.w(it, null, tag) }
        if (error is ThrowableError) {
            Napier.w(error.message, error.throwable, tag)
        } else {
            Napier.w(error.message, null, tag)
        }
    }

    fun trace(tag: String? = null, message: String? = null) =
        Napier.v(message ?: nullString, null, tag)

    fun trace(message: String? = null) = Napier.v(message ?: nullString)

    fun trace(tag: String? = null, message: String? = null, throwable: Throwable? = null) =
        Napier.v(message ?: nullString, throwable, tag)

    fun trace(tag: String? = null, message: String? = null, error: Error) {
        message?.let { Napier.v(it, null, tag) }
        if (error is ThrowableError) {
            Napier.v(error.message, error.throwable, tag)
        } else {
            Napier.v(error.message, null, tag)
        }
    }
}
