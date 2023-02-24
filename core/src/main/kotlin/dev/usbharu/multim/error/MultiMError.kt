package dev.usbharu.multim.error

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.plugins.*

open class MultiMError(
    message: String,
    val _throwable: Throwable? = null,
    val errorType: ErrorType
) :
    ThrowableError(_throwable ?: Throwable(message), "MultiM ${errorType.message} ERROR : $message")

class MultiMHttpError(val httpError: HttpError, throwable: Throwable? = httpError.throwable) :
    MultiMError(
        """
        HTTP ERROR ${httpError.code} ${httpError.errorName}.
        Message : ${httpError.message}
        ${throwable?.let { "Exception : ${it.message}" }}
        ${httpError.throwable?.let { "Exception : ${it.message}" }}
    """.trimIndent(),
        throwable,
        ErrorType.HTTP
    ) {

    constructor(e: ClientRequestException) : this(
        HttpError.ClientError(
            e.response.status.value,
            e.message,
            e
        )
    )

    constructor(e: ServerResponseException) : this(
        HttpError.ServerError(
            e.response.status.value,
            e.message,
            e
        )
    )

    constructor(httpClientClientError: HttpClientClientError) : this(
        (httpClientClientError.throwable as ClientRequestException).let {
            HttpError.ClientError(
                it.response.status.value,
                it.message,
                it
            )
        }, httpClientClientError.throwable
    )

    constructor(httpClientServerError: HttpClientServerError) : this(
        (httpClientServerError.throwable as ServerResponseException).let {
            HttpError.ClientError(
                it.response.status.value,
                it.message,
                it
            )
        }, httpClientServerError.throwable
    )
}

open class MultiMContentTransformError(
    val typeName: String,
    message: String
) :
    MultiMError(
        """
            TypeName : $typeName
            Message :  $message
        """.trimIndent(), null, ErrorType.CONTENT_TRANSFORM
    )

class MultiMJsonContentTransformError(
    message: String
) : MultiMContentTransformError(
    "json",
    message
)


sealed class HttpError(
    val code: Int,
    val message: String,
    val errorName: String,
    val throwable: Throwable? = null
) {
    data class ClientError(val _code: Int, val _message: String, val _throwable: Throwable?) :
        HttpError(_code, _message, "Client", _throwable)

    data class ServerError(val _code: Int, val _message: String, val _throwable: Throwable?) :
        HttpError(_code, _message, "Server", _throwable)
}


enum class ErrorType(val message: String) {
    API("API"),
    HTTP("HTTP"),
    CONTENT_TRANSFORM("Content Transform"),
    ILLEGAL_ARGUMENT("Illegal Argument"),
    NOT_IMPL("Not yet implement"),
    UNKNOWN("UNKNOWN")
}

fun <T> Result<T, ThrowableError>.mapMultiMError(): Result<T, MultiMError> {
    return when (this) {
        is Ok -> this
        is Err -> {
            when (this.error) {
                is HttpClientServerError -> {
                    Err(MultiMHttpError(this.error.throwable as ServerResponseException))
                }

                is HttpClientClientError -> {
                    Err(MultiMHttpError(this.error.throwable as ClientRequestException))
                }
                is MultiMError -> {
                    Err(this.error as MultiMError)
                }
                else -> {
                    Err(MultiMError(this.error.message, this.error.throwable, ErrorType.UNKNOWN))
                }
            }
        }
    }
}

typealias MultiMResult<T> = Result<T, MultiMError>

fun TODO(throwable: Throwable? = runCatching { kotlin.TODO() }.exceptionOrNull()): Err<MultiMError> {
    return Err(MultiMError("Not yet Implement.", throwable, ErrorType.NOT_IMPL))
}
