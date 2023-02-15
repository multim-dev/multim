package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.error.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

abstract class ApiClient(var baseUrl: String, val client: HttpClient) {

    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
        return createHttpClient(config)
    }

    suspend inline fun <reified R> postEmpty(
        path: String,
        baseUrl: String = this.baseUrl
    ): Result<R, ThrowableError> {
        val post = try {
            client.post(baseUrl + path)
        } catch (e: ClientRequestException) {
            return Err(HttpClientClientError(e))
        } catch (e: ServerResponseException) {
            return Err(HttpClientServerError(e))
        }
        return runCatching<R> { post.body() }.fold(
            onSuccess = { Ok(it) },
            onFailure = { Err(ThrowableError(it)) })
    }

    suspend inline fun <reified T, reified R> post(
        content: T,
        path: String,
        baseUrl: String = this.baseUrl
    ): Result<R, ThrowableError> {
        val post =
            try {
                client.post(baseUrl + path) {
                    contentType(ContentType.Application.Json)
                    setBody(content)
                }
            } catch (e: ClientRequestException) {
                return Err(HttpClientClientError(e))
            } catch (e: ServerResponseException) {
                return Err(HttpClientServerError(e))
            }
        return runCatching<R> { post.body() }.fold(
            onSuccess = { Ok(it) },
            onFailure = { Err(ThrowableError(it)) })
    }

    suspend inline fun <reified T> postWithoutResponse(
        content: T,
        path: String,
        baseUrl: String = this.baseUrl
    ): Result<Unit, ThrowableError> {
        return runCatching<Unit> {
            client.post(baseUrl + path) {
                contentType(ContentType.Application.Json)
                setBody(content)
            }
        }.fold(onSuccess = Ok(), onFailure = ThrowableError())
    }

    suspend fun get(
        path: String,
        block: HttpRequestBuilder.() -> Unit
    ): Result<HttpResponse, ThrowableError> {
        return runCatching<HttpResponse> {
            client.get(
                baseUrl + path,
                block
            )
        }.fold(onSuccess = { Ok(it) }, onFailure = { Err(ThrowableError(it)) })
    }
}

// todo なぜこれがここにあるのかわからないが、ここにあるのはおかしいので消す。
fun createHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient {
    return MultiM.httpClientWithJson.config(config)
}
