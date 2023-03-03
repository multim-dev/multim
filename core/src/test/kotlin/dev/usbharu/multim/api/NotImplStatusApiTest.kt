package dev.usbharu.multim.api

import dev.usbharu.multim.TestUtil.failOnSuccess
import dev.usbharu.multim.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotImplStatusApiTest {

    val avatar = object : Avatar(""){}
    private val account = object : Account("","",false,avatar){
        override val cacheKey: String
            get() = TODO("Not yet implemented")
    }
    private val content = object : Content("F6k"){}
    private val statusId = object : StatusId(){
        override fun equals(other: Any?): Boolean {
            TODO("Not yet implemented")
        }

        override fun hashCode(): Int {
            TODO("Not yet implemented")
        }

        override fun getUrl(): String {
            TODO("Not yet implemented")
        }

        override val cacheKey: String
            get() = TODO("Not yet implemented")
    }
    private val statusForPost = object : StatusForPost(account,content) {

    }
    private val reaction = object : Reaction("",""){}

    @Test
    fun post() = runTest {
        NotImplStatusApi.post(statusForPost).failOnSuccess()
    }

    @Test
    fun delete() = runTest {
        NotImplStatusApi.delete(statusId).failOnSuccess()
    }

    @Test
    fun findById() = runTest {
        NotImplStatusApi.findById(statusId).failOnSuccess()
    }

    @Test
    fun addReaction() = runTest {
        NotImplStatusApi.addReaction(statusId,reaction).failOnSuccess()
    }

    @Test
    fun removeReaction() = runTest {
        NotImplStatusApi.removeReaction(statusId,reaction).failOnSuccess()
    }

    @Test
    fun reactions() = runTest {
        NotImplStatusApi.reactions(statusId).failOnSuccess()
    }

    @Test
    fun replies() = runTest {
        NotImplStatusApi.replies(statusId).failOnSuccess()
    }

    @Test
    fun repost() = runTest {
        NotImplStatusApi.repost(statusId).failOnSuccess()
    }

    @Test
    fun unRepost() = runTest {
        NotImplStatusApi.unRepost(statusId).failOnSuccess()
    }

    @Test
    fun replyTo() = runTest {
        NotImplStatusApi.replyTo(statusId,statusForPost).failOnSuccess()
    }

    @Test
    fun addToBookmarks() = runTest {
        NotImplStatusApi.addToBookmarks(statusId).failOnSuccess()
    }

    @Test
    fun removeFromBookmarks() = runTest {
        NotImplStatusApi.removeFromBookmarks(statusId).failOnSuccess()
    }

    @Test
    fun getPreviousAndNext() = runTest {
        NotImplStatusApi.getPreviousAndNext(statusId).failOnSuccess()
    }

    @Test
    fun availableReactions() = runTest {
        NotImplStatusApi.availableReactions().failOnSuccess()
    }
}
