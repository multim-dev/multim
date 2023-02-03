package dev.usbharu.multim.model

abstract class Source {
    data class Base64Source(val base64: String) : Source()

    data class ByteArraySource(val byteArray: ByteArray) : Source()

    data class StringSource(val string: String) : Source()

    data class UrlSource(val url: String) : Source()
}
