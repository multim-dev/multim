package dev.usbharu.multim.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

abstract class ApiClient(var baseUrl: String, val client: HttpClient) {

    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
        return createHttpClient(config)
    }

    suspend inline fun <reified R> post(path: String, baseUrl: String = this.baseUrl): R {
        val post = client.post(baseUrl + path)
        return post.body()
    }

    suspend inline fun <reified T, reified R> post(
        content: T,
        path: String,
        baseUrl: String = this.baseUrl
    ): R {
        val post = client.post(baseUrl + path) {
            contentType(ContentType.Application.Json)
            setBody(content)
        }

        return post.body()
    }

    suspend inline fun <reified T> postWithoutResponse(
        content: T,
        path: String,
        baseUrl: String = this.baseUrl
    ) {
        client.post(baseUrl + path) {
            contentType(ContentType.Application.Json)
            setBody(content)
        }

    }

    suspend fun get(path: String, block: HttpRequestBuilder.() -> Unit): HttpResponse {
        return client.get(baseUrl + path, block)
    }
}

 fun createHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient{
     return HttpClient(CIO)
 }