package dev.usbharu.multim.api

import MisskeyTestUtil
import dev.usbharu.multim.Logger
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString

class MisskeyApiClientTest : ApiClientTest() {
    override val apiClient: ApiClient
        get() {
            return MisskeyTestUtil.apiClient(HttpClient(MockEngine {
                when (it.url.encodedPath.replaceFirst("/", "")) {
                    postEmpty_emptyRequest_returnOk -> {
                        respond(PostEmptyData("data"))
                    }

                    postEmpty_illegalRequest_returnErr -> {
                        respondBadRequest()
                    }

                    postEmpty_serverError_returnErr -> {
                        respondError(HttpStatusCode.NotImplemented)
                    }

                    post_request_returnOkWithEchoBody -> {
                        respond(it.body.toByteArray().decodeToString(), HttpStatusCode.OK, headersOf(HttpHeaders.ContentType,"application/json"))
                    }

                    post_IllegalRequest_returnErr -> {
                        respondBadRequest()
                    }

                    post_serverError_returnErr -> {
                        respondError(HttpStatusCode.NotImplemented)
                    }

                    postWithoutResponse_request_returnOk -> {
                        respondOk()
                    }

                    postWithoutResponse_IllegalRequest_returnErr -> {
                        respondBadRequest()
                    }

                    postWithoutResponse_serverError_returnErr -> {
                        respondError(HttpStatusCode.NotImplemented)
                    }

                    get_request_returnOk -> {
                        respond(PostEmptyData("43c56ee1-ad7a-418e-9411-2fcb61b34cf9"))
                    }

                    get_IllegalRequest_returnErr -> {
                        respondBadRequest()
                    }

                    get_serverError_returnErr -> {
                        respondError(HttpStatusCode.NotImplemented)
                    }

                    else -> {
                        Logger.error("不明なエンドポイント ${it.url.encodedPath}")
                        respondBadRequest()
                    }
                }
            }) {
                install(ContentNegotiation) {
                    json(MisskeyTestUtil.json, ContentType.Any)
                }
            })
        }

    private fun PostEmptyData.toJson(): String {
        return DefaultJson.encodeToString(this)
    }

    private fun MockRequestHandleScope.respond(
        postEmptyData: PostEmptyData,
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        headers: Headers = headersOf(HttpHeaders.ContentType, "application/json")
    ): HttpResponseData {
        return respond(postEmptyData.toJson(), statusCode, headers)
    }
}
