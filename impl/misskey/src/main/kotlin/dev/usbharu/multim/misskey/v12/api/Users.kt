package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*

class Users(val client: MisskeyApiClient) {
    suspend fun relation(relationRequest: UsersRelationRequest): UsersRelationResponse {
        return client.post(relationRequest, "api/users/relation")
    }

    suspend fun notes(notesRequest: UsersNotesRequest): UsersNotesResponse {
        return client.post(notesRequest, "api/users/notes")
    }

    suspend fun show(showRequest: UsersShowRequest): UsersShowResponse {
        return client.post(showRequest, "api/users/show")
    }
}
