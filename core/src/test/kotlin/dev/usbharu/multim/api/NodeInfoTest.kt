package dev.usbharu.multim.api

import dev.usbharu.multim.Logger
import dev.usbharu.multim.TestUtil.failOnError
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class NodeInfoE2E {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun nodeinfo() = runTest {
        val nodeinfo = NodeinfoApi(HttpClient(CIO).config {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }).nodeinfo(System.getProperty("multim_misskey_instance")).failOnError()
        Logger.debug(nodeinfo.toString())
    }
}
