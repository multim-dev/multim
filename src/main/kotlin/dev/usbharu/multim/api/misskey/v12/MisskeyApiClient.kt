package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.ApiClient
import dev.usbharu.multim.model.misskey.v12.components.MisskeyNeedAuth
import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
class MisskeyApiClient(var token: String, baseUrl: String, client: HttpClient) :
    ApiClient(baseUrl, client.config {
        install(WebSockets) {
            pingInterval = 20_000
        }
        install(createClientPlugin("MisskeyAuthPlugin") {
            onRequest { request, content ->
                println("request type is :${content::class}")
                if (content is MisskeyNeedAuth) {
                    println("injection token")
                    content.i = token
                }
                request.headers.append(
                    "Content-Type",
                    ContentType.Application.Json
                )
            }
        })
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    })
