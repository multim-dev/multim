@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package dev.usbharu.multim.misskey.v13.api

import MisskeyTestUtil.createFakeNoteToString
import MisskeyTestUtil.createMockHttpClient
import MisskeyTestUtil.json
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.ApShowRequest
import dev.usbharu.multim.misskey.v13.model.ApShowResponse
import dev.usbharu.multim.misskey.v13.model.components.Note
import dev.usbharu.multim.misskey.v13.model.components.UserDetailedNotMe
import dev.usbharu.multim.misskey.v13.model.components.UserLite
import dev.usbharu.multim.model.SingleTokenAuth
import io.ktor.client.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ApTest {
    val baseUrl = "https://localhost/"

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


    @Suppress("LongMethod")
    @Test
    fun show_userUrl_returnUser() = runTest {
        val userDetailedNotMe = UserDetailedNotMe(
            id = "Xqxv",
            name = "fakeUser",
            username = "fakeUserName",
            host = "example.com",
            avatarUrl = "https://example.com",
            avatarBlurhash = "jvwxIWI",
            avatarColor = null,
            isAdmin = false,
            isModerator = false,
            isBot = false,
            isCat = false,
            emojis = emptyMap(),
            onlineStatus = "unknown",
            url = "https://example.com",
            uri = "https://example.com",
            createdAt = Instant.parse("2023-03-07T20:33:58.868Z"),
            updatedAt = Instant.parse("2023-03-07T20:33:58.868Z"),
            lastFetchedAt = Instant.parse("2023-03-07T20:33:58.868Z"),
            bannerUrl = null,
            bannerBlurhash = null,
            bannerColor = null,
            isLocked = false,
            isSilenced = false,
            isSuspended = false,
            description = "fake user deskription",
            location = null,
            birthday = null,
            lang = null,
            fields = emptyList(),
            followersCount = 1,
            followingCount = 1,
            notesCount = 10,
            pinnedNoteIds = emptyList(),
            pinnedNotes = emptyList(),
            pinnedPageId = null,
            pinnedPage = null,
            publicReactions = false,
            twoFactorEnabled = false,
            usePasswordLessLogin = false,
            securityKeys = false,
            isFollowing = true,
            isFollowed = true,
            hasPendingFollowRequestFromYou = false,
            hasPendingFollowRequestToYou = false,
            isBlocking = false,
            isBlocked = false,
            isMuted = false
        )
        val typeUser = ApShowResponse.TypeUser(userDetailedNotMe)

        val apShowResponse = Ap(
            apiClient(
                createMockHttpClient(
                    typeUser,
                    false,
                    baseUrl + "api/ap/show", ApShowResponse.serializer()
                )
            )
        ).show(ApShowRequest("https://example.com")).failOnError()
        assertEquals(typeUser, apShowResponse)
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

    @Test
    fun show_noteUrl_returnNote() = runTest {
        val note = Note(
            id = "rKiw2x4",
            createdAt = Instant.parse("2023-03-07T20:33:58.868Z"),
            text = "fake note",
            cw = null,
            userId = "A7pFuM",
            user = UserLite(
                id = "A7pFuM",
                name = "fakeUser",
                username = "fakeUsername",
                host = "example.com",
                avatarUrl = "https://exapmple.com",
                avatarBlurhash = "CpIBR1B",
                avatarColor = null,
                isAdmin = false,
                isModerator = false,
                isBot = false,
                isCat = false,
                emojis = emptyMap(),
                onlineStatus = "online"
            ),
            replyId = null,
            renoteId = null,
            reply = null,
            renote = null,
            isHidden = false,
            visibility = "public",
            mentions = emptyList(),
            visibleUserIds = emptyList(),
            fileIds = emptyList(),
            files = emptyList(),
            tags = emptyList(),
            poll = null,
            channelId = null,
            channel = null,
            localOnly = false,
            emojis = emptyMap(),
            reactions = emptyMap(),
            renoteCount = 0, repliesCount = 0, uri = "https://example.com/asjfl"
        )
        val typeNote = ApShowResponse.TypeNote(note)
        val apShowResponse =
            Ap(
                apiClient(
                    createMockHttpClient(
                        typeNote,
                        true,
                        baseUrl + "api/ap/show",
                        ApShowResponse.serializer()
                    )
                )
            ).show(ApShowRequest("https://example.com/test/afasfa")).failOnError()

        assertEquals(typeNote, apShowResponse)
    }

    private fun apiClient(httpClient: HttpClient): MisskeyApiClient {
        return MisskeyApiClient(SingleTokenAuth("cdgj2h71"), baseUrl, httpClient)
    }
}
