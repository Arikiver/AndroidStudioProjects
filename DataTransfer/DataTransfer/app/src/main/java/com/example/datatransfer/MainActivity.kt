package com.example.datatransfer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {

    lateinit var send_button: Button
    lateinit var send_text: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_activity)
        send_button = findViewById(R.id.send_button_id)
        send_text = findViewById(R.id.send_text_id)


        send_button.setOnClickListener {
            // get the value which input by user in EditText and convert it to string
            val str = send_text.text.toString()
            // Create the Intent object of this class Context() to Second_activity class
            val intent = Intent(applicationContext, SecondActivity::class.java)
            // now by putExtra method put the value in key, value pair key is
            // message_key by this key we will receive the value, and put the string
            intent.putExtra("message_key", str)
            // start the Intent
            startActivity(intent)
        }


    }
}

