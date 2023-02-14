package dev.usbharu.multim.error

abstract class HttpClientError(throwable: Throwable, message: String = throwable.localizedMessage) :
    ThrowableError(throwable, message)

class HttpClientServerError(throwable: Throwable, message: String = throwable.localizedMessage) :
    HttpClientError(throwable, message)

class HttpClientClientError(throwable: Throwable, message: String = throwable.localizedMessage) :
    HttpClientError(throwable, message)
