package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.model.Status

/**
 * IDの文字列で取得できるアカウント操作のAPI
 *
 */
interface StringIdAccountApi : AccountApi {
    /**
     * 指定されたユーザーのタイムラインを取得する.
     *
     * @param accountId 取得するユーザーのアカウントID(文字列)
     * @param since 指定されたID(文字列)以後の投稿を取得
     * @param until 指定されたID(文字列)以前の投稿を取得
     * @return 取得された投稿のリスト
     */
    @Deprecated("statusesに統合")
    suspend fun userTimeline(
        accountId: String,
        since: String? = null,
        until: String? = null,
    ): MultiMResult<List<Status>> {
        Logger.debug("Account Api", "Not impl account api. userTimeline by id.")
        return Err(MultiMError("userTimeline by id not implements", null, ErrorType.NOT_IMPL))
    }

    /**
     * 指定したアカウントの投稿を取得する.
     *
     * @param accountId 投稿を取得するアカウントのID
     * @param includeRepost trueでrepostを含める
     * @param since since以後の投稿を取得する
     * @param until until以前の投稿を取得する
     * @return 取得した投稿のList
     */
    suspend fun statuses(
        accountId: String,
        includeRepost: Boolean = false,
        since: String? = null,
        until: String? = null
    ): MultiMResult<List<Status>> {
        Logger.debug("Account Api", "Not impl account api. statuses by id.")
        return Err(MultiMError("statuses by id not implements", null, ErrorType.NOT_IMPL))
    }

    /**
     * 指定したアカウントのプロフィールを取得する
     *
     * @param accountId プロフィールを取得するアカウントのID
     * @return 取得した
     */
    suspend fun profile(accountId: String): MultiMResult<Profile> {
        Logger.debug("Account Api", "Not impl account api. profile by id.")
        return Err(MultiMError("profile by id not implements", null, ErrorType.NOT_IMPL))
    }
}

/**
 * 未実装API
 *
 * すべてのAPIが未実装なAPI
 */
object NotImplStringIdAccountApi : StringIdAccountApi
