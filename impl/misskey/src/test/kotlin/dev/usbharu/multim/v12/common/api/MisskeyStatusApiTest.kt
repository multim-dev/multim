package dev.usbharu.multim.v12.common.api

import MisskeyTestUtil
import com.github.michaelbull.result.get
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.common.MisskeyStatusId
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.common.api.MisskeyStatusApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class MisskeyStatusApiTest {

    val misskeyStatusApi = MisskeyStatusApi(
        MisskeyApis(
            MisskeyApiClient(
                "",
                "http://localhost/",
                MisskeyTestUtil.createMockHttpClient(
                    checkAuth = false, content =
                    Json.encodeToString(MisskeyTestUtil.createFakeNote("Fx0Z", "D93", "brqNq4v", "earn"))
                )
            )
        )
    )

    @Suppress("JSON_FORMAT_REDUNDANT")
    @Test
    fun serializationTest() {

        @Serializable
        data class Hoge(val a: String, val b: String)

        //language=JSON
        val hoge: Hoge = Json(Json.Default) {
            isLenient = true;ignoreUnknownKeys = true
        }.decodeFromString("""{"a": "aaaa","b": "bbbb"}""")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun findByIdTest() = runTest {
        val findById = misskeyStatusApi.findById(MisskeyStatusId("9a65528e5z", "https://lcalhost")).get()
        println(findById?.content?.text)
    }
}
