package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Files
import dev.usbharu.multim.model.FilesForPost
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountFiles(override val innerData: Files, override val hashCode: Int) :
    MultiAccountData<Files>, Files(innerData.files)

class MultiAccountFilesForPost(override val innerData: FilesForPost, override val hashCode: Int) :
    MultiAccountData<FilesForPost>, FilesForPost(innerData.files)
