@file:OptIn(ExperimentalCoroutinesApi::class)
@file:Suppress("SpellCheckingInspection")

package dev.usbharu.multim.misskey.v12.api

import MisskeyTestUtil.createFakeNote
import MisskeyTestUtil.createMockHttpClient
import MisskeyTestUtil.json
import com.github.michaelbull.result.*
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
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
import org.junit.jupiter.api.Disabled
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
            notes.globalTimeline(globalTimelineRequest = NotesGlobalTimelineRequest()).failOnError()
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
        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest()).failOnError()
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
        val localTimeline = notes.localTimeline(NotesLocalTimelineRequest()).failOnError()
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
        val note = notes.show(NotesShowRequest("mLvakn")).failOnError()
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
        val create = notes.create(NotesCreateRequest(text = "gold")).failOnError()
        assertEquals(note, create.createdNote)
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


    private val expectNoteArray = listOf(
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

    private val client = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskey_token")),
        System.getProperty("multim_misskey_instance"),
        MultiM.httpClientWithJson.config {}
    )
    private val notes = Notes(client)
    private val drive = Drive(client)

    @Test
    fun globalTimeline() = runTest {
        val globalTimeline = notes.globalTimeline(NotesGlobalTimelineRequest()).failOnError()
        println(globalTimeline)
    }

    @Test
    fun hybridTimeline() = runTest {
        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest()).failOnError()
        println(hybridTimeline)
    }

    @Test
    fun localTimeline() = runTest {
        val localTimeline = notes.localTimeline(NotesLocalTimelineRequest()).failOnError()
        println(localTimeline)
    }

    @Test
    fun show() = runTest {
        val show =
            notes.show(NotesShowRequest(notes.notes(NotesNotesRequest()).failOnError().first().id))
                .failOnError()
        println(show)
    }

    @Test
    fun create() = runTest {
        val created = notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.HOME,
                text = "このノートはMultim のテストで作成されました。${this@NotesTestE2E::class} create Test"
            )
        ).failOnError()
        println(created.createdNote)
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
        ).failOnError()

        assertEquals(driveFile, createdNote.createdNote.files?.firstOrNull())
    }

    @Test
    fun createWithPoll() = runTest {
        val poll = NotesCreateRequest.Poll(choices = setOf("a", "b", "c"))
        val create = notes.create(
            NotesCreateRequest(
                text = "このノートはMultiMのテストで作成され、投票付きノートの作成で使用されます。 ${this@NotesTestE2E::class} create note with poll",
                poll = poll
            )
        ).failOnError()
        assertEquals(poll.choices.map { it }, create.createdNote.poll?.choices?.map { it.text })
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
        val featured = notes.featured().failOnError()
        println(featured)
    }

    @Test
    fun children() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultiMのテストで作成され、子ノート取得のテストで使用されます。${this@NotesTestE2E::class} children"))
        val id = create.failOnError().createdNote.id
        val children = notes.children(NotesChildrenRequest(id)).failOnError()
        println(children)
    }

    @Test
    fun conversation() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultiMのテストで作成され、関連ノート取得のテストで使用されます。 ${this@NotesTestE2E::class} conversation"))
                .failOnError()
        val conversation =
            notes.conversation(NotesConversationRequest(create.createdNote.id)).failOnError()
        println(conversation)
    }

    @Test
    fun state() = runTest {
        val create = notes.create(NotesCreateRequest(text="このノートはMultiMのテストで作成され、ノートの状態を取得するテストで使用されます。 ${this@NotesTestE2E::class} state")).failOnError()
        val state = notes.state(NotesStateRequest(create.createdNote.id)).failOnError()
        println(state)
    }

    @Test
    fun favoritesCreate() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、お気に入り登録のテストで使用されます。 ${this@NotesTestE2E::class} favorites create test"))
                .failOnError()
        NotesFavoritesCreateRequest(create.createdNote.id)
            .let { notes.Favorites().create(it) }
        assertTrue(
            NotesStateRequest(create.createdNote.id)
                .let { notes.state(it).failOnError().isFavorited })
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
        assertTrue(notes.state(NotesStateRequest(create.createdNote.id)).failOnError().isFavorited)
        notes.Favorites().delete(NotesFavoritesDeleteRequest(create.createdNote.id))
        assertFalse(
            notes.state(NotesStateRequest(create.createdNote.id)).failOnError().isFavorited
        )
    }

    @Test
    fun mentions() = runTest {
        val mentions = notes.mentions(NotesMentionsRequest()).failOnError()
        println(mentions)
    }

    @Test
    fun reactions() = runTest {
        val create = notes.create(NotesCreateRequest(text = "このノートはMultiMのテストで作成され、リアクション取得のテストで使用されます。 ${this@NotesTestE2E::class} reactions")).failOnError()
        val reactions = notes.reactions(NotesReactionsRequest(create.createdNote.id))
        println(reactions)
    }

    @Test
    fun renotes() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、リノートのテストで使用されます。 ${this@NotesTestE2E::class}  renotes test"))
                .failOnError()
        val notesCreateResponse =
            notes.create(NotesCreateRequest(renoteId = create.createdNote.id)).failOnError()
        val renotes = NotesRenoteRequest(create.createdNote.id)
            .let { notes.renotes(it) }.failOnError()
        assertEquals(listOf(notesCreateResponse.createdNote), renotes)
        println(renotes)
    }

    @Test
    fun replies() = runTest {
        val root =
            notes.create(NotesCreateRequest(text = "このノートはMultim のテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test"))
                .failOnError()
        val reply1 = notes.create(
            NotesCreateRequest(
                text = "返信1 このノートはMultimのテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test",
                replyId = root.createdNote.id
            )
        ).failOnError()
        val reply2 = notes.create(
            NotesCreateRequest(
                text = "返信2 このノートはMultimのテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test",
                replyId = root.createdNote.id
            )
        ).failOnError()
        val replies = root.createdNote.id.let { NotesRepliesRequest(it) }
            .let { notes.replies(it) }.failOnError()
        assertEquals(
            listOf(reply1.createdNote, reply2.createdNote).sortedBy { note -> note.id },
            replies.sortedBy { note -> note.id })
    }

    @Test
    fun searchByTag() = runTest {
        val tag = UUID.randomUUID().toString()
        val tagedNote =
            notes.create(NotesCreateRequest(text = "#$tag このノートはMultimのテストで作成され、タグ検索のテストで使用されます。 ${this@NotesTestE2E::class} search by tag test"))
                .failOnError()
        val searchByTag = notes.searchByTag(NotesSearchByTagRequest(tag)).failOnError()
        org.assertj.core.api.Assertions.assertThat(searchByTag)?.isNotEmpty
        assertTrue(searchByTag.contains(tagedNote.createdNote))
        println(searchByTag)
    }

    @Test
    fun threadMutingCreate() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultimのテストで作成され、スレッドミュートのテストで使用されます。 ${this@NotesTestE2E::class} thread mute create test"))
                .failOnError()
        assertFalse(
            NotesStateRequest(create.createdNote.id)
                .let { notes.state(it).failOnError().isMutedThread })
        NotesThreadMutingCreateRequest(create.createdNote.id)
            .let { notes.ThreadMuting().create(it) }
        assertTrue(
            NotesStateRequest(create.createdNote.id)
                .let { notes.state(it).failOnError().isMutedThread })
    }

    @Test
    fun threadMutingDelete() = runTest {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultimのテストで作成され、スレッドミュート解除のテストで使用されます。 ${this@NotesTestE2E::class} thread mute delete test"))
                .failOnError()
        assertFalse(
            notes.state(NotesStateRequest(create.createdNote.id)).failOnError().isMutedThread
        )
        NotesThreadMutingCreateRequest(create.createdNote.id)
            .let { notes.ThreadMuting().create(it) }
        assertTrue(
            NotesStateRequest(create.createdNote.id)
                .let { notes.state(it).failOnError().isMutedThread })
        NotesThreadMutingDeleteRequest(create.createdNote.id)
            .let { notes.ThreadMuting().delete(it) }
        assertFalse(
            NotesStateRequest(create.createdNote.id)
                .let { notes.state(it).failOnError().isMutedThread })
    }

    @Test
    fun timeline() = runTest {
        val timeline = notes.timeline(NotesTimelineRequest()).failOnError()
        println(timeline)
    }

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun unrenote() = runBlocking {
        val create =
            notes.create(NotesCreateRequest(text = "このノートはMultimのテストで作成され、リノート取り消しのテストで使用されます。 ${this@NotesTestE2E::class} unrenote test"))
                .failOnError()
        delay(1000)
        val renoted =
            notes.create(NotesCreateRequest(renoteId = create.createdNote.id)).failOnError()
        delay(1000)
        NotesUnrenoteRequest(create.createdNote.id).let { notes.unrenote(it) }
        delay(1000)
        assertThrows<ClientRequestException> {
            NotesShowRequest(
                renoted.createdNote.id
            ).let { notes.show(it) }
        }

    }

    @Test
    @Disabled("リスト操作系のAPIが未実装なため無効化")
    // TODO: リスト操作系のAPI追加後、リストを取得してテストするように
    fun userListTimeline() = runTest {

        val userListTimeline = notes.userListTimeline(NotesUserListTimelineRequest("9ady10e6z5"))
        println(userListTimeline)

    }

    @Test
    @Disabled("自分以外ということが確定しているノートを取得できない為無効化")
    // TODO: 自分以外のノートを取得できるようになってからテストを追加
    fun watchingCreate() = runTest {

        val noteId = "9bk3hn1qd0"
        val state = notes.state(NotesStateRequest(noteId)).failOnError()
        if (state.isWatching) {
            notes.Watching().delete(NotesWatchingDeleteRequest(noteId))
        }
        notes.Watching().create(NotesWatchingCreateRequest(noteId)).failOnError()
        assertTrue(notes.state(NotesStateRequest(noteId)).failOnError().isWatching)
    }

    @Test
    @Disabled("自分以外ということが確定しているノートを取得できない為無効化")
    fun watchingDelete() = runTest {
        val noteId = "9bk3hn1qd0"
        val state = notes.state(NotesStateRequest(noteId)).failOnError()
        if (!state.isWatching) {
            notes.Watching().create(NotesWatchingCreateRequest(noteId))
        }
        notes.Watching().delete(NotesWatchingDeleteRequest(noteId))
        assertFalse(notes.state(NotesStateRequest(noteId)).failOnError().isWatching)
    }
}
