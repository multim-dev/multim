package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*

class Following(val client: MisskeyApiClient) {
    suspend fun create(followingCreateRequest: FollowingCreateRequest): MultiMResult<FollowingCreateResponse> {
        return client.post<FollowingCreateRequest, FollowingCreateResponse>(
            followingCreateRequest,
            "api/following/create"
        ).mapMultiMError()
    }

    suspend fun delete(followingDeleteRequest: FollowingDeleteRequest): MultiMResult<FollowingDeleteResponse> {
        return client.post<FollowingDeleteRequest, FollowingDeleteResponse>(
            followingDeleteRequest,
            "api/following/delete"
        ).mapMultiMError()
    }

    suspend fun invalidate(followingInvalidateRequest: FollowingInvalidateRequest)
    : MultiMResult<FollowingInvalidateResponse> {
        return client.post<FollowingInvalidateRequest, FollowingInvalidateResponse>(
            followingInvalidateRequest,
            "api/following/invalidate"
        ).mapMultiMError()
    }

    inner class Requests {
        suspend fun accept(followingRequestsAcceptRequest: FollowingRequestsAcceptRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(
                followingRequestsAcceptRequest,
                "api/following/requests/list"
            ).mapMultiMError()
        }

        suspend fun cancel(followingRequestsCancelRequest: FollowingRequestsCancelRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(
                followingRequestsCancelRequest,
                "api/following/requests/cancel"
            ).mapMultiMError()
        }

        suspend fun list(): MultiMResult<FollowingRequestsListResponse> {
            return client.postEmpty<FollowingRequestsListResponse>("api/following/requests/list")
                .mapMultiMError()
        }

        suspend fun reject(followingRequestsRejectRequest: FollowingRequestsRejectRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(
                followingRequestsRejectRequest,
                "api/following/requests/reject"
            ).mapMultiMError()
        }
    }
}
