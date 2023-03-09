package dev.usbharu.multim.misskey.v12.common.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.TODO
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.common.MisskeyReaction
import dev.usbharu.multim.misskey.v12.common.MisskeyStatusId
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.NoteConverter.toStatus
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.ReactionConverter.toReactions
import dev.usbharu.multim.misskey.v12.model.*
import dev.usbharu.multim.model.*
import dev.usbharu.multim.util.EmojiUtils


/**
 * Misskey status api
 * 現在の実装だと、マルチアカウントには対応していません。
 * @property misskeyApis
 * @constructor Create empty Misskey status api
 */
class MisskeyStatusApi(private val misskeyApis: MisskeyApis) : StatusApi {
    override suspend fun post(status: StatusForPost): MultiMResult<Status> {

        return misskeyApis.notes.create(NotesCreateRequest(text = status.content.text))
            .map { it.createdNote.toStatus() }

    }

    override suspend fun delete(id: StatusId): MultiMResult<Unit> {
        if (id is MisskeyStatusId) {
            misskeyApis.notes.delete(NotesDeleteRequest(id.id))
            return Ok(Unit)
        }
        return TODO()
    }

    override suspend fun findById(id: StatusId): MultiMResult<Status> {
        return id.fetchId()
            .flatMap { misskeyApis.notes.show(NotesShowRequest(it.id)) }
            .map { it.toStatus() }
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): MultiMResult<Unit> {
        if (reaction is MisskeyReaction && id is MisskeyStatusId) {
            misskeyApis.notes.Reaction().create(
                NotesReactionCreateRequest(
                    noteId = id.id, reaction = reaction.toLocal().name
                )
            )
            return Ok(Unit)
        }
        return TODO()
    }

    /**
     * Remove reaction
     *
     * @param id
     * @param reaction 無視される
     * @return
     */
    override suspend fun removeReaction(
        id: StatusId,
        reaction: Reaction?
    ): MultiMResult<Unit> {
        return if (id is MisskeyStatusId) {
            misskeyApis.notes.Reaction().delete(NotesReactionDeleteRequest(id.id))
            Ok(Unit)
        } else {
            //サーバーが認知していない投稿のリアクションを消せるわけないので何もしない
            Err(MultiMError("id is Not misskey id", null, ErrorType.API))
        }
    }

    override suspend fun reactions(id: StatusId): MultiMResult<Map<Reaction, Int>> {
        return id.fetchId().flatMap { misskeyApis.notes.show(NotesShowRequest(it.id)) }
            .map { it.reactions.toReactions(it) }
    }

    override suspend fun repost(id: StatusId): MultiMResult<Status> {
        return id.fetchId()
            .flatMap { misskeyApis.notes.create(NotesCreateRequest(renoteId = it.id)) }
            .map { it.createdNote.toStatus() }
    }

    override suspend fun unRepost(id: StatusId): MultiMResult<Unit> {
        return if (id is MisskeyStatusId) {
            misskeyApis.notes.unrenote(NotesUnrenoteRequest(id.id))
            Ok(Unit)
        } else {
            //サーバーが認知していない投稿の再投稿を消せるわけないので何もしない
            Err(MultiMError("id is Not Misskey id", null, ErrorType.API))
        }
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): MultiMResult<Status> {
        return id.fetchId().flatMap {
            misskeyApis.notes.create(
                NotesCreateRequest(
                    replyId = it.id,
                    text = status.content.text
                )
            )
        }.map { it.createdNote.toStatus() }
    }

    override suspend fun addToBookmarks(id: StatusId): MultiMResult<Unit> {
        return id.fetchId()
            .flatMap { misskeyApis.notes.Favorites().create(NotesFavoritesCreateRequest(it.id)) }
    }

    override suspend fun removeFromBookmarks(id: StatusId): MultiMResult<Unit> {
        return if (id is MisskeyStatusId) {
            misskeyApis.notes.Favorites().delete(NotesFavoritesDeleteRequest(id.id))
            Ok(Unit)
        } else {
            // サーバーが認知していない投稿のブックマークを消せるわけないので何もしない
            Err(MultiMError("id is Not Misskey id", null, ErrorType.API))
        }
    }

    override suspend fun getPreviousAndNext(id: StatusId): MultiMResult<PreviousAndNextPosts> {
        return TODO() //よくわからんのでとりあえず未実装 多分user-timelineからリノートを取り除いたらできる
    }

    override suspend fun replies(id: StatusId): MultiMResult<List<Status>> {
        return id.fetchId().flatMap { misskeyApis.notes.children(NotesChildrenRequest(it.id)) }
            .map { it.map { note -> note.toStatus() } }
    }

    override suspend fun availableReactions(): MultiMResult<List<Reaction>> {
        return misskeyApis.meta.meta(MetaRequest())
            .map { metaResponse ->
                metaResponse.emojis
            }
            .map {
                val mutableListOf = mutableListOf<Reaction>()
                mutableListOf.addAll(EmojiUtils.getAllEmoji().map { MisskeyReaction(it.string, null) })
                mutableListOf.addAll(it.map { MisskeyReaction(it.name, it.url) })
                mutableListOf
            }
    }

    private suspend fun StatusId.fetchId(): MultiMResult<MisskeyStatusId> {
        if (this is MisskeyStatusId) {
            return Ok(this)
        }
        return misskeyApis.ap.show(ApShowRequest(getUrl())).flatMap {
            if (it is ApShowResponse.TypeNote) {
                Ok(it.note.toStatus().id)
            } else {
                Err(
                    MultiMError(
                        "The format of the Id($this ${this.getUrl()}) is different.",
                        null,
                        ErrorType.API
                    )
                )
            }
        }
    }


}
