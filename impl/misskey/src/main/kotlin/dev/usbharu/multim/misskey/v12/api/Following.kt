package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*

class Following(val client: MisskeyApiClient) {
    suspend fun create(followingCreateRequest: FollowingCreateRequest): FollowingCreateResponse {
        return client.post(followingCreateRequest, "api/following/create")
    }

    suspend fun delete(followingDeleteRequest: FollowingDeleteRequest): FollowingDeleteResponse {
        return client.post(followingDeleteRequest, "api/following/delete")
    }

    suspend fun invalidate(followingInvalidateRequest: FollowingInvalidateRequest): FollowingInvalidateResponse {
        return client.post(followingInvalidateRequest, "api/following/invalidate")
    }

    inner class Requests {
        suspend fun accept(followingRequestsAcceptRequest: FollowingRequestsAcceptRequest) {
            client.postWithoutResponse(
                followingRequestsAcceptRequest,
                "api/following/requests/list"
            )
        }

        suspend fun cancel(followingRequestsCancelRequest: FollowingRequestsCancelRequest) {
            client.postWithoutResponse(
                followingRequestsCancelRequest,
                "api/following/requests/cancel"
            )
        }

        suspend fun list(): FollowingRequestsListResponse {
            return client.post("api/following/requests/list")
        }

        suspend fun reject(followingRequestsRejectRequest: FollowingRequestsRejectRequest) {
            return client.postWithoutResponse(
                followingRequestsRejectRequest,
                "api/following/requests/reject"
            )
        }
    }
}
