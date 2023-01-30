package dev.usbharu.multim.misskey.v12.common.api

import dev.usbharu.multim.api.ApiClient
import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

class MisskeyApiClient(var token: String, baseUrl: String, client: HttpClient) :
    ApiClient(baseUrl, client.config {
        expectSuccess = true
        install(WebSockets) {
            pingInterval = 20_000
            contentConverter = KotlinxWebsocketSerializationConverter(json)
        }
        install(createClientPlugin("MisskeyAuthPlugin") {
            onRequest { request, content ->
                println("request type is :${content::class}")
                if (content is MisskeyNeedAuth) {
                    println("injection token")
                    content.i = token
                }
                request.headers.append(
                    "Content-Type", ContentType.Application.Json
                )
            }
        })
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(json = json)
        }
    }) {
    inner class Streaming {

        val callbackMutex = Mutex()
        val callbackList = mutableMapOf<String, Callback>()

        var webSocketSession: DefaultClientWebSocketSession? = null

        val coroutineScope = CoroutineScope(Dispatchers.IO)
        fun connect() {
            coroutineScope.launch {
                webSocketSession =
                    client.webSocketSession("ws" + baseUrl.replaceFirst("http", "") + "/streaming")
                webSocketSession?.incoming?.consumeEach { frame ->
                    callbackMutex.withLock {
                        callbackList.forEach {
                            it.value.invoke(frame)
                        }
                    }
                }
            }
        }

        suspend fun disconnect() {
            coroutineScope.launch {
                webSocketSession?.close()
            }.cancelAndJoin()
        }

        inline fun <reified T> send(data: T) {
            if (webSocketSession == null) {
                connect()
            }
            coroutineScope.launch {
                webSocketSession?.sendSerialized(data)
            }
        }

        fun listen(id: String, callback: Callback) {
            coroutineScope.launch {
                callbackMutex.withLock {
                    callbackList[id] = callback
                }
            }
        }

        fun unlisted(id: String) {
            coroutineScope.launch {
                callbackMutex.withLock {
                    callbackList.remove(id)
                    if (callbackList.isEmpty()) {
                        disconnect()
                    }
                }
            }
        }

        fun clearCallback() {
            coroutineScope.launch {

                callbackMutex.withLock {
                    callbackList.clear()
                    disconnect()
                }
            }
        }

    }
}

typealias Callback = (Frame) -> Unit
