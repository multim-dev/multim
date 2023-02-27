package dev.usbharu.multim.model

abstract class Folder(
    val fileCount: Int,
    val parent: Folder? = null
)
