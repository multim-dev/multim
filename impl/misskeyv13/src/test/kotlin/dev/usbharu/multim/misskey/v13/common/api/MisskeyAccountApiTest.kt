package dev.usbharu.multim.misskey.v13.common.api

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import dev.usbharu.multim.Logger
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.AccountApiTest
import dev.usbharu.multim.error.MultiMHttpError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.misskey.v13.api.MisskeyApis
import dev.usbharu.multim.misskey.v13.common.MisskeyAccount
import dev.usbharu.multim.misskey.v13.common.MisskeyAvatar
import dev.usbharu.multim.model.SingleTokenAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
@Suppress("EmptyIfBlock")
@OptIn(ExperimentalCoroutinesApi::class)
class MisskeyAccountApiTest : AccountApiTest() {
    override val accountApi: AccountApi
        get() = MisskeyAccountApi(
            MisskeyApis(
                MisskeyApiClient(
                    SingleTokenAuth(System.getProperty("multim_misskeyv13_token")),
                    System.getProperty("multim_misskeyv13_instance"),
                    MultiM.httpClientWithJson
                )
            )
        )

    @Test
    override fun userTimeline() = runTest {
        accountApi.userTimeline(
            MisskeyAccount(
                "9cfye6ns6b",
                "test",
                "test",
                false,
                MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/9bg1zu54y7")
            )
        ).failOnError()
    }

    @Test
    override fun follow() = runTest {
        val myself = MisskeyAccount(
            "9bg1zu54y7",
            "test",
            "test",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/9bg1zu54y7")
        )
        val account = MisskeyAccount(
            "9cfye6ns6b",
            "test1",
            "test1",
            false,
            MisskeyAvatar("https://test-misskey-v13.usbharu.dev/identicon/9bk3hbcjcy")
        )
        if (accountApi.relationships(myself, account).failOnError().following) {
            Logger.warn("すでにフォローされているのでフォローをいったん解除します。")
            accountApi.unfollow(account)
        }
        accountApi.follow(account).failOnError()
        assertTrue(accountApi.relationships(myself, account).failOnError().following, "フォローされていない")
        accountApi.unfollow(account).failOnError()
        assertFalse(accountApi.relationships(myself, account).failOnError().following, "フォローしたまま")
    }

    @Test
    override fun follow_illegalAccount_returnErr() = runTest {
        val myself = MisskeyAccount(
            "9bg1zu54y7",
            "test",
            "test",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/9bg1zu54y7")
        )
        val illegalAccount = MisskeyAccount(
            "Obrg",
            "beard",
            "leg",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/TrT")
        )
        val follow = accountApi.follow(illegalAccount)
        follow.onSuccess {
            fail("成功してはいけない")
        }
        follow.onFailure {
            if (it is MultiMHttpError) {

            } else {
                Logger.error("Account Api Test", "想定されたエラーではない", it)
                fail("想定されたエラーではない")
            }
        }
    }

    @Test
    override fun unfollow() = runTest {
        val myself = MisskeyAccount(
            "9bg1zu54y7",
            "test",
            "test",
            false,
            MisskeyAvatar("https://test-misskey-v13.usbharu.dev/identicon/9bg1zu54y7")
        )
        val account = MisskeyAccount(
            "9cfye6ns6b",
            "test1",
            "test1",
            false,
            MisskeyAvatar("https://test-misskey-v13.usbharu.dev/identicon/9bk3hbcjcy")
        )
        if (!accountApi.relationships(myself, account).failOnError().following) {
            Logger.warn("まだにフォローされているのでフォローをいったんします。")
            accountApi.follow(account)
        }
        accountApi.unfollow(account).failOnError()
        assertFalse(accountApi.relationships(myself, account).failOnError().following, "フォローされていない")
    }

    @Test
    override fun unfollow_illegalAccount_returnErr() = runTest {
        val illegalAccount = MisskeyAccount(
            "I37",
            "1Q8I",
            "U72oM",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/TFOTXjvs")
        )
        val unfollow = accountApi.unfollow(illegalAccount)
        unfollow.onSuccess {
            fail("成功してはいけない")
        }
        unfollow.onFailure {
            if (it is MultiMHttpError) {

            } else {
                Logger.error("Account Api Test", "想定されたエラーではない", it)
                fail("想定されたエラーではない")
            }
        }
    }

    @Test
    override fun profile() = runTest {
        val myself = MisskeyAccount(
            "9cfye6ns6b",
            "test1",
            "test1",
            false,
            MisskeyAvatar("https://test-misskey-v13.usbharu.dev/identicon/9bg1zu54y7")
        )
        val profile = accountApi.profile(myself).failOnError()
        assertEquals(myself, profile.account)
    }

    @Test
    override fun profile_illegalAccount_returnErr() = runTest {
        val illegalAccount = MisskeyAccount(
            "x0Z3N",
            "R8kOy",
            "2bQfw",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/BPk")
        )
        val profile = accountApi.profile(illegalAccount)
        profile.onSuccess {
            fail("成功してはいけない")
        }
        profile.onFailure {
            if (it is MultiMHttpError) {

            } else {
                fail("想定されたエラーではない")
            }
        }
    }

    override fun statuses() {
        TODO("Not yet implemented")
    }

    override fun statusesWithIncludeRepost() {
        TODO("Not yet implemented")
    }

    override fun statuses_illegalAccount_returnErr() {
        TODO("Not yet implemented")
    }

    @Test
    override fun relationships() = runTest {
        val myself = MisskeyAccount(
            "9bg1zu54y7",
            "test",
            "test",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/9bg1zu54y7")
        )
        val account = MisskeyAccount(
            "9bk3hbcjcy",
            "test1",
            "test1",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/9bk3hbcjcy")
        )
        accountApi.relationships(myself, account).failOnError()
    }

    @Test
    override fun relationships_illegalAccount_returnErr() = runTest {
        val myself = MisskeyAccount(
            "9bg1zu54y7",
            "test",
            "test",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/9bg1zu54y7")
        )
        val illegalAccount = MisskeyAccount(
            "Obrg",
            "beard",
            "leg",
            false,
            MisskeyAvatar("https://test-misskey-v12.usbharu.dev/identicon/TrT")
        )
        val relationships = accountApi.relationships(myself, illegalAccount)
        relationships.onSuccess {

        }
        relationships.onFailure {
            if (it is MultiMHttpError) {
                fail("Misskey実装ではエラーが出ない")
            } else {
                fail("想定されたエラーではない")
            }
        }
    }

    override fun requestCancel() {
        TODO("Not yet implemented")
    }

    override fun requestCancel_illegalAccount_returnErr() {
        TODO("Not yet implemented")
    }

    override fun requestAccept() {
        TODO("Not yet implemented")
    }

    override fun requestAccept_illegalAccount_returnErr() {
        TODO("Not yet implemented")
    }

    override fun requestReject() {
        TODO("Not yet implemented")
    }

    override fun requestReject_illegalAccount_returnErr() {
        TODO("Not yet implemented")
    }

    fun <T> MultiMResult<T>.failOnError(): T {
        val get = this.get()
        if (get != null) {
            return get
        }
        val error = this.getError()!!
        fail("Return Error ${error.message}", error.throwable)
    }
}
