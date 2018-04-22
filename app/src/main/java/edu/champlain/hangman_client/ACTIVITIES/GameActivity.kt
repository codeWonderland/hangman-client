package edu.champlain.hangman_client.ACTIVITIES

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import edu.champlain.hangman_client.R
import edu.champlain.hangman_client.UTIL.HangmanWebSocket
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity() {

    private val serverUrl = "ws://10.0.1.16:3000/ws"

    private var webSocket: WebSocket? = null

    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        name = intent.getStringExtra(EXTRA_USERNAME)

//        Set the welcome message
        val textView: TextView = findViewById(R.id.welcome_message)

        val welcomeMessage = "Welcome $name!"

        textView.text = welcomeMessage


//        Create the WebSocket connection
        val client = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .build()

        val request = Request.Builder()
                .url(serverUrl)
                .build()

        webSocket = client.newWebSocket(request, HangmanWebSocket(this, name))
    }

//    If the letter isn't empty, send letter to WebSocket
    fun sendLetter(view: View) {
        val letterInput: EditText = findViewById(R.id.letter_input)

        val letter: String = letterInput.text.toString()

        if (letter != "") {
//            we use a question mark because webSocket is nullable
            webSocket?.send("{ \"Author\": \"$name\", \"Body\": \"$letter\" }")
        }
    }

    fun setAnswer(answer: String) {
        val answerView: TextView = this.findViewById(R.id.answer_view)

        answerView.text = answer
    }
}
