package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.*
import dev.usbharu.multim.misskey.v13.model.components.Note

class Users(val client: MisskeyApiClient) {
    suspend fun relation(relationRequest: UsersRelationRequest): MultiMResult<UsersRelationResponse> {
        return client.post<UsersRelationRequest, UsersRelationResponse>(
            relationRequest,
            "api/users/relation"
        ).mapMultiMError()
    }

    suspend fun notes(notesRequest: UsersNotesRequest): MultiMResult<UsersNotesResponseV13> {
        return client.post<UsersNotesRequest, List<Note>>(notesRequest, "api/users/notes")
            .mapMultiMError()
    }

    suspend fun show(showRequest: UsersShowRequest): MultiMResult<UsersShowResponseV13> {
        return client.post<UsersShowRequest, UsersShowResponseV13>(showRequest, "api/users/show")
            .mapMultiMError()
    }
}
