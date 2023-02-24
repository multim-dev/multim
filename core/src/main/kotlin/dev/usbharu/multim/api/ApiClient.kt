package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.usbharu.multim.Logger
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.error.HttpClientClientError
import dev.usbharu.multim.error.HttpClientServerError
import dev.usbharu.multim.error.Ok
import dev.usbharu.multim.error.ThrowableError
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
        Logger.trace("Api Client", "Post empty $baseUrl$path")
        val post = try {
            client.post(baseUrl + path)
        } catch (e: ClientRequestException) {
            Logger.warn(
                "Api Client",
                "FAILURE Client Request Exception at Post empty $baseUrl$path"
            )
            return Err(HttpClientClientError(e))
        } catch (e: ServerResponseException) {
            Logger.warn(
                "Api Client",
                "FAILURE Server Response Exception at Post empty $baseUrl$path"
            )
            return Err(HttpClientServerError(e))
        }
        return runCatching<R> { post.body() }.fold(
            onSuccess = { Ok(it) },
            onFailure = {
                Logger.warn("Api Client", "FAILURE Get body $baseUrl$path")
                Err(ThrowableError(it))
            })
    }

    suspend inline fun <reified T, reified R> post(
        content: T,
        path: String,
        baseUrl: String = this.baseUrl
    ): Result<R, ThrowableError> {
        Logger.trace("Api Client", "Post with request body to $baseUrl$path ")
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
        Logger.warn(post.bodyAsText())
        return runCatching<R> { post.body() }.fold(
            onSuccess = { Ok(it) },
            onFailure = {
                Logger.warn("Api Client", "FAILURE Get body $baseUrl$path ")
                Err(ThrowableError(it))
            })
    }

    suspend inline fun <reified T> postWithoutResponse(
        content: T,
        path: String,
        baseUrl: String = this.baseUrl
    ): Result<Unit, ThrowableError> {
        Logger.trace("Api Client", "Post without response $baseUrl$path")
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
        return Ok(Unit)

    }

    suspend fun get(
        path: String,
        block: HttpRequestBuilder.() -> Unit
    ): Result<HttpResponse, ThrowableError> {
        Logger.trace("Api Client", "Get $baseUrl$path")
        return runCatching<HttpResponse> {
            client.get(
                baseUrl + path,
                block
            )
        }.fold(onSuccess = { Ok(it) }, onFailure = {
            Logger.warn("Api Client", "FAILURE Get $baseUrl$path")
            Err(ThrowableError(it))
        })
    }
}

// todo なぜこれがここにあるのかわからないが、ここにあるのはおかしいので消す。
@Deprecated(
    "このAPIがここにあるのはおかしいので削除予定です。", ReplaceWith(
        "MultiM.httpClientWithJson.config(config)",
        "dev.usbharu.multim.MultiM"
    )
)
fun createHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient {
    Logger.warn("DEPRECATED","Deprecated API calls. This API is deprecated.")
    return MultiM.httpClientWithJson.config(config)
}
