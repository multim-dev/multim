package dev.usbharu.multim.model

abstract class File(
    val data:ByteArray,
    val type:String,
    val name:String
)

abstract class FileForPost(
    val source:Source,
    val type:String,
    val name:String
)
