package dev.usbharu.multim.misskey.v12.common.api

import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.api.IApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.NoteConverter.toStatus
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.UsersConverter.toProfile
import dev.usbharu.multim.misskey.v12.model.UsersNotesRequest
import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.model.Status

class MisskeyIApi(val misskeyApis: MisskeyApis) : IApi {
    override suspend fun profile(): MultiMResult<Profile> {
        return misskeyApis.i.i().map { it.toProfile() }
    }

    override suspend fun statuses(): MultiMResult<List<Status>> {
        return misskeyApis.i.i().map { it.id }
            .flatMap { misskeyApis.users.notes(UsersNotesRequest(it)) }
            .map { it.map { note -> note.toStatus() } }
    }
}
