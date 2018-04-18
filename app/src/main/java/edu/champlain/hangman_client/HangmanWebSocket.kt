package edu.champlain.hangman_client

import java.util.concurrent.TimeUnit
import okhttp3.WebSocket
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocketListener
import okio.ByteString

class HangmanWebSocket(val name: String) : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response?) {
        val message = "{\"Author\": \"ClientName\", \"Body\": \"${this.name}\"}"
        println("Message is $message")
        webSocket.send(message)
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        println("MESSAGE: " + text!!)
    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString) {
        println("BYTESTRING MESSAGE: " + bytes.hex())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String?) {
        webSocket.close(1000, null)
        println("CLOSE: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket?, t: Throwable, response: Response?) {
        println("Websocket failure")
        t.printStackTrace()
        println("Websocket failure")
    }
}