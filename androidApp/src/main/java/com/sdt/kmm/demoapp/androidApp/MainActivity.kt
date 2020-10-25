package com.sdt.kmm.demoapp.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sdt.kmm.demoapp.shared.Greeting
import com.sdt.kmm.demoapp.shared.Api
import android.widget.TextView

fun greet(): String {
    return Greeting().greeting()
}

fun login() {
    return Api().login()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        login()
    }
}
