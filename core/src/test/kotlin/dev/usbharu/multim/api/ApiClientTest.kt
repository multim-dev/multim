package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.get
import dev.usbharu.multim.TestUtil.assertIsErr
import dev.usbharu.multim.TestUtil.assertIsOk
import dev.usbharu.multim.error.HttpClientClientError
import dev.usbharu.multim.error.HttpClientServerError
import dev.usbharu.multim.error.ThrowableError
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
abstract class ApiClientTest {

    abstract val apiClient: ApiClient

    val postEmpty_emptyRequest_returnOk = "postEmpty/emptyRequest/returnOk"
    @Test
    open fun postEmpty_emptyRequest_returnOk() {
        runTest {
            val postEmpty = apiClient.postEmpty<PostEmptyData>(postEmpty_emptyRequest_returnOk)
            assertIsOk(postEmpty)
        }
    }

    val postEmpty_illegalRequest_returnErr = "postEmpty/illegalRequest/returnErr"
    @Test
    open fun postEmpty_illegalRequest_returnErr() {
        runTest {
            val postEmpty = apiClient.postEmpty<PostEmptyData>(postEmpty_illegalRequest_returnErr)
            assertInstanceOf(Err::class.java, postEmpty, "返り値がErr型ではない")
            assertInstanceOf(
                HttpClientClientError::class.java,
                (postEmpty as Err).error,
                "ErrorがHttpClientClientError型ではない"
            )
            assertInstanceOf(
                ClientRequestException::class.java,
                postEmpty.error.throwable,
                "throwableがClientRequestException型ではない"
            )
        }
    }

    val postEmpty_serverError_returnErr = "postEmpty/serverError/returnErr"
    @Test
    fun postEmpty_serverError_returnErr() = runTest {
        val postEmpty = apiClient.postEmpty<PostEmptyData>(postEmpty_serverError_returnErr)
        assertInstanceOf(Err::class.java, postEmpty, "返り値がErr型ではない")
        assertInstanceOf(
            HttpClientServerError::class.java,
            (postEmpty as Err).error,
            "ErrorがHttpClientServerError型ではない"
        )
        assertInstanceOf(
            ServerResponseException::class.java,
            postEmpty.error.throwable,
            "throwableがServerResponseException型ではない"
        )
    }

    val post_request_returnOkWithEchoBody = "post/request/returnOkWithEchoBody"
    @Test
    fun post_request_returnOkWithEchoBody() {
        runTest {
            val expectBody = PostEmptyData("957a29f2-03bc-4856-b880-3ef42fbc4c77")
            val actual = apiClient.post<PostEmptyData, PostEmptyData>(
                expectBody,
                post_request_returnOkWithEchoBody
            )
            assertIsOk(actual)
            assertEquals(expectBody, actual.get())
        }
    }

    val post_IllegalRequest_returnErr = "post/illegalRequest/returnErr"
    @Test
    fun post_illegalRequest_returnErr() {
        runTest {
            val body = PostEmptyData("1974d2a0-552e-442d-a60c-07ab410d4e44")
            val actual = apiClient.post<PostEmptyData, PostEmptyData>(
                body,
                post_IllegalRequest_returnErr
            )
            assertIsErr<ThrowableError, HttpClientClientError>(actual)
        }
    }

    val post_serverError_returnErr = "post/serverError/returnErr"
    @Test
    fun post_serverError_returnErr() {
        runTest {
            val body = PostEmptyData("3a84e1a1-5323-4158-824d-ccf9efee0bf7")
            val actual = apiClient.post<PostEmptyData, PostEmptyData>(
                body,
                post_serverError_returnErr
            )
            assertIsErr<ThrowableError, HttpClientServerError>(actual)
        }
    }
    val postWithoutResponse_request_returnOk = "postWithoutResponse/request/returnOk"
    @Test
    fun postWithoutResponse_request_returnOk() {
        runTest {
            val body = PostEmptyData("497676e7-731b-42cb-bc44-dee573858e66")
            val actual =
                apiClient.postWithoutResponse(body, postWithoutResponse_request_returnOk)
            assertIsOk(actual)
        }
    }

    val postWithoutResponse_IllegalRequest_returnErr = "postWithoutResponse/illegalRequest/returnErr"
    @Test
    fun postWithoutResponse_illegalRequest_returnErr() {
        runTest {
            val body = PostEmptyData("20b04325-1386-4076-8068-fb6370ba6150")
            val actual =
                apiClient.postWithoutResponse(body, postWithoutResponse_IllegalRequest_returnErr)
            assertIsErr<ThrowableError, HttpClientClientError>(actual)
        }
    }

    val postWithoutResponse_serverError_returnErr = "postWithoutResponse/serverError/returnErr"
    @Test
    fun postWithoutResponse_serverError_returnErr() {
        runTest {
            val body = PostEmptyData("5ef770aa-b228-4b02-90dd-1c1866933f16")
            val actual =
                apiClient.postWithoutResponse(body, postWithoutResponse_serverError_returnErr)
            assertIsErr<ThrowableError, HttpClientServerError>(actual)
        }
    }

    val get_request_returnOk = "get/request/returnOk"
    @Test
    fun get_request_returnOk() {
        runTest {
            val expectBody = PostEmptyData("43c56ee1-ad7a-418e-9411-2fcb61b34cf9")
            val actual = apiClient.get(get_request_returnOk) {}
            assertIsOk(actual)
            assertEquals(expectBody, actual.get()?.body<PostEmptyData>())
        }
    }

    val get_IllegalRequest_returnErr = "get/illegalRequest/returnErr"
    @Test
    fun get_illegalRequest_returnErr() {
        runTest {
            val actual = apiClient.get(get_IllegalRequest_returnErr) {}
            assertIsErr<ThrowableError, HttpClientClientError>(actual)
        }
    }

    val get_serverError_returnErr = "get/serverError/returnErr"
    @Test
    fun get_serverError_returnErr() {
        runTest {
            val actual = apiClient.get(get_serverError_returnErr) {}
            assertIsErr<ThrowableError, HttpClientServerError>(actual)
        }
    }

    @Serializable
    data class PostEmptyData(val testData: String)
}
