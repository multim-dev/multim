package dev.usbharu.multim.misskey.v13.common.api

import dev.usbharu.multim.Logger
import dev.usbharu.multim.MultiM.json
import dev.usbharu.multim.api.ApiClient
import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import dev.usbharu.multim.model.SingleTokenAuth
import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString

class MisskeyApiClient(var auth: SingleTokenAuth, baseUrl: String, client: HttpClient) :
    ApiClient(baseUrl, client.config {
        expectSuccess = true
        install(WebSockets) {
            pingInterval = 20_000
            contentConverter = KotlinxWebsocketSerializationConverter(json)
        }
        install(createClientPlugin("MisskeyAuthPlugin") {
            onRequest { request, content ->
                Logger.trace("Misskey Auth Plugin","request type is :${content::class}")
                if (content is MisskeyNeedAuth) {
                    Logger.trace("Misskey Auth Plugin","Injection token")
                    content.i = auth.token
                }
                request.headers.append(
                    "Content-Type", ContentType.Application.Json
                )
            }
        })
    }) {

    internal val streaming = Streaming()

    internal inner class Streaming(context: CoroutineDispatcher = Dispatchers.IO) {

        val callbackMutex = Mutex()
        val callbackList = mutableMapOf<String, Callback>()

        val coroutineScope = CoroutineScope(context)

        var commands = MutableStateFlow("a")
        fun connect() {
            val launch = coroutineScope.launch {
                client.wss("ws" + baseUrl.replaceFirst("http", "") + "streaming?i=${auth.token}") {
                    awaitAll(
                        coroutineScope.async {
                            commands.onEach {
                                Logger.debug("Api Client Streaming", "Sending :$it")
                                outgoing.send(Frame.Text(it))
                            }
                                .launchIn(coroutineScope)
                        },
                        coroutineScope.async {
                            incoming.consumeEach {
                                callbackMutex.withLock {
                                    callbackList.forEach { mutableEntry ->
                                        mutableEntry.value.invoke(it)
                                    }
                                }
                            }
                        }
                    )
                }
                Logger.debug("Api Client Streaming","Connected")

            }
//            launch.join()
        }

        suspend fun disconnect() {
            coroutineScope.launch {
//                webSocketSession?.close()
            }.cancelAndJoin()
        }

        inline fun <reified T> send(data: T) {
            coroutineScope.launch {
                val value = json.encodeToString(data)
                commands.emit(value)
            }
        }

        fun send(data: String) {
            coroutineScope.launch {
                commands.emit(data)
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
