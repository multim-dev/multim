package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Timeline(val client: MisskeyApiClient) {

    val sendMutex: Mutex = Mutex()
    val receiveMutex: Mutex = Mutex()

    val sendList: MutableList<String> = mutableListOf()

    val receiveCallbackList: MutableList<(String) -> Unit> = mutableListOf()

    suspend fun connect() {
//        withContext(Dispatchers.Default){
//            webSocketSession = client.webSocketSession("wss" + baseUrl.replaceFirst(Regex("https?"),"")+"/streaming")
//        }

//        println("end session?")

        val urlString = "ws" + client.baseUrl.replaceFirst(Regex("http"), "") + "/streaming"
        println(urlString)
        client.client.ws(urlString) {
            awaitAll(
                async {
                    incoming.consumeEach {
                        when (it) {
                            is Frame.Close -> println("Connection closed")
                            is Frame.Text -> {
                                val readText = it.readText()
                                println("Received: $readText")
                                for (function in receiveCallbackList) {
                                    function.invoke(readText)
                                }
                            }
                            is Frame.Binary -> println("binary")
                            is Frame.Ping -> println("ping")
                            is Frame.Pong -> println("pong")
                            else -> println("else")
                        }
                    }
                },
                async {
                    sendMutex.withLock {

                        val text = sendList.removeLastOrNull()
                        if (text != null) {
                            outgoing.send(Frame.Text(text))
                        }
                    }
                }
            )
        }

    }

    suspend fun send(text: String) {

        sendMutex.withLock {
            sendList.add(text)
            println("send $text")
        }
    }

    suspend fun addCallback(callback: (String) -> Unit) {
        receiveMutex.withLock {
            receiveCallbackList.add(callback)
        }
    }


}
