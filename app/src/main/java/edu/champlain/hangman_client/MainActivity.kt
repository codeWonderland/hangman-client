package edu.champlain.hangman_client

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocketListener
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val serverUrl = "ws://172.19.35.66:3000/ws"
    val name = "codeWonderland"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .build()

        val request = Request.Builder()
                .url(serverUrl)
                .build()

        val webSocket = client.newWebSocket(request, HangmanWebSocket(name))
    }
}

