@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.usbharu.multim.misskey.v12.api

import MisskeyTestUtil.createFakeNote
import MisskeyTestUtil.createMockHttpClient
import MisskeyTestUtil.json
import com.github.michaelbull.result.*
import dev.usbharu.multim.api.createHttpClient
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*
import dev.usbharu.multim.model.SingleTokenAuth
import io.github.artsok.RepeatedIfExceptionsTest
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class NotesTest {

    @Test
    fun globalTimelineTest() = runTest {
        val notes = Notes(
            MisskeyApiClient(
                SingleTokenAuth("aaaa"),
                "",
                createMockHttpClient(
                    checkAuth = false,
                    content = json.encodeToString(expectNoteArray)
                )
            )
        )
        val globalTimeline =
            notes.globalTimeline(globalTimelineRequest = NotesGlobalTimelineRequest()).get()
        assertEquals(expectNoteArray, globalTimeline)
    }

    @Test
    fun hybridTimelineTest() = runTest {
        val notes = Notes(
            MisskeyApiClient(
                SingleTokenAuth("aaaa"),
                "",
                createMockHttpClient(content = json.encodeToString(expectNoteArray))
            )
        )
        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest()).get()
        assertEquals(expectNoteArray, hybridTimeline)
    }

    @Test
    fun localTimelineTest() = runTest {
        val notes = Notes(
            MisskeyApiClient(
                SingleTokenAuth("aaa"),
                "",
                createMockHttpClient(
                    checkAuth = false,
                    content = json.encodeToString(expectNoteArray)
                )
            )
        )
        val localTimeline = notes.localTimeline(NotesLocalTimelineRequest()).get()
        assertEquals(expectNoteArray, localTimeline)
    }

    @Test
    fun showTest() = runTest {
        val expectedNote = createFakeNote("mLyakn", "7j1VB0L", "Ymhq", "8V7QrxD6")
        val notes = Notes(
            MisskeyApiClient(
                SingleTokenAuth("aaa"),
                "",
                createMockHttpClient(
                    checkAuth = false,
                    content = json.encodeToString(expectedNote)
                )
            )
        )
        val note = notes.show(NotesShowRequest("mLvakn")).get()
        assertEquals(expectedNote, note)
    }

    @Test
    fun createTest() = runTest {
        val note = createFakeNote("aT9o", "o00672o", "W2kwto", "gold")
        val notes = Notes(
            MisskeyApiClient(
                SingleTokenAuth("aaa"),
                "",
                createMockHttpClient(content = json.encodeToString(NotesCreateResponse(note)))
            )
        )
        val create = notes.create(NotesCreateRequest(text = "gold")).get()
        assertEquals(note, create?.createdNote)
    }

    @Test
    fun deleteTest() = runTest {
        val notes =
            Notes(MisskeyApiClient(SingleTokenAuth("aaa"), "", createMockHttpClient(content = "")))
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

    val client = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        createHttpClient()
    )
    val notes = Notes(client)
    val drive = Drive(client)

    @Test
    fun globalTimeline() = runTest {
        val globalTimeline = notes.globalTimeline(NotesGlobalTimelineRequest()).get()
        println(globalTimeline)
    }

    @Test
    fun hybridTimeline() = runTest {
        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest()).get()
        println(hybridTimeline)
    }

    @Test
    fun localTimeline() = runTest {
        val localTimeline = notes.localTimeline(NotesLocalTimelineRequest()).get()
        println(localTimeline)
    }

    @Test
    fun show() = runTest {
        val show = notes.show(NotesShowRequest("9ack8wxw3c")).get()
        println(show)
    }

    @Test
    fun create() = runTest {
        val created = notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.HOME,
                text = "このノートはMultim のテストで作成されました。${this@NotesTestE2E::class} create Test"
            )
        ).get()
        println(created?.createdNote)
    }

    @Test
    fun createWithFile() = runTest {
        val encode: ByteArray = withContext(Dispatchers.IO) {
            NotesTestE2E::class.java.classLoader.getResourceAsStream("notes/create/files/note_with_file_test.jpg")!!
                .readBytes()
        }

        val driveFile = drive.Files().create(DriveFilesCreateRequest(file = encode))
        val createdNote = notes.create(
            NotesCreateRequest(
                text = "このノートはMultiMのテストで作成され、ファイル添付のテストで使用されます。",
                fileIds = setOf(driveFile.id)
            )
        ).get()

        assertEquals(driveFile, createdNote?.createdNote?.files?.firstOrNull())
    }

    @Test
    fun createWithPoll() = runTest {
        val poll = NotesCreateRequest.Poll(choices = setOf("a", "b", "c"))
        val create = notes.create(
            NotesCreateRequest(
                text = "このノートはMultiMのテストで作成され、投票付きノートの作成で使用されます。 ${this@NotesTestE2E::class} create note with poll",
                poll = poll
            )
        ).get()
        assertEquals(poll.choices.map { it }, create?.createdNote?.poll?.choices?.map { it.text });
    }

    @Test
    fun delete() = runTest {
        val create = notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.HOME,
                text = "このノートはMultim のテストで作成され、削除される予定です。 ${this@NotesTestE2E::class} delete Test"
            )
        )
        val deleteNote = when (create) {
            is Ok -> create.value.createdNote.id
            is Err -> fail(create.error.message, create.error._throwable)
        }
        notes.delete(NotesDeleteRequest(deleteNote))
        assertInstanceOf(Err::class.java, notes.show(NotesShowRequest(deleteNote)))
        //消せていたら失敗する

    }

    @Test
    fun featured() = runTest {
        val featured = notes.featured()
        println(featured)
    }

    @Test
    fun children() = runTest {
        val children = notes.children(NotesChildrenRequest("9ad7btwst8"))
        println(children)
    }

    @Test
    fun conversation() = runTest {
        val conversation = notes.conversation(NotesConversationRequest("9ad7btwst8"))
        println(conversation)
    }

    @Test
    fun state() = runTest {
        val state = notes.state(NotesStateRequest("9ad7btwst8"))
        println(state)
    }

    @Test
    fun favoritesCreate() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、お気に入り登録のテストで使用されます。 ${this@NotesTestE2E::class} favorites create test"))
                .get()
        create?.createdNote?.id?.let { NotesFavoritesCreateRequest(it) }
            ?.let { notes.Favorites().create(it) }
        assertTrue(create?.createdNote?.id?.let { NotesStateRequest(it) }
            ?.let { notes.state(it).get()?.isFavorited } == true)
