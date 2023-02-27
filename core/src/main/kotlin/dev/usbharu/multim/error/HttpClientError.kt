package dev.usbharu.multim.error

import io.ktor.client.plugins.*

abstract class HttpClientError(
    throwable: ResponseException,
    message: String = throwable.localizedMessage
) :
    ThrowableError(throwable, message)

class HttpClientServerError(
    throwable: ServerResponseException,
    message: String = throwable.localizedMessage
) :
    HttpClientError(throwable, message)

class HttpClientClientError(
    throwable: ClientRequestException,
    message: String = throwable.localizedMessage
) :
    HttpClientError(throwable, message)
