package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.createHttpClient
import dev.usbharu.multim.model.misskey.v12.ApShowRequest
import dev.usbharu.multim.model.misskey.v12.ApShowResponse
import dev.usbharu.multim.secret.BuildKonfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfSystemProperty
import kotlin.test.assertIs
