package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.createHttpClient
import dev.usbharu.multim.model.misskey.v12.ApShowRequest
import dev.usbharu.multim.model.misskey.v12.ApShowResponse
import dev.usbharu.multim.secret.BuildKonfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfSystemProperty
import kotlin.test.assertIs

class ApTestE2E {

    val misskeyApiClient = MisskeyApiClient(
        BuildKonfig.token, "https://misskey.usbharu.dev/",
        createHttpClient()
    )

    @Test
    fun show_showUserRequest_respondTypeUser() = runTest {
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://pawoo.net/web/accounts/1593472"))
        assertIs<ApShowResponse.TypeUser>(show)
    }

    @Test
    fun show_showNoteRequest_respondTypeNote() = runTest {
        val show = Ap(misskeyApiClient).show(ApShowRequest("https://misskey.usbharu.dev/notes/9aaw20p6w4"))
        assertIs<ApShowResponse.TypeNote>(show)
    }
}
