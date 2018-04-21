package edu.champlain.hangman_client.ACTIVITIES

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import edu.champlain.hangman_client.UTIL.HangmanWebSocket
import edu.champlain.hangman_client.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

const val EXTRA_USERNAME: String = "edu.champlain.hangman_client.USERNAME"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View) {
        val usernameInput: EditText = this.findViewById(R.id.username_input)

        val username: String = usernameInput.text.toString()
        
        if (username != "") {
            val intent: Intent = Intent(this, GameActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, username)

            startActivity(intent)
        }
    }
}

