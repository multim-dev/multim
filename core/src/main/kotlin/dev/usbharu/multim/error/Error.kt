package dev.usbharu.multim.error

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok

abstract class Error(open val message: String)

open class ThrowableError(
    val throwable: Throwable,
    override val message: String = throwable.localizedMessage
) : Error(message)

class SimpleError(message: String) : Error(message)

fun <R> Ok(): (R) -> Ok<R> {
    return { Ok(it) }
}

fun ThrowableError(): (Throwable) -> Err<ThrowableError> {
    return { Err(ThrowableError(it)) }
}

fun <T> Error(error: (T) -> Error): (T) -> Err<Error> {
    return { Err(error(it)) }
}
