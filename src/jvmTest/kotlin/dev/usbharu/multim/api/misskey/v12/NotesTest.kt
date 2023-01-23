package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.createHttpClient
import dev.usbharu.multim.model.misskey.v12.NotesGlobalTimelineRequest
import dev.usbharu.multim.model.misskey.v12.NotesHybridTimelineRequest
import dev.usbharu.multim.secret.BuildKonfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

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
}
