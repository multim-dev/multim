@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package dev.usbharu.multim.v12.api

import MisskeyTestUtil.checkAuth
import MisskeyTestUtil.createFakeNoteToString
import MisskeyTestUtil.json
import dev.usbharu.multim.api.createHttpClient
import dev.usbharu.multim.misskey.v12.api.Ap
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.ApShowRequest
import dev.usbharu.multim.misskey.v12.model.ApShowResponse
import io.github.artsok.RepeatedIfExceptionsTest
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test


class ApTest {

    val misskeyApiClient = MisskeyApiClient(
        System.getProperty("multim_misskey_token"), System.getProperty("multim_misskey_instance"),
        createHttpClient()
    )

    @Test
    fun show_showUserMockRequest_respondTypeUser() = runTest {
        //language=JSON
        val typeUser = """{
  "type": "User",
  "object": {
    "id": "54e9d",
    "name": "Pvf4U",
    "username": "e1zfG7u",
    "onlineStatus": "online",
    "createdAt": "2023-01-22T12:40:43.000Z",
    "publicReactions": false
  }
}"""

        val misskeyApiClient = MisskeyApiClient(
            "aaaaaaaa", "https://localhost", HttpClient(
                MockEngine(
                    checkAuth(
                        typeUser, HttpStatusCode.OK, headersOf("Content-Type", "application/json")
                    )
                )
            )
        )
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://localhost/test/IN7OFhht"))
        assertEquals(json.decodeFromString<ApShowResponse.TypeUser>(typeUser), show)
    }


    @Test
    fun show_showNoteRequestMock_respondTypeNote() = runTest {
        val typeNote = """
            {
              "type": "Note",
              "object": ${createFakeNoteToString("gK74q", "us3", "76c", "ioVg")}
            }
        """.trimIndent()

        val misskeyApiClient = MisskeyApiClient(
            "W7Xw8F", "https://localhost", HttpClient(
                MockEngine(
                    checkAuth(
                        typeNote, HttpStatusCode.OK, headersOf("Content-Type", "application/json")
                    )
                )
            )
        )
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://localhost/test/C56WI"))
        assertEquals(json.decodeFromString<ApShowResponse.TypeNote>(typeNote), show)
    }
}

class ApTestE2E {

    val misskeyApiClient = MisskeyApiClient(
        System.getProperty("multim_misskey_token"), System.getProperty("multim_misskey_instance"),
        createHttpClient()
    )

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun show_showUserRequest_respondTypeUser() = runBlocking {
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://mstdn-dev.usbharu.dev/@testAdmin"))
        delay(1000)
        assertInstanceOf(ApShowResponse.TypeUser::class.java,show)
    }

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun show_showNoteRequest_respondTypeNote() = runBlocking {
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://mstdn-dev.usbharu.dev/@testAdmin/109739544444885718"))
        delay(1000)
        assertInstanceOf(ApShowResponse.TypeNote::class.java,show)
    }
}
