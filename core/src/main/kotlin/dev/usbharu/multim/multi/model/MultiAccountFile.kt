package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.File
import dev.usbharu.multim.model.FileForPost
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountFile(override val innerData: File, override val hashCode: Int) :
    MultiAccountData<File>, File(innerData.data, innerData.type, innerData.name)

class MultiAccountFileForPost(override val innerData: FileForPost, override val hashCode: Int) :
    MultiAccountData<FileForPost>, FileForPost(innerData.source, innerData.type, innerData.name)
