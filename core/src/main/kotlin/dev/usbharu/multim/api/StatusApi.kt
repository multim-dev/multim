package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.UniqueId
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * 投稿に関する操作のAPI
 *
 */
@Suppress("PropertyName","VariableNaming")
interface StatusApi {
    val POST: String
        get() = "status/post"

    /**
     * 投稿する.
     *
     * @param status 投稿する内容
     * @return 投稿された内容
     */
    suspend fun post(status: StatusForPost): MultiMResult<Status> {
        Logger.debug("Status Api", "Not impl status api post")
        return Err(MultiMError("post not implements", null, ErrorType.NOT_IMPL))
    }

    val DELETE: String
        get() = "status/delete"

    /**
     * 投稿を削除する.
     *
     * @param id 削除する投稿のID
     * @return 成功したらUnit
     */
    suspend fun delete(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Not impl status api delete")
        return Err(MultiMError("delete not implements", null, ErrorType.NOT_IMPL))
    }

    val FIND_BY_ID: String
        get() = "status/findById"

    /**
     * IDから投稿を取得する
     *
     * @param id 取得する投稿のID
     * @return 取得された投稿
     */
    suspend fun findById(id: StatusId): MultiMResult<Status> {
        Logger.debug("Status Api", "Not impl status api findById")
        return Err(MultiMError("findById not implements", null, ErrorType.NOT_IMPL))
    }

    val ADD_REACTION: String
        get() = "status/addReaction"

    /**
     * 投稿にリアクションを追加する.
     * すでにリアクションを追加している投稿にもう一度追加した場合の挙動は実装に依存する.
     * @param id リアクションする投稿のID
     * @param reaction リアクションするリアクション
     * @return 成功したらUnit
     */
    suspend fun addReaction(id: StatusId, reaction: Reaction): MultiMResult<Unit> {
        Logger.debug("Status Api", "Not impl status api addReaction")
        return Err(MultiMError("addReaction not implements", null, ErrorType.NOT_IMPL))
    }

    val REMOVE_REACTION: String
        get() = "status/remvoeReaction"

    /**
     * 削除するリアクション.
     * reactionの内容にかかわらず、削除されるリアクションは実装に依存する.
     *
     * @param id 削除するリアクションがある投稿
     * @param reaction 削除するリアクション
     * @return 成功したらUnit
     */
    suspend fun removeReaction(id: StatusId, reaction: Reaction?): MultiMResult<Unit> {
        Logger.debug("Status Api", "Not impl status api removeReaction")
        return Err(MultiMError("removeReaction not implements", null, ErrorType.NOT_IMPL))
    }

    val REACTIONS: String
        get() = "status/reactions"

    /**
     * Reactions 投稿に付加されているリアクションの一覧を取得する.
     * 今後返り値が変わる可能性がある.
     * @param id リアクション一覧を取得する投稿のID
     * @return 付加されているリアクションをキーとし、付加されている数をvalueにしたMap
     */
    // TODO: 返り値をPairのListにしてもいいと思う
    suspend fun reactions(id: StatusId): MultiMResult<Map<Reaction, Int>> {
        Logger.debug("Status Api", "Not impl status api reactions")
        return Err(MultiMError("reactions not implements", null, ErrorType.NOT_IMPL))
    }

    val REPLIES: String
        get() = "status/replies"

    /**
     * 投稿対しての返信を取得する.
     *
     * @param id 返信を取得する投稿
     * @return 返信の投稿のList
     */
    suspend fun replies(id: StatusId): MultiMResult<List<Status>> {
        Logger.debug("Status Api", "Not impl status api replies")
        return Err(MultiMError("replies not implements", null, ErrorType.NOT_IMPL))
    }

    val REPOST: String
        get() = "status/repost"

    /**
     * 再投稿する.
     * リツイート、ブースト、リノートを指す.
     *
     * @param id 再投稿する投稿のID
     * @return 再投稿された投稿
     */
    suspend fun repost(id: StatusId): MultiMResult<Status> {
        Logger.debug("Status Api", "Not impl status api repost")
        return Err(MultiMError("repost not implements", null, ErrorType.NOT_IMPL))
    }

