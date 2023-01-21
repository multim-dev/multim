package dev.usbharu.multim.utils

import java.util.UUID

actual object UUID {
    actual fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }
}
