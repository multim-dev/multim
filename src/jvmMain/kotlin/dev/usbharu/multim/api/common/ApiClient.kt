package dev.usbharu.multim.api.common

import io.ktor.client.*
import io.ktor.client.engine.cio.*


actual fun createHttpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(CIO) {
    config(this)
}
