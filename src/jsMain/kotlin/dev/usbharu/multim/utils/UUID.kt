package dev.usbharu.multim.utils

import kotlin.random.Random

actual object UUID {
    actual fun generateUUID(): String {
        val bytes = Random.nextBytes(size = 16)
        bytes[6] = 0x10.toByte()
        bytes[8] = 0x0100.toByte()
        return buildString {
            append(bytes.copyOfRange(0, 4).toHex()).append('-')
            append(bytes.copyOfRange(4, 6).toHex()).append('-')
            append(bytes.copyOfRange(6, 8).toHex()).append('-')
            append(bytes.copyOfRange(8, 10).toHex()).append('-')
            append(bytes.copyOfRange(10, 16).toHex())
        }
    }

    private fun ByteArray.toHex(): String {
        return toUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }
    }
}
