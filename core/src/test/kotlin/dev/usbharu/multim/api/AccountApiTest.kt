package dev.usbharu.multim.api

import org.junit.jupiter.api.Test

abstract class AccountApiTest {

    abstract val accountApi: AccountApi

    @Test
    abstract fun userTimeline()

    @Test
    open fun userTimeline_illegalAccount_returnErr() {
    }

    @Test
    open fun userTimeline_requestWithSinceId_returnRangeTimeline() {
    }

    @Test
    open fun userTimeline_requestWithUntilId_returnRangeTimeline() {
    }

    @Test
    open fun userTimeline_requestWithSinceIdAndUntilId_returnRangeTimeline() {
    }


    @Test
    abstract fun follow() // フォローに関しては、どのように振る舞うべきかは実装によって違うので詳細なテストは書かない

    @Test
    abstract fun follow_illegalAccount_returnErr()

    @Test
    abstract fun unfollow() //アンフォローも同じ

    @Test
    abstract fun unfollow_illegalAccount_returnErr()

    @Test
    abstract fun profile()

    @Test
    abstract fun profile_illegalAccount_returnErr()

    @Test
    abstract fun statuses()

    @Test
    abstract fun statusesWithIncludeRepost()

    @Test
    abstract fun statuses_illegalAccount_returnErr()

    @Test
    abstract fun relationships()

    @Test
    open fun relationships_sameAccount_returnErr() {
    }

    @Test
    abstract fun relationships_illegalAccount_returnErr()

    @Test
    abstract fun requestCancel()

    @Test
    abstract fun requestCancel_illegalAccount_returnErr()

    @Test
    abstract fun requestAccept()

    @Test
    abstract fun requestAccept_illegalAccount_returnErr()

    @Test
    abstract fun requestReject()

    @Test
    abstract fun requestReject_illegalAccount_returnErr()
}
