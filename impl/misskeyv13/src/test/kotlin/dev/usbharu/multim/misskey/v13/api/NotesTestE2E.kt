package dev.usbharu.multim.misskey.v13.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.DriveFilesCreateRequest
import dev.usbharu.multim.misskey.v13.model.NotesChildrenRequest
import dev.usbharu.multim.misskey.v13.model.NotesConversationRequest
import dev.usbharu.multim.misskey.v13.model.NotesCreateRequest
import dev.usbharu.multim.misskey.v13.model.NotesDeleteRequest
import dev.usbharu.multim.misskey.v13.model.NotesFavoritesCreateRequest
import dev.usbharu.multim.misskey.v13.model.NotesFavoritesDeleteRequest
import dev.usbharu.multim.misskey.v13.model.NotesGlobalTimelineRequest
import dev.usbharu.multim.misskey.v13.model.NotesHybridTimelineRequest
import dev.usbharu.multim.misskey.v13.model.NotesLocalTimelineRequest
import dev.usbharu.multim.misskey.v13.model.NotesMentionsRequest
import dev.usbharu.multim.misskey.v13.model.NotesNotesRequest
import dev.usbharu.multim.misskey.v13.model.NotesReactionsRequest
import dev.usbharu.multim.misskey.v13.model.NotesRenoteRequest
import dev.usbharu.multim.misskey.v13.model.NotesRepliesRequest
import dev.usbharu.multim.misskey.v13.model.NotesSearchByTagRequest
import dev.usbharu.multim.misskey.v13.model.NotesShowRequest
import dev.usbharu.multim.misskey.v13.model.NotesStateRequest
import dev.usbharu.multim.misskey.v13.model.NotesThreadMutingCreateRequest
import dev.usbharu.multim.misskey.v13.model.NotesThreadMutingDeleteRequest
import dev.usbharu.multim.misskey.v13.model.NotesTimelineRequest
import dev.usbharu.multim.misskey.v13.model.NotesUnrenoteRequest
import dev.usbharu.multim.misskey.v13.model.NotesUserListTimelineRequest
import dev.usbharu.multim.misskey.v13.model.NotesWatchingCreateRequest
import dev.usbharu.multim.misskey.v13.model.NotesWatchingDeleteRequest
import dev.usbharu.multim.model.SingleTokenAuth
import io.github.artsok.RepeatedIfExceptionsTest
import io.ktor.client.plugins.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class NotesTestE2E {

    private val client = MisskeyApiClient(SingleTokenAuth(System.getProperty("multim_misskeyv13_token")),
        System.getProperty("multim_misskeyv13_instance"),
        MultiM.httpClientWithJson.config {})
    private val notes = Notes(client)
    private val drive = Drive(client)

    @Test
    fun globalTimeline() = runTest {
        notes.globalTimeline(NotesGlobalTimelineRequest()).failOnError()
    }

    @Test
    fun hybridTimeline() = runTest {
        notes.hybridTimeline(NotesHybridTimelineRequest()).failOnError()
    }

    @Test
    fun localTimeline() = runTest {
        notes.localTimeline(NotesLocalTimelineRequest()).failOnError()
    }

    @Test
    fun show() = runTest {
        notes.show(NotesShowRequest(notes.notes(NotesNotesRequest()).failOnError().first().id)).failOnError()

    }

    @Test
    fun create() = runTest {
        notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.HOME,
                text = "このノートはMultim のテストで作成されました。${this@NotesTestE2E::class} create Test"
            )
        ).failOnError()
    }

    @Test
    fun createFollowOnly() = runTest {
        val notesCreateResponse = notes.create(
            NotesCreateRequest(
                visibility = NotesCreateRequest.Visibility.FOLLOWERS,
                text = "このノートはMultiMのテストで作成され、公開範囲 フォロワーのみ のテストに使用されます。" +
                        " ${this@NotesTestE2E::class} create note with followers only"
            )
        ).failOnError()
        Assertions.assertEquals("followers", notesCreateResponse.createdNote.visibility)
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
                text = "このノートはMultiMのテストで作成され、ファイル添付のテストで使用されます。", fileIds = setOf(driveFile.id)
            )
        ).failOnError()

        Assertions.assertEquals(driveFile, createdNote.createdNote.files?.firstOrNull())
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
        Assertions.assertEquals(poll.choices.map { it }, create.createdNote.poll?.choices?.map { it.text })
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
            is Err -> Assertions.fail(create.error.message, create.error._throwable)
        }
        notes.delete(NotesDeleteRequest(deleteNote))
        Assertions.assertInstanceOf(Err::class.java, notes.show(NotesShowRequest(deleteNote)))
        //消せていたら失敗する

    }

    @Test
    fun featured() = runTest {
        notes.featured().failOnError()
    }

    @Test
    fun children() = runTest {
        val create = notes.create(
            NotesCreateRequest(
                text = "このノートはMultiMのテストで作成され、"
                        + "子ノート取得のテストで使用されます。${this@NotesTestE2E::class} children",
            )
        )
        val id = create.failOnError().createdNote.id
        notes.children(NotesChildrenRequest(id)).failOnError()
    }

    @Test
    fun conversation() = runTest {
        val create = notes.create(
            NotesCreateRequest(
                text = "このノートはMultiMのテストで作成され、" +
                        "関連ノート取得のテストで使用されます。 ${this@NotesTestE2E::class} conversation"
            )
        ).failOnError()
        notes.conversation(NotesConversationRequest(create.createdNote.id)).failOnError()
    }

    @Test
    fun state() = runTest {
        val create = notes.create(
            NotesCreateRequest(
                text = "このノートはMultiMのテストで作成され、" +
                        "ノートの状態を取得するテストで使用されます。 ${this@NotesTestE2E::class} state"
            )
        ).failOnError()
        notes.state(NotesStateRequest(create.createdNote.id)).failOnError()
    }

    @Test
    fun favoritesCreate() = runTest {
        val create = notes.create(
            NotesCreateRequest(
                text = "このノートはMultim のテストで作成され、" +
                        "お気に入り登録のテストで使用されます。 ${this@NotesTestE2E::class} favorites create test"
            )
        ).failOnError()
        NotesFavoritesCreateRequest(create.createdNote.id).let { notes.Favorites().create(it) }
        Assertions.assertTrue(NotesStateRequest(create.createdNote.id).let {
            notes.state(it).failOnError().isFavorited
        })
//todo ネストがやばいことになってるのでnullが返ってきた時点で失敗にする
    }

    @Test
    fun favoritesDelete() = runTest {
        val result =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultim のテストで作成され、" +
                            "お気に入り削除のテストで使用されます。 ${this@NotesTestE2E::class} favorites delete test"
                )
            )
        val create = when (result) {
            is Ok -> result.value
            is Err -> Assertions.fail(result.error.message, result.error._throwable)
        }
        notes.Favorites().create(NotesFavoritesCreateRequest(create.createdNote.id))
        Assertions.assertTrue(notes.state(NotesStateRequest(create.createdNote.id)).failOnError().isFavorited)
        notes.Favorites().delete(NotesFavoritesDeleteRequest(create.createdNote.id))
        Assertions.assertFalse(
            notes.state(NotesStateRequest(create.createdNote.id)).failOnError().isFavorited
        )
    }

    @Test
    fun mentions() = runTest {
        val mentions = notes.mentions(NotesMentionsRequest()).failOnError()
    }

    @Test
    fun reactions() = runTest {
        val create =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultiMのテストで作成され、" +
                            "リアクション取得のテストで使用されます。 ${this@NotesTestE2E::class} reactions"
                )
            )
                .failOnError()
        val reactions = notes.reactions(NotesReactionsRequest(create.createdNote.id))
    }

    @Test
    fun renotes() = runTest {
        val create =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultim のテストで作成され、" +
                            "リノートのテストで使用されます。 ${this@NotesTestE2E::class}  renotes test"
                )
            )
                .failOnError()
        val notesCreateResponse = notes.create(NotesCreateRequest(renoteId = create.createdNote.id)).failOnError()
        val renotes = NotesRenoteRequest(create.createdNote.id).let { notes.renotes(it) }.failOnError()
        Assertions.assertEquals(listOf(notesCreateResponse.createdNote), renotes)
    }

    @Test
    fun replies() = runTest {
        val root =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultim のテストで作成され、" +
                            "返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test"
                )
            )
                .failOnError()
        val reply1 = notes.create(
            NotesCreateRequest(
                text = "返信1 このノートはMultimのテストで作成され、返信取得のテストで使用されます。" +
                        " ${this@NotesTestE2E::class} replies test",
                replyId = root.createdNote.id
            )
        ).failOnError()
        val reply2 = notes.create(
            NotesCreateRequest(
                text = "返信2 このノートはMultimのテストで作成され、返信取得のテストで使用されます。 ${this@NotesTestE2E::class} replies test",
                replyId = root.createdNote.id
            )
        ).failOnError()
        val replies = root.createdNote.id.let { NotesRepliesRequest(it) }.let { notes.replies(it) }.failOnError()
        Assertions.assertEquals(listOf(reply1.createdNote, reply2.createdNote).sortedBy { note -> note.id },
            replies.sortedBy { note -> note.id })
    }

    @Test
    fun searchByTag() = runTest {
        val tag = UUID.randomUUID().toString()
        val tagedNote =
            notes.create(
                NotesCreateRequest(
                    text = "#$tag このノートはMultimのテストで作成され、" +
                            "タグ検索のテストで使用されます。 ${this@NotesTestE2E::class} search by tag test"
                )
            )
                .failOnError()
        val searchByTag = notes.searchByTag(NotesSearchByTagRequest(tag)).failOnError()
        org.assertj.core.api.Assertions.assertThat(searchByTag)?.isNotEmpty
        Assertions.assertTrue(searchByTag.contains(tagedNote.createdNote))
    }

    @Test
    fun threadMutingCreate() = runTest {
        val create =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultimのテストで作成され、スレッドミュートのテストで使用されます。 " +
                            "${this@NotesTestE2E::class} thread mute create test"
                )
            )
                .failOnError()
        Assertions.assertFalse(NotesStateRequest(create.createdNote.id).let {
            notes.state(it).failOnError().isMutedThread
        })
        NotesThreadMutingCreateRequest(create.createdNote.id).let { notes.ThreadMuting().create(it) }
        Assertions.assertTrue(NotesStateRequest(create.createdNote.id).let {
            notes.state(it).failOnError().isMutedThread
        })
    }

    @Test
    fun threadMutingDelete() = runTest {
        val create =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultimのテストで作成され、スレッドミュート解除のテストで使用されます。 " +
                            "${this@NotesTestE2E::class} thread mute delete test"
                )
            )
                .failOnError()
        Assertions.assertFalse(
            notes.state(NotesStateRequest(create.createdNote.id)).failOnError().isMutedThread
        )
        NotesThreadMutingCreateRequest(create.createdNote.id).let { notes.ThreadMuting().create(it) }
        Assertions.assertTrue(NotesStateRequest(create.createdNote.id).let {
            notes.state(it).failOnError().isMutedThread
        })
        NotesThreadMutingDeleteRequest(create.createdNote.id).let { notes.ThreadMuting().delete(it) }
        Assertions.assertFalse(NotesStateRequest(create.createdNote.id).let {
            notes.state(it).failOnError().isMutedThread
        })
    }

    @Test
    fun timeline() = runTest {
        val timeline = notes.timeline(NotesTimelineRequest()).failOnError()
    }

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun unrenote() = runBlocking {
        val create =
            notes.create(
                NotesCreateRequest(
                    text = "このノートはMultimのテストで作成され、リノート取り消しのテストで使用されます。" +
                            " ${this@NotesTestE2E::class} unrenote test"
                )
            )
                .failOnError()
        delay(1000)
        val renoted = notes.create(NotesCreateRequest(renoteId = create.createdNote.id)).failOnError()
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
    }

    @Test
    @Disabled("自分以外ということが確定しているノートを取得できない為無効化")
    // TODO: 自分以外のノートを取得できるようになってからテストを追加
    fun watchingCreate() = runTest {
//
//        val noteId = "9bk3hn1qd0"
//        val state = notes.state(NotesStateRequest(noteId)).failOnError()
//        if (state.isWatching) {
//            notes.Watching().delete(NotesWatchingDeleteRequest(noteId))
//        }
//        notes.Watching().create(NotesWatchingCreateRequest(noteId)).failOnError()
//        Assertions.assertTrue(notes.state(NotesStateRequest(noteId)).failOnError().isWatching)
    }

    @Test
    @Disabled("自分以外ということが確定しているノートを取得できない為無効化")
    fun watchingDelete() = runTest {
//        val noteId = "9bk3hn1qd0"
//        val state = notes.state(NotesStateRequest(noteId)).failOnError()
//        if (!state.isWatching) {
//            notes.Watching().create(NotesWatchingCreateRequest(noteId))
//        }
//        notes.Watching().delete(NotesWatchingDeleteRequest(noteId))
//        Assertions.assertFalse(notes.state(NotesStateRequest(noteId)).failOnError().isWatching)
    }
}
