package dev.usbharu.multim.api.common

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun createHttpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Js).config(config)
}