    val UN_REPOST: String
        get() = "status/unRepost"

    /**
     * 再投稿を取り消す
     * 再投稿した際に返される投稿のIDを指定してください.
     *
     * 投稿A を再投稿した際に返される 投稿B のIDを指定することで再投稿の 投稿B 自体を削除します.
     *
     * @param id 取り消す再投稿のID
     * @return 成功したらUnit
     */
    suspend fun unRepost(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Not impl status api unRepost")
        return Err(MultiMError("unRepost not implements", null, ErrorType.NOT_IMPL))
    }

    val REPLY_TO: String
        get() = "status/replyTo"

    /**
     * 投稿に返信する.
     *
     * @param id 返信相手の投稿ID
     * @param status 返信する投稿
     * @return 投稿された返信
     */
    suspend fun replyTo(id: StatusId, status: StatusForPost): MultiMResult<Status> {
        Logger.debug("Status Api", "Not impl status api replyTo")
        return Err(MultiMError("replyTo not implements", null, ErrorType.NOT_IMPL))
    }

    val ADD_TO_BOOKMARKS: String
        get() = "status/addBookMarks"

    /**
     * ブックマークに投稿を追加する.
     * お気に入りなどの場合もある.
     * すでに追加されている投稿をもう一度追加した場合の挙動は実装に依存する
     *
     * @param id 追加する投稿のID
     * @return 成功したらUnit
     */
    suspend fun addToBookmarks(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Not impl status api addToBookmarks")
        return Err(MultiMError("addToBookmarks not implements", null, ErrorType.NOT_IMPL))
    }

    val REMOVE_FROM_BOOKMARKS: String
        get() = "status/removeFromBookmarks"

    /**
     * ブックマークから投稿を削除する.
     * 同じ投稿が複数個ブックマークされている場合の挙動は実装に依存する
     *
     * @param id
     * @return 成功したらUnit
     */
    suspend fun removeFromBookmarks(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Not impl status api removeFromBookmarks")
        return Err(MultiMError("removeFromBookmarks not implements", null, ErrorType.NOT_IMPL))
    }

    val GET_PREVIOUS_AND_NEXT: String
        get() = "status/getPreviousAndNext"

    /**
     * 指定された投稿の前後の投稿を取得する.
     *
     * @param id 前後の投稿を取得する投稿のID
     * @return 取得した前後の投稿
     */
    suspend fun getPreviousAndNext(id: StatusId): MultiMResult<PreviousAndNextPosts> {
        Logger.debug("Status Api", "Not impl status api getPreviousAndNext")
        return Err(MultiMError("getPreviousAndNext not implements", null, ErrorType.NOT_IMPL))
    }

    /**
     * ユニークIDを計算する
     * 投稿の内容、投稿者名、投稿時間からハッシュを作成します.
     * ハッシュの作成方法は今後変更される可能性があります.
     *
     * @param status 計算する投稿
     * @return 計算されたユニークID
     */
    fun getUniqueId(status: Status): Int {
        val localDateTime = status.createdAt.toLocalDateTime(TimeZone.UTC)
        val date: String =
            "${localDateTime.year}${localDateTime.monthNumber}${localDateTime.dayOfMonth}" +
                    "${localDateTime.hour}${localDateTime.minute}${localDateTime.second}"
        return UniqueId.hashAlgorithm.hash32x86(
            (status.content.text + status.account.accountName + date).encodeToByteArray()
        ).toInt()
    }

    val AVAILABLE_REACTIONS: String
        get() = "status/availableReactions"

    /**
     * 付加可能なリアクションのを取得する.
     * 実際に付加しない永久分からない場合もあるので実装依存.
     * @return 付加可能なリアクションの一覧,
     */
    suspend fun availableReactions(): MultiMResult<List<Reaction>> {
        Logger.debug("Status Api", "Not impl status api availableReactions")
        return Err(MultiMError("availableReactions not implements", null, ErrorType.NOT_IMPL))
    }
}

/**
 * 未実装API
 *
 * すべてのAPIが未実装なAPI
 */
object NotImplStatusApi : StatusApi
