package dev.usbharu.multim.api.common

import dev.usbharu.multim.utils.UUID
import kotlin.test.Test

class UUIDTest {

    @Test
    fun generateUUIDTest() {
        val uuid = UUID.generateUUID()
        println(uuid)
    }
}
