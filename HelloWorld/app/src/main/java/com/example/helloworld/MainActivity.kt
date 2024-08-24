package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
//import com.example.helloworldkotlin.R // Import your app's R class

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}