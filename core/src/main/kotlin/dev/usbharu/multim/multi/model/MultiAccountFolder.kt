package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Folder
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountFolder(override val innerData: Folder, override val hashCode: Int) :
    MultiAccountData<Folder>, Folder(innerData.fileCount, innerData.parent)
