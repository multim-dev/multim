package dev.usbharu.multim.api

import dev.usbharu.multim.TestUtil.failOnSuccess
import dev.usbharu.multim.model.Account
import dev.usbharu.multim.model.Avatar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotImplAccountApiTest {
    val avatar = object : Avatar("") {}

    val account = object : Account("", "", false, avatar) {
        override val cacheKey: String
            get() = TODO("Not yet implemented")

    }

    @Test
    fun userTimeline() = runTest {
        @Suppress("DEPRECATION")
        NotImplAccountApi.userTimeline(account).failOnSuccess()
    }

    @Test
    fun follow() = runTest {
        NotImplAccountApi.follow(account).failOnSuccess()
    }

    @Test
    fun unfollow() = runTest {
        NotImplAccountApi.unfollow(account).failOnSuccess()
    }

    @Test
    fun profile() = runTest {
        NotImplAccountApi.profile(account).failOnSuccess()
    }


    @Test
    fun statuses() = runTest {
        NotImplAccountApi.statuses(account).failOnSuccess()
    }


    @Test
    fun relationships() = runTest {
        NotImplAccountApi.relationships(account, account).failOnSuccess()
    }


    @Test
    fun requestCancel() = runTest {
        NotImplAccountApi.requestCancel(account).failOnSuccess()
    }


    @Test
    fun requestAccept() = runTest {
        NotImplAccountApi.requestAccept(account).failOnSuccess()
    }

    @Test
    fun requestReject() = runTest {
        NotImplAccountApi.requestReject(account).failOnSuccess()
    }
}
