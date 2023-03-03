@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package dev.usbharu.multim.misskey.v12.api

import MisskeyTestUtil.createFakeNoteToString
import MisskeyTestUtil.createMockHttpClient
import MisskeyTestUtil.json
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.ApShowRequest
import dev.usbharu.multim.misskey.v12.model.ApShowResponse
import dev.usbharu.multim.model.SingleTokenAuth
import io.github.artsok.RepeatedIfExceptionsTest
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
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        MultiM.httpClientWithJson.config {}
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
            SingleTokenAuth("aaaaaaaa"), "https://localhost", createMockHttpClient(typeUser)
        )
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://localhost/test/IN7OFhht"))
            .failOnError()
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
            SingleTokenAuth("W7Xw8F"), "https://localhost", createMockHttpClient(typeNote)
        )
        val show =
            Ap(misskeyApiClient).show(ApShowRequest("https://localhost/test/C56WI")).failOnError()
        assertEquals(json.decodeFromString<ApShowResponse.TypeNote>(typeNote), show)
    }
}

class ApTestE2E {

    val misskeyApiClient = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        MultiM.httpClientWithJson.config {}
    )

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun show_showUserRequest_respondTypeUser() = runBlocking {
        val show =
            Ap(misskeyApiClient).show(ApShowRequest("https://mstdn-dev.usbharu.dev/@testAdmin"))
                .failOnError()
        delay(1000)
        assertInstanceOf(ApShowResponse.TypeUser::class.java, show)
    }

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun show_showNoteRequest_respondTypeNote() = runBlocking {
        val show =
            Ap(misskeyApiClient).show(ApShowRequest("https://mstdn-dev.usbharu.dev/@testAdmin/109739544444885718"))
                .failOnError()
        delay(1000)
        assertInstanceOf(ApShowResponse.TypeNote::class.java, show)
    }
}
