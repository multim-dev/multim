package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.TestUtil.failOnError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.ApShowRequest
import dev.usbharu.multim.misskey.v13.model.ApShowResponse
import dev.usbharu.multim.misskey.v13.model.NotesNotesRequest
import dev.usbharu.multim.model.SingleTokenAuth
import io.github.artsok.RepeatedIfExceptionsTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions

class ApTestE2E {

    val misskeyApiClient = MisskeyApiClient(
        SingleTokenAuth(System.getProperty("multim_misskeyv13_token")),
        System.getProperty("multim_misskeyv13_instance"),
        MultiM.httpClientWithJson.config {}
    )

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun show_showUserRequest_respondTypeUser() = runBlocking {
        val show =
            Ap(misskeyApiClient).show(
                ApShowRequest(
                    System.getProperty("multim_misskeyv13_instance") + "@" + I(
                        misskeyApiClient
                    ).i().failOnError().username
                )
            )
                .failOnError()
        delay(1000)
        Assertions.assertInstanceOf(ApShowResponse.TypeUser::class.java, show)
    }

    @RepeatedIfExceptionsTest(repeats = 4)
//    @Test
    fun show_showNoteRequest_respondTypeNote() = runBlocking {
        val show =
            Ap(misskeyApiClient).show(
                ApShowRequest(
                    Notes(misskeyApiClient).notes(NotesNotesRequest()).failOnError().first().uri
                        ?: Assertions.fail()
                )
            )
                .failOnError()
        delay(1000)
        Assertions.assertInstanceOf(ApShowResponse.TypeNote::class.java, show)
    }
}