//todo ネストがやばいことになってるのでnullが返ってきた時点で失敗にする
    }

    @Test
    fun favoritesDelete() = runTest {
        val result =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、お気に入り削除のテストで使用されます。 ${this@NotesTestE2E::class} favorites delete test"))
        val create =
            when (result) {
                is Ok -> result.value
                is Err -> fail(result.error.message, result.error._throwable)
            }
        notes.Favorites().create(NotesFavoritesCreateRequest(create.createdNote.id))
        assertTrue(notes.state(NotesStateRequest(create.createdNote.id)).get()?.isFavorited == true)
        notes.Favorites().delete(NotesFavoritesDeleteRequest(create.createdNote.id))
        assertFalse(
            notes.state(NotesStateRequest(create.createdNote.id)).get()?.isFavorited == true
        )
    }

    @Test
    fun mentions() = runTest {
        val mentions = notes.mentions(NotesMentionsRequest())
        println(mentions)
    }

    @Test
    fun reactions() = runTest {
        val reactions = notes.reactions(NotesReactionsRequest("9ack8wxw3c"))
        println(reactions)
    }

    @Test
    fun renotes() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、リノートのテストで使用されます。 ${this@NotesTestE2E::class}  renotes test"))
                .get()
        val notesCreateResponse =
            notes.create(NotesCreateRequest(renoteId = create?.createdNote?.id)).get()
        val renotes = create?.createdNote?.id?.let { NotesRenoteRequest(it) }
            ?.let { notes.renotes(it) }?.get()
        assertEquals(listOf(notesCreateResponse?.createdNote), renotes)
        println(renotes)
    }

    @Test
    fun replies() = runTest {
        val root =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test"))
                .get()
        val reply1 = notes.create(
            NotesCreateRequest(
                text = "返信1 このノートはMultimのテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test",
                replyId = root?.createdNote?.id
            )
        ).get()
        val reply2 = notes.create(
            NotesCreateRequest(
                text = "返信2 このノートはMultimのテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test",
                replyId = root?.createdNote?.id
            )
        ).get()
        val replies = root?.createdNote?.id?.let { NotesRepliesRequest(it) }
            ?.let { notes.replies(it) }?.get()
        assertEquals(
            listOf(reply1?.createdNote, reply2?.createdNote).sortedBy { note -> note?.id },
            replies?.sortedBy { note -> note.id })
    }

    @Test
    fun searchByTag() = runTest {
        val tag = UUID.randomUUID().toString()
        val tagedNote =
            notes.create(NotesCreateRequest(text = "#$tag このノートはMultimのテストで作成され、タグ検索のテストで使用されます。 ${this@NotesTestE2E::class} search by tag test"))
                .get()
        val searchByTag = notes.searchByTag(NotesSearchByTagRequest(tag)).get()
        org.assertj.core.api.Assertions.assertThat(searchByTag)?.isNotEmpty
        assertTrue(searchByTag?.contains(tagedNote?.createdNote) == true)
        println(searchByTag)
    }

    @Test
    fun threadMutingCreate() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultimのテストで作成され、スレッドミュートのテストで使用されます。 ${this@NotesTestE2E::class} thread mute create test"))
                .get()
        assertFalse(create?.createdNote?.id?.let { NotesStateRequest(it) }
            ?.let { notes.state(it).get()?.isMutedThread } == true)
        create?.createdNote?.id?.let { NotesThreadMutingCreateRequest(it) }
            ?.let { notes.ThreadMuting().create(it) }
        assertTrue(create?.createdNote?.id?.let { NotesStateRequest(it) }
            ?.let { notes.state(it).get()?.isMutedThread } == true)
    }

    @Test
    fun threadMutingDelete() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultimのテストで作成され、スレッドミュート解除のテストで使用されます。 ${this@NotesTestE2E::class} thread mute delete test"))
                .get()
        create?.createdNote?.id?.let { NotesStateRequest(it) }?.let {
            notes.state(it).get()?.isMutedThread?.let {
                assertFalse(
                    it
                )
            }
        }
        create?.createdNote?.id?.let { NotesThreadMutingCreateRequest(it) }
            ?.let { notes.ThreadMuting().create(it) }
        assertTrue(create?.createdNote?.id?.let { NotesStateRequest(it) }
            ?.let { notes.state(it).get()?.isMutedThread } == true)
        create?.createdNote?.id?.let { NotesThreadMutingDeleteRequest(it) }
            ?.let { notes.ThreadMuting().delete(it) }
        assertFalse(create?.createdNote?.id?.let { NotesStateRequest(it) }
            ?.let { notes.state(it).get()?.isMutedThread } == true)
    }

    @Test
    fun timeline() = runTest {
        val timeline = notes.timeline(NotesTimelineRequest())
        println(timeline)
    }

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun unrenote() = runBlocking {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultimのテストで作成され、リノート取り消しのテストで使用されます。 ${this@NotesTestE2E::class} unrenote test"))
                .get()
        delay(1000)
        val renoted = notes.create(NotesCreateRequest(renoteId = create?.createdNote?.id)).get()
        delay(1000)
        create?.createdNote?.id?.let { NotesUnrenoteRequest(it) }?.let { notes.unrenote(it) }
        delay(1000)
        assertThrows<ClientRequestException> {
            renoted?.createdNote?.id?.let {
                NotesShowRequest(
                    it
                )
            }?.let { notes.show(it) }
        }

    }

    @Test
    fun userListTimeline() = runTest {
        val userListTimeline = notes.userListTimeline(NotesUserListTimelineRequest("9ady10e6z5"))
        println(userListTimeline)

    }

    @Test
    fun watchingCreate() = runTest {
        val noteId = "9bk3hn1qd0"
        val state = notes.state(NotesStateRequest(noteId)).get()
        if (state?.isWatching == true) {
            notes.Watching().delete(NotesWatchingDeleteRequest(noteId))
        }
        notes.Watching().create(NotesWatchingCreateRequest(noteId)).get()
        assertTrue(notes.state(NotesStateRequest(noteId)).get()?.isWatching == true)
    }

    @Test
    fun watchingDelete() = runTest {
        val noteId = "9bk3hn1qd0"
        val state = notes.state(NotesStateRequest(noteId)).get()
        if (state?.isWatching == false) {
            notes.Watching().create(NotesWatchingCreateRequest(noteId))
        }
        notes.Watching().delete(NotesWatchingDeleteRequest(noteId))
        assertFalse(notes.state(NotesStateRequest(noteId)).get()?.isWatching == true)
    }
}
