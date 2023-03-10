package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.*

//todo 成功したかをboolで返しているが、詳細がわからないのでしっかり返す。

/**
 * Account api.
 * アカウントの操作に関するAPI
 *
 */
@Suppress("PropertyName","VariableNaming")
interface AccountApi {
    /**
     * 指定されたユーザーのタイムラインを取得する.
     *
     * @param account 取得するユーザーのアカウント
     * @param since 指定されたID以後の投稿を取得
     * @param until 指定されたID以前の投稿を取得
     * @return 取得された投稿のリスト
     */
    @Deprecated("statusesに統合")
    suspend fun userTimeline(
        account: Account,
        since: StatusId? = null,
        until: StatusId? = null
    ): MultiMResult<List<Status>> {
        Logger.debug("Account Api", "Not impl account api. userTimeline.")
        return Err(MultiMError("userTimeline not implements", null, ErrorType.NOT_IMPL))
    }

    val FOLLOW: String
        get() = "account/follow"

    /**
     * 指定したアカウントをフォローする。
     *
     * @param account フォローするアカウント
     * @return 成功したらUnit
     */
    suspend fun follow(account: Account): MultiMResult<Unit> {
        Logger.debug("Account Api", "Not impl account api. follow.")
        return Err(MultiMError("follow not implements", null, ErrorType.NOT_IMPL))
    }

    val UNFOLLOW: String
        get() = "account/unfollow"

    /**
     * 指定したアカウントのフォローを解除する.
     *
     * @param account フォローを解除するアカウント
     * @return 成功したらUnit
     */
    suspend fun unfollow(account: Account): MultiMResult<Unit> {
        Logger.debug("Account Api", "Not impl account api. unfollow.")
        return Err(MultiMError("unfollow not implements", null, ErrorType.NOT_IMPL))
    }

    val PROFILE: String
        get() = "account/profile"

    /**
     * 指定したアカウントのプロフィールを取得する.
     *
     * @param account プロフィールを取得するアカウント
     * @return 取得したプロフィール
     */
    suspend fun profile(account: Account): MultiMResult<Profile> {
        Logger.debug("Account Api", "Not impl account api. profile.")
        return Err(MultiMError("profile not implements", null, ErrorType.NOT_IMPL))
    }

    val STATUSES: String
        get() = "account/statuses"

    /**
     * 指定したアカウントの投稿を取得する.
     *
     * @param account 投稿を取得するアカウント
     * @param includeRepost trueでrepostを含める
     * @param since since以後の投稿を取得する
     * @param until until以後の投稿を取得する
     * @return 取得した投稿のList
     */
    suspend fun statuses(
        account: Account,
        includeRepost: Boolean = false,
        since: StatusId? = null,
        until: StatusId? = null,
    ): MultiMResult<List<Status>> {
        Logger.debug("Account Api", "Not impl account api. statuses.")
        return Err(MultiMError("statuses not implements", null, ErrorType.NOT_IMPL))
    }

    val RELATIONSHIPS: String
        get() = "account/relationships"

    /**
     * 指定したアカウント同士の関係性を取得する.
     *
     * @param myself 自分自身
     * @param other 相手
     * @return 取得した関係性
     */
    suspend fun relationships(myself: Account, other: Account): MultiMResult<Relation> {
        Logger.debug("Account Api", "Not impl account api. relationships.")
        return Err(MultiMError("relationships not implements", null, ErrorType.NOT_IMPL))
    }

    val REQUEST_CANCEL: String
        get() = "account/requestCancel"

    /**
     * フォローリクエストを中止する
     *
     * @param account 中止するフォローリクエストを送っている相手
     * @return 成功したらUnit
     */
    suspend fun requestCancel(account: Account): MultiMResult<Unit> {
        Logger.debug("Account Api", "Not impl account api. requestCancel.")
        return Err(MultiMError("requestCancel not implements", null, ErrorType.NOT_IMPL))
    }

    val REQUEST_ACCEPT: String
        get() = "account/requestAccept"

    /**
     * フォローリクエストを承認する
     *
     * @param account 承認するフォローリクエストを受け取った相手
     * @return 成功したらUnit
     */
    suspend fun requestAccept(account: Account): MultiMResult<Unit> {
        Logger.debug("Account Api", "Not impl account api. requestAccept.")
        return Err(MultiMError("requestAccept not implements", null, ErrorType.NOT_IMPL))
    }

    val REQUEST_REJECT: String
        get() = "account/requestReject"

    /**
     * フォローリクエストを拒否する
     *
     * @param account 拒否するフォローリクエストを受け取った相手
     * @return 成功したらUnit
     */
    suspend fun requestReject(account: Account): MultiMResult<Unit> {
        Logger.debug("Account Api", "Not impl account api. requestReject.")
        return Err(MultiMError("requestReject not implements", null, ErrorType.NOT_IMPL))
    }
}

/**
 * 未実装API.
 *
 * すべてのAPIが実装されていないAPIです.
 */
object NotImplAccountApi : AccountApi
