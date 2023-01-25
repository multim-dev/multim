package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*
import io.ktor.client.call.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class Drive(val client: MisskeyApiClient) {

    suspend fun drive(): DriveResponse {
        return client.post("api/drive")
    }

    suspend fun files(filesRequest: DriveFilesRequest): DriveFilesResponse {
        return client.post(filesRequest, "api/drive/files")
    }

    suspend fun folders(foldersRequest: DriveFoldersRequest): DriveFoldersResponse {
        return client.post(foldersRequest, "api/drive/folders")
    }

    inner class Files {
        suspend fun attachedNotes(attachedNotesRequest: DriveFilesAttachedNotesRequest): DriveFilesAttachedNotesResponse {
            return client.post(attachedNotesRequest, "api/drive/files/attached-notes")
        }

        suspend fun checkExistence(checkExistenceRequest: DriveFilesCheckExistenceRequest): DriveFilesCheckExistenceResponse {
            return client.post(checkExistenceRequest, "api/drive/files/check-existence")
        }

        suspend fun create(createRequest: DriveFilesCreateRequest): DriveFilesCreateResponse {
            return client.client.submitFormWithBinaryData(
                client.baseUrl + "api/drive/files/create",
                formData = formData {
                    append("\"i\"",client.token)
                    append("\"file\"",createRequest.file, Headers.build {
                        append(HttpHeaders.ContentDisposition,"filename=${createRequest.name}")
                    })
                }).body()
//            return client.post(createRequest, "api/drive/files/create")
        }

        suspend fun delete(deleteRequest: DriveFilesDeleteRequest) {
            return client.postWithoutResponse(deleteRequest, "api/drive/files/delete")
        }

        suspend fun findByHash(findByHashRequest: DriveFilesFindByHashRequest): DriveFilesFindByHashResponse {
            return client.post(findByHashRequest, "api/drive/files/find-by-hash")
        }

        suspend fun find(findRequest: DriveFilesFindRequest): DriveFilesFindResponse {
            return client.post(findRequest, "api/drive/files/find")
        }

        suspend fun show(showRequest: DriveFilesShowRequestByUrl): DriveFilesShowResponse {
            return client.post(showRequest, "api/drive/files/show")
        }

        suspend fun show(showRequest: DriveFilesShowRequestByFileId): DriveFilesShowResponse {
            return client.post(showRequest, "api/drive/files/show")
        }

        suspend fun update(updateRequest: DriveFilesUpdateRequest): DriveFilesUpdateResponse {
            return client.post(updateRequest, "api/drive/files/update")
        }

        suspend fun uploadFromUrl(uploadFromUrlRequest: DriveFilesUploadFromUrlRequest) {
            return client.postWithoutResponse(
                uploadFromUrlRequest,
                "api/drive/files/upload-from-url"
            )
        }
    }

    inner class Folders {
        suspend fun create(foldersCreateRequest: DriveFoldersCreateRequest): DriveFoldersCreateResponse {
            return client.post(foldersCreateRequest, "api/drive/folders/create")
        }

        suspend fun delete(foldersDeleteRequest: DriveFoldersDeleteRequest) {
            return client.postWithoutResponse(foldersDeleteRequest, "api/drive/folders/delete")
        }

        suspend fun find(foldersFindRequest: DriveFoldersFindRequest): DriveFoldersFindResponse {
            return client.post(foldersFindRequest, "api/drive/folders/find")
        }
    }
}
