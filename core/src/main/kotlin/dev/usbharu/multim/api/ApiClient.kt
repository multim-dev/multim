package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.usbharu.multim.Logger
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.error.HttpClientClientError
import dev.usbharu.multim.error.HttpClientServerError
import dev.usbharu.multim.error.ThrowableError
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

/**
 * APIクライアントの基底クラス.
 *baseUrl+pathにリクエストされます。
 *
 * @property baseUrl ベースURL
 * @property client 使用するHTTP クライアント
 * @constructor APIクライアントを作成する.
 */
abstract class ApiClient(var baseUrl: String, val client: HttpClient) {

    /**
     * HTTPクライアントを作成する.
     *
     * @param config 設定
     * @receiver
     * @return
     */
    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
        return MultiM.httpClientWithJson.config(config)
    }

    /**
     * 空のリクエストでPOSTリクエストする.
     *
     * @param R レスポンスのクラス
     * @param path リクエストするパス
     * @param baseUrl リクエストのbaseURL
     * @return レスポンス
     */
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

    /**
     * リクエストボディを指定してPOSTリクエストする.
     *
     * @param T リクエストするクラス
     * @param R レスポンスのクラス
     * @param content リクエストボディ
     * @param path リクエストするパス
     * @param baseUrl リクエストのbaseURL
     * @return レスポンス
     */
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
//        Logger.warn(post.bodyAsText())
        return runCatching<R> { post.body() }.fold(
            onSuccess = { Ok(it) },
            onFailure = {
                Logger.warn("Api Client", "FAILURE Get body $baseUrl$path ")
                Err(ThrowableError(it))
            })
    }

    /**
     * 空のレスポンスを期待してPOSTリクエストする.
     *
     * @param T リクエストボディのクラス
     * @param content リクエストボディ
     * @param path リクエストするパス
     * @param baseUrl リクエストのbaseURL
     * @return 成功したらUnit
     */
    @Suppress("TooGenericExceptionCaught")
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
        } catch (e: Exception) {
            return Err(ThrowableError(e))
        }
        return Ok(Unit)

    }

    /**
     * GETリクエストする
     *
     * @param path
     * @param block HttpRequestBuilderでリクエストを構成する.
     * @receiver HttpRequestBuilder
     * @return 生のレスポンス
     */
    @Suppress("TooGenericExceptionCaught")
    suspend fun get(
        path: String,
        block: HttpRequestBuilder.() -> Unit
    ): Result<HttpResponse, ThrowableError> {
        Logger.trace("Api Client", "Get $baseUrl$path")
        return try {
            Ok(client.get(baseUrl + path, block))
        } catch (e: ClientRequestException) {
            Err(HttpClientClientError(e))
        } catch (e: ServerResponseException) {
            Err(HttpClientServerError(e))
        } catch (e: Exception) {
            Logger.warn("Api Client", "FAILURE Get $baseUrl$path")
            Err(ThrowableError(e))
        }
    }
}

/**
 * HTTP クライアントを作成する.
 *
 * @param config 構成
 * @receiver HttpClientConfig
 * @return 作成されたHttpClient
 */// todo なぜこれがここにあるのかわからないが、ここにあるのはおかしいので消す。
@Deprecated(
    "このAPIがここにあるのはおかしいので削除予定です。", ReplaceWith(
        "MultiM.httpClientWithJson.config(config)",
        "dev.usbharu.multim.MultiM"
    )
)
fun createHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient {
    Logger.warn("DEPRECATED", "Deprecated API calls. This API is deprecated.")
    return MultiM.httpClientWithJson.config(config)
}
