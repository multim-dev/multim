package dev.usbharu.multim.misskey.v13.common

import dev.usbharu.multim.model.File

class MisskeyFile(data: ByteArray, type: String, name: String) : File(data, type, name)
