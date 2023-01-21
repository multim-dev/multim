package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.model.misskey.v12.*

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