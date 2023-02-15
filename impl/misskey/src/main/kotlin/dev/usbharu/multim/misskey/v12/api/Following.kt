package dev.usbharu.multim.misskey.v12.api

import com.github.michaelbull.result.Result
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*

class Following(val client: MisskeyApiClient) {
    suspend fun create(followingCreateRequest: FollowingCreateRequest): Result<FollowingCreateResponse, MultiMError> {
        return client.post<FollowingCreateRequest, FollowingCreateResponse>(
            followingCreateRequest,
            "api/following/create"
        ).mapMultiMError()
    }

    suspend fun delete(followingDeleteRequest: FollowingDeleteRequest): Result<FollowingDeleteResponse, MultiMError> {
        return client.post<FollowingDeleteRequest, FollowingDeleteResponse>(
            followingDeleteRequest,
            "api/following/delete"
        ).mapMultiMError()
    }

    suspend fun invalidate(followingInvalidateRequest: FollowingInvalidateRequest): Result<FollowingInvalidateResponse, MultiMError> {
        return client.post<FollowingInvalidateRequest, FollowingInvalidateResponse>(
            followingInvalidateRequest,
            "api/following/invalidate"
        ).mapMultiMError()
    }

    inner class Requests {
        suspend fun accept(followingRequestsAcceptRequest: FollowingRequestsAcceptRequest): Result<Unit, MultiMError> {
            return client.postWithoutResponse(
                followingRequestsAcceptRequest,
                "api/following/requests/list"
            ).mapMultiMError()
        }

        suspend fun cancel(followingRequestsCancelRequest: FollowingRequestsCancelRequest): Result<Unit, MultiMError> {
            return client.postWithoutResponse(
                followingRequestsCancelRequest,
                "api/following/requests/cancel"
            ).mapMultiMError()
        }

        suspend fun list(): Result<FollowingRequestsListResponse, MultiMError> {
            return client.postEmpty<FollowingRequestsListResponse>("api/following/requests/list")
                .mapMultiMError()
        }

        suspend fun reject(followingRequestsRejectRequest: FollowingRequestsRejectRequest): Result<Unit, MultiMError> {
            return client.postWithoutResponse(
                followingRequestsRejectRequest,
                "api/following/requests/reject"
            ).mapMultiMError()
        }
    }
}
