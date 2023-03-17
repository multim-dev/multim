package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.*
import io.ktor.client.call.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class Drive(val client: MisskeyApiClient) {
    @Suppress("MemberNameEqualsClassName")
    suspend fun drive(): MultiMResult<DriveResponse> {
        return client.postEmpty<DriveResponse>("api/drive").mapMultiMError()
    }

    suspend fun files(filesRequest: DriveFilesRequest): MultiMResult<DriveFilesResponse> {
        return client.post<DriveFilesRequest, DriveFilesResponse>(filesRequest, "api/drive/files")
            .mapMultiMError()
    }

    suspend fun folders(foldersRequest: DriveFoldersRequest): MultiMResult<DriveFoldersResponse> {
        return client.post<DriveFoldersRequest, DriveFoldersResponse>(
            foldersRequest,
            "api/drive/folders"
        ).mapMultiMError()
    }

    inner class Files {
        suspend fun attachedNotes(attachedNotesRequest: DriveFilesAttachedNotesRequest)
                : MultiMResult<DriveFilesAttachedNotesResponse> {
            return client.post<DriveFilesAttachedNotesRequest, DriveFilesAttachedNotesResponse>(
                attachedNotesRequest,
                "api/drive/files/attached-notes"
            ).mapMultiMError()
        }

        suspend fun checkExistence(checkExistenceRequest: DriveFilesCheckExistenceRequest)
                : MultiMResult<DriveFilesCheckExistenceResponse> {
            return client.post<DriveFilesCheckExistenceRequest, DriveFilesCheckExistenceResponse>(
                checkExistenceRequest,
                "api/drive/files/check-existence"
            ).mapMultiMError()
        }

        //TODO Result型対応
        suspend fun create(createRequest: DriveFilesCreateRequest): DriveFilesCreateResponse {
            return client.client.submitFormWithBinaryData(
                client.baseUrl + "api/drive/files/create",
                formData = formData {
                    append("\"i\"", client.auth.token)
                    append("\"file\"", createRequest.file, Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=${createRequest.name}")
                    })
                }).body()
//            return client.post(createRequest, "api/drive/files/create")
        }

        suspend fun delete(deleteRequest: DriveFilesDeleteRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(deleteRequest, "api/drive/files/delete")
                .mapMultiMError()
        }

        suspend fun findByHash(findByHashRequest: DriveFilesFindByHashRequest)
                : MultiMResult<DriveFilesFindByHashResponse> {
            return client.post<DriveFilesFindByHashRequest, DriveFilesFindByHashResponse>(
                findByHashRequest,
                "api/drive/files/find-by-hash"
            ).mapMultiMError()
        }

        suspend fun find(findRequest: DriveFilesFindRequest): MultiMResult<DriveFilesFindResponse> {
            return client.post<DriveFilesFindRequest, DriveFilesFindResponse>(
                findRequest,
                "api/drive/files/find"
            ).mapMultiMError()
        }

        suspend fun show(showRequest: DriveFilesShowRequestByUrl): MultiMResult<DriveFilesShowResponse> {
            return client.post<DriveFilesShowRequestByUrl, DriveFilesShowResponse>(
                showRequest,
                "api/drive/files/show"
            ).mapMultiMError()
        }

        suspend fun show(showRequest: DriveFilesShowRequestByFileId): MultiMResult<DriveFilesShowResponse> {
            return client.post<DriveFilesShowRequestByFileId, DriveFilesShowResponse>(
                showRequest,
                "api/drive/files/show"
            ).mapMultiMError()
        }

        suspend fun update(updateRequest: DriveFilesUpdateRequest): MultiMResult<DriveFilesUpdateResponse> {
            return client.post<DriveFilesUpdateRequest, DriveFilesUpdateResponse>(
                updateRequest,
                "api/drive/files/update"
            ).mapMultiMError()
        }

        suspend fun uploadFromUrl(uploadFromUrlRequest: DriveFilesUploadFromUrlRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(
                uploadFromUrlRequest,
                "api/drive/files/upload-from-url"
            ).mapMultiMError()
        }
    }

    inner class Folders {
        suspend fun create(foldersCreateRequest: DriveFoldersCreateRequest): MultiMResult<DriveFoldersCreateResponse> {
            return client.post<DriveFoldersCreateRequest, DriveFoldersCreateResponse>(
                foldersCreateRequest,
                "api/drive/folders/create"
            ).mapMultiMError()
        }

        suspend fun delete(foldersDeleteRequest: DriveFoldersDeleteRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(foldersDeleteRequest, "api/drive/folders/delete")
                .mapMultiMError()
        }

        suspend fun find(foldersFindRequest: DriveFoldersFindRequest): MultiMResult<DriveFoldersFindResponse> {
            return client.post<DriveFoldersFindRequest, DriveFoldersFindResponse>(
                foldersFindRequest,
                "api/drive/folders/find"
            ).mapMultiMError()
        }
    }
}
