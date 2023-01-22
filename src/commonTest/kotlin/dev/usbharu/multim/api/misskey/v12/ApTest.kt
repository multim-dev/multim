@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.TestUtil.checkAuth
import dev.usbharu.multim.api.common.TestUtil.createFakeNoteToString
import dev.usbharu.multim.api.common.TestUtil.json
import dev.usbharu.multim.api.common.createHttpClient
import dev.usbharu.multim.model.misskey.v12.ApShowRequest
import dev.usbharu.multim.secret.BuildKonfig
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlin.test.Test
import kotlin.test.assertEquals


class ApTest {

    val misskeyApiClient = MisskeyApiClient(
        BuildKonfig.token, "https://misskey.usbharu.dev/",
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
        assertEquals(json.decodeFromString(typeUser), show)
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
        assertEquals(json.decodeFromString(typeNote), show)
    }
}
