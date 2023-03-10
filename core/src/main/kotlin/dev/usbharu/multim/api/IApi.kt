package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.model.Status

/**
 * トークンの所有者(通常は生成したユーザー)に関する情報を取得するAPI
 *
 */
@Suppress("PropertyName","VariableNaming")
interface IApi {
    val PROFILE: String
        get() = "i/profile"

    /**
     * プロフィールを取得する
     *
     * @return 取得したプロフィール
     */
    suspend fun profile(): MultiMResult<Profile> {
        Logger.debug("I Api", "Not impl i api. profile")
        return Err(MultiMError("profile not implements", null, ErrorType.NOT_IMPL))
    }

    val STATUSES: String
        get() = "i/statuses"

    /**
     * 投稿を取得する
     *
     * @return 取得した投稿のList
     */

    //TODO : 詳細なフィルタリングができるように ページングも
    suspend fun statuses(): MultiMResult<List<Status>> {
        Logger.debug("I Api", "Not impl i api. statuses")
        return Err(MultiMError("profile not implements", null, ErrorType.NOT_IMPL))
    }

}

/**
 * 未実装API
 * すべてのAPIが実装されていないAPI
 */
object NotImplIApi : IApi
