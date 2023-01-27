package dev.usbharu.multim.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class NodeInfoE2E{
    @Test
    fun nodeinfo() = runTest {
        val nodeinfo = NodeinfoApi(HttpClient(CIO).config {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }).nodeinfo("https://misskey-dev.usbharu.dev/")
        println(nodeinfo)
    }
}
