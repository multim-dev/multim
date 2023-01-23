package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.createHttpClient
import dev.usbharu.multim.model.misskey.v12.*
import dev.usbharu.multim.secret.BuildKonfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class NotesTestE2E {

    val notes = Notes(MisskeyApiClient(BuildKonfig.token, "https://misskey.usbharu.dev/", createHttpClient()))

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
        val show = notes.show(NotesShowRequest("9ac0713keb"))
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
        assertFails {
            notes.show(NotesShowRequest(deleteNote.createdNote.id))
        }
    }

    @Test
    fun featured() = runTest {
        val featured = notes.featured()
        println(featured)
    }


}
