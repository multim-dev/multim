package dev.usbharu.multim.misskey.v12.common.api

import dev.usbharu.multim.api.ApiClient
import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class MisskeyApiClient(var token: String, baseUrl: String, client: HttpClient) :
    ApiClient(baseUrl, client.config {
        expectSuccess = true
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
