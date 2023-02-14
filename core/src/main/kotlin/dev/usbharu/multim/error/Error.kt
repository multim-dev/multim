package dev.usbharu.multim.error

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result as MichaelbullResultResult

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

fun <T,R : Error> Error(error: (T) -> R): (T) -> Err<R> {
    return { Err(error(it)) }
}

fun <A,B : Error> Result<A>.foldWithOk(onSuccess:(A)->Ok<A> = Ok(), onFailure:(Throwable)->B): MichaelbullResultResult<A,B> {
    return fold(
        onSuccess = onSuccess,
        onFailure = Error(onFailure)
    )
}
