package dev.usbharu.multim.api.common.impl.misskey

import dev.usbharu.multim.api.common.StatusApi
import dev.usbharu.multim.converter.misskey.v12.NoteConverter.toStatus
import dev.usbharu.multim.converter.misskey.v12.ReactionConverter.toReactions
import dev.usbharu.multim.model.common.*
import dev.usbharu.multim.model.common.impl.misskey.MisskeyReaction
import dev.usbharu.multim.model.common.impl.misskey.MisskeyStatusId
import dev.usbharu.multim.model.misskey.v12.*


/**
 * Misskey status api
 * 現在の実装だと、マルチアカウントには対応していません。
 * @property misskeyApis
 * @constructor Create empty Misskey status api
 */
class MisskeyStatusApi(private val misskeyApis: MisskeyApis) : StatusApi {
    override suspend fun post(status: StatusForPost): Status {

        val create = misskeyApis.notes.create(NotesCreateRequest(text = status.content.getText()))
        return create.createdNote.toStatus()
    }

    override suspend fun delete(id: StatusId): Boolean {
        if (id is MisskeyStatusId) {
            misskeyApis.notes.delete(NotesDeleteRequest(id.id))
            return true
        }
        return false
    }

    override suspend fun findById(id: StatusId): Status {
        return misskeyApis.notes.show(NotesShowRequest(id.fetchId().id)).toStatus()
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): Boolean {
        if (reaction is MisskeyReaction && id is MisskeyStatusId) {
            misskeyApis.notes.Reaction().create(
                NotesReactionCreateRequest(
                    noteId = id.id, reaction = reaction.toLocal().name
                )
            )
            return true
        }
        return false
    }

    /**
     * Remove reaction
     *
     * @param id
     * @param reaction 無視される
     * @return
     */
    override suspend fun removeReaction(id: StatusId, reaction: Reaction?): Boolean {
        return if (id is MisskeyStatusId) {
            misskeyApis.notes.Reaction().delete(NotesReactionDeleteRequest(id.id))
            true
        } else {
            //サーバーが認知していない投稿のリアクションを消せるわけないので何もしない
            false
        }
    }

    override suspend fun reactions(id: StatusId): Map<Reaction, Int> {
        val show = misskeyApis.notes.show(NotesShowRequest(id.fetchId().id))
        return show.reactions.toReactions(show)
    }

    override suspend fun repost(id: StatusId): Status {
        return misskeyApis.notes.create(NotesCreateRequest(renoteId = id.fetchId().id)).createdNote.toStatus()
    }

    override suspend fun unRepost(id: StatusId): Boolean {
        return if (id is MisskeyStatusId) {
            misskeyApis.notes.unrenote(NotesUnrenoteRequest(id.id))
            true
        } else {
            //サーバーが認知していない投稿の再投稿を消せるわけないので何もしない
            false
        }
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): Status {
        return misskeyApis.notes.create(
            NotesCreateRequest(
                replyId = id.fetchId().id, text = status.content.getText()
            )
        ).createdNote.toStatus()
    }

    override suspend fun addToBookmarks(id: StatusId): Boolean {
        misskeyApis.notes.Favorites().create(NotesFavoritesCreateRequest(id.fetchId().id))
        return true
    }

    override suspend fun removeFromBookmarks(id: StatusId): Boolean {
        return if (id is MisskeyStatusId) {
            misskeyApis.notes.Favorites().delete(NotesFavoritesDeleteRequest(id.id))
            true
        } else {
            // サーバーが認知していない投稿のブックマークを消せるわけないので何もしない
            false
        }
    }

    override suspend fun getPreviousAndNext(id: StatusId): PreviousAndNextPosts {
        TODO("よくわからないので未実装") //よくわからんのでとりあえず未実装 多分user-timelineからリノートを取り除いたらできる
    }

    override suspend fun replies(id: StatusId): List<Status> {

        return misskeyApis.notes.children(NotesChildrenRequest(id.fetchId().id))
            .map { it.toStatus() }

    }

    private suspend fun StatusId.fetchId(): MisskeyStatusId {
        if (this is MisskeyStatusId) {
            return this
        }
        val show = misskeyApis.ap.show(ApShowRequest(getUrl()))
        if (show is ApShowResponse.TypeNote) {
            return show.note.toStatus().id
        }
        throw IllegalArgumentException("The format of the Id is different.")
    }
}
