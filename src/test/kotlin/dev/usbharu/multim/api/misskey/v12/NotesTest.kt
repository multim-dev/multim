package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.TestUtil
import dev.usbharu.multim.api.common.TestUtil.createFakeNote
import dev.usbharu.multim.api.common.TestUtil.json
import dev.usbharu.multim.api.common.createHttpClient
import dev.usbharu.multim.model.misskey.v12.*
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalCoroutinesApi::class)
class NotesTest {

    @Test
    fun globalTimelineTest() = runTest {
        val notes = Notes(
            MisskeyApiClient(
                "aaaa",
                "",
                TestUtil.createMockHttpClient(
                    checkAuth = false,
                    content = json.encodeToString(expectNoteArray)
                )
            )
        )
        val globalTimeline =
            notes.globalTimeline(globalTimelineRequest = NotesGlobalTimelineRequest())
        assertEquals(expectNoteArray, globalTimeline)
    }

    @Test
    fun hybridTimelineTest() = runTest {
        val notes = Notes(
            MisskeyApiClient(
                "aaaa",
                "",
                TestUtil.createMockHttpClient(content = json.encodeToString(expectNoteArray))
            )
        )
        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest())
        assertEquals(expectNoteArray, hybridTimeline)
    }

    @Test
    fun localTimelineTest() = runTest {
        val notes = Notes(
            MisskeyApiClient(
                "aaa",
                "",
                TestUtil.createMockHttpClient(
                    checkAuth = false,
                    content = json.encodeToString(expectNoteArray)
                )
            )
        )
        val localTimeline = notes.localTimeline(NotesLocalTimelineRequest())
        assertEquals(expectNoteArray, localTimeline)
    }

    @Test
    fun showTest() = runTest {
        val expectedNote = createFakeNote("mLyakn", "7j1VB0L", "Ymhq", "8V7QrxD6")
        val notes = Notes(
            MisskeyApiClient(
                "aaa",
                "",
                TestUtil.createMockHttpClient(
                    checkAuth = false,
                    content = json.encodeToString(expectedNote)
                )
            )
        )
        val note = notes.show(NotesShowRequest("mLvakn"))
        assertEquals(expectedNote, note)
    }

    @Test
    fun createTest() = runTest {
        val note = createFakeNote("aT9o", "o00672o", "W2kwto", "gold")
        val notes = Notes(
            MisskeyApiClient(
                "aaa",
                "",
                TestUtil.createMockHttpClient(content = json.encodeToString(NotesCreateResponse(note)))
            )
        )
        val create = notes.create(NotesCreateRequest(text = "gold"))
        assertEquals(note, create.createdNote)
    }

    @Test
    fun deleteTest() = runTest {
        val notes = Notes(MisskeyApiClient("aaa", "", TestUtil.createMockHttpClient(content = "")))
        notes.delete(NotesDeleteRequest("Rw4g2CH"))
    }

    @Test
    fun ldttest() {
        val parse = Instant.parse("2023-01-17T06:58:08.000Z")
        println(parse)
    }


    val expectNoteArray = listOf(
        createFakeNote(
            "4wT4lSP",
            "p9hdK",
            "oYDd",
            "Lorem ipsum dolor sit amet illum lorem ut magna elit dolore amet consectetuer est odio sadipscing lobortis stet sea. Consetetur consetetur stet ut aliquyam aliquyam ut nam congue gubergren gubergren kasd lorem tempor amet kasd consetetur ipsum. Dolor iriure sit dolore erat sit diam adipiscing odio ipsum ullamcorper erat et. Duis gubergren est."
        ),
        createFakeNote(
            "bFe7t9M",
            "v9C",
            "T470fU",
            "Lorem ipsum dolor sit amet at amet rebum vel dolor amet erat invidunt sed duo sit facilisis magna. Sed gubergren et sed amet minim diam tempor diam facilisi amet ipsum justo eirmod gubergren aliquyam."
        ),
        createFakeNote(
            "0P5Kd4LD",
            "t9KO12Q",
            "kcK",
            "Lorem ipsum dolor sit amet et eirmod justo et ut sadipscing tempor labore amet hendrerit vero. Tincidunt lobortis esse kasd in amet erat aliquyam nonumy takimata adipiscing sit at tation stet ex duo justo tempor."
        ),
        createFakeNote(
            "dy3055V7",
            "e1swe9O7",
            "9AtX",
            "Lorem ipsum dolor sit amet et. Dolores dolore vero at tempor wisi ipsum delenit tempor gubergren et dignissim."
        ),
        createFakeNote(
            "vEv9",
            "4xTvOwh",
            "T2ES2jYV",
            "Lorem ipsum dolor sit amet duis feugait erat sanctus luptatum consetetur nonumy sed at ea."
        ),
        createFakeNote(
            "427AW",
            "638oy",
            "JEAgxoD",
            "Lorem ipsum dolor sit amet nam rebum aliquyam nonumy lorem eos clita. Diam ea zzril at eos dolor gubergren et placerat et gubergren lorem eirmod amet sea dolor."
        ),
        createFakeNote(
            "tfkV4j",
            "2bu",
            "UxN1",
            "Lorem ipsum dolor sit amet et sit accusam gubergren dolor amet magna vel at vero. Iusto gubergren ut euismod feugiat et laoreet aliquyam autem."
        ),
        createFakeNote(
            "ty67",
            "5o9JgtBR",
            "c99Zv",
            "Lorem ipsum dolor sit amet sanctus dolore sit. Augue dolor et ipsum ipsum clita labore diam sanctus no ipsum est sed ex elitr ut."
        ),
        createFakeNote(
            "JOm2U2ci",
            "198ZRz8",
            "P29WMF9",
            "Lorem ipsum dolor sit amet nonumy elitr ipsum facilisis vel euismod gubergren consequat consetetur volutpat vel labore. Dolores dolor stet tempor."
        ),
        createFakeNote(
            "2Nv8RU7W",
            "6yv5",
            "dHT06",
            "Lorem ipsum dolor sit amet at ipsum vero. Et et eirmod tempor diam et sit duo sed dolores."
        )
    )
}

