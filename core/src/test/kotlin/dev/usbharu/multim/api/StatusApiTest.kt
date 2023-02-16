package dev.usbharu.multim.api

import org.junit.jupiter.api.Test

abstract class StatusApiTest {
    @Test
    abstract fun post()

    @Test
    abstract fun post_illegalStatus()

    @Test
    abstract fun delete()

    @Test
    abstract fun delete_illegalStatusId()

    @Test
    abstract fun findById()

    @Test
    abstract fun findById_illegalStatusId()

    @Test
    abstract fun addReaction()

    @Test
    abstract fun addReaction_illegalStatusId()

    @Test
    abstract fun addReaction_illegalReaction()

    @Test
    abstract fun removeReaction()

    @Test
    abstract fun removeReaction_illegalStatusId()

    @Test
    abstract fun removeReaction_illegalReaction()

    @Test
    abstract fun reactions()

    @Test
    abstract fun reaction_illegalStatusId()

    @Test
    abstract fun replies()

    @Test
    abstract fun replies_illegalStatusId()

    @Test
    abstract fun repost()

    @Test
    abstract fun repost_illegalStatusId()

    @Test
    abstract fun unRepost()

    @Test
    abstract fun unRepost_illegalStatusId()

    @Test
    abstract fun replyTo()

    @Test
    abstract fun replyTo_illegalStatusId()

    @Test
    abstract fun replyTo_illegalStatus()

    @Test
    abstract fun addToBookmarks()

    @Test
    abstract fun addToBookmarks_illegalStatusId()

    @Test
    abstract fun removeFromBookmarks()

    @Test
    abstract fun removeFromBookmarks_illegalStatusId()

    @Test
    abstract fun getPreviousAndNext()

    @Test
    abstract fun getPreviousAndNext_illegalStatusIs()

}
