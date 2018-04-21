package edu.champlain.hangman_client.UTIL

import android.widget.TextView
import edu.champlain.hangman_client.ACTIVITIES.GameActivity
import edu.champlain.hangman_client.MODELS.Word
import edu.champlain.hangman_client.R
import java.util.concurrent.TimeUnit
import okhttp3.WebSocket
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.*

class HangmanWebSocket(val context: GameActivity, val name: String) : WebSocketListener() {

    private val word: Word = Word()

    override fun onOpen(webSocket: WebSocket, response: Response?) {
        val message = JSONObject()
                .put("Author", "ClientName")
                .put("Body", this.name)
                .toString()
        println("Message is $message")
        webSocket.send(message)
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        println("MESSAGE: " + text!!)
        context.runOnUiThread {
            //stuff that updates ui
            val message: JSONObject = JSONObject(text)
            when (message.getString("Author")) {
                "Server" -> println("The server says: ${message.getString("Body")}")
                "User" -> println("New User: ${message.getString("Body")}")
                "Word" ->
//                Here we need to take the activity's textView of the word
//                and put the encrypted word in it
                    setupWord(message.getString("Body"))

                "Letter" ->
                    newLetter(message.getString("Body"))
                "Winner" ->
                    context.setAnswer("${message.getString("Body")} has won! The word is ${word.encrypt}")
                else -> println("${message.getString("Author")} has score of ${message.getString("Body")}")
            }
        }
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

    fun setupWord(wordString: String) {
        word.newWord(wordString)
        context.setAnswer(word.getEncryptedWord())
    }

    fun newLetter(letter: String) {
        println("New letter: $letter")
        context.setAnswer(word.getEncryptedWord(letter))
    }
}