class NotesTestE2E {

    val notes = Notes(
        MisskeyApiClient(
            System.getProperty("multim_misskey_token"),
            System.getProperty("multim_misskey_instance"),
            createHttpClient()
        )
    )

    @Test
    fun globalTimeline() = runTest {
        val globalTimeline = notes.globalTimeline(NotesGlobalTimelineRequest())
        println(globalTimeline)
    }

    @Test
    fun hybridTimeline() = runTest {
        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest())
        println(hybridTimeline)
    }

    @Test
    fun localTimeline() = runTest {
        val localTimeline = notes.localTimeline(NotesLocalTimelineRequest())
        println(localTimeline)
    }

    @Test
    fun show() = runTest {
        val show = notes.show(NotesShowRequest("9ack8wxw3c"))
        println(show)
    }

    @Test
    fun create() = runTest {
        val created = notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.HOME,
                text = "このノートはMultim のテストで作成されました。${this@NotesTestE2E::class} create Test"
            )
        )
        println(created.createdNote)
    }

    @Test
    fun delete() = runTest {
        val deleteNote = notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.HOME,
                text = "このノートはMultim のテストで作成され、削除される予定です。 ${this@NotesTestE2E::class} delete Test"
            )
        )
        notes.delete(NotesDeleteRequest(deleteNote.createdNote.id))
        assertThrows<ClientRequestException> {
            notes.show(NotesShowRequest(deleteNote.createdNote.id)) //消せていたら失敗する
        }
        }

        @Test
        fun featured() = runTest {
            val featured = notes.featured()
            println(featured)
        }


    }
