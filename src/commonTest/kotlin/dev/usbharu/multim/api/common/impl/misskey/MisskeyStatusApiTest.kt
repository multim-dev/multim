package dev.usbharu.multim.api.common.impl.misskey

import dev.usbharu.multim.api.common.TestUtil
import dev.usbharu.multim.api.misskey.v12.MisskeyApiClient
import dev.usbharu.multim.model.common.impl.misskey.MisskeyStatusId
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class MisskeyStatusApiTest {

    val misskeyStatusApi = MisskeyStatusApi(
        MisskeyApis(
            MisskeyApiClient(
                "",
                "http://localhost/",
                TestUtil.createMockHttpClient(
                    Json.encodeToString(TestUtil.createFakeNote("Fx0Z", "D93", "brqNq4v", "earn"))
                )
            )
        )
    )

    @Test
    fun serializationTest() {

        @Serializable
        data class Hoge(val a: String, val b: String)

        //language=JSON
        val hoge: Hoge = Json(Json.Default) {
            isLenient = true;ignoreUnknownKeys = true;
        }.decodeFromString("""{"a": "aaaa","b": "bbbb"}""")
    }

    @Test
    fun findByIdTest() = runTest {
        val findById = misskeyStatusApi.findById(MisskeyStatusId("9a65528e5z", "https://lcalhost"))
        println(findById.content.getText())
    }
}
