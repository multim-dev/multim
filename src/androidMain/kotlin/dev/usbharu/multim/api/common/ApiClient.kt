package dev.usbharu.multim.api.common

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*


actual fun createHttpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)
}
