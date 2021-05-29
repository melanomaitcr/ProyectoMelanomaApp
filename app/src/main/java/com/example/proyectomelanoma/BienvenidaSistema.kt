package com.example.proyectomelanoma

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.sleep


class BienvenidaSistema : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida_sistema)

        val handler = Handler()
        handler.postDelayed(task, 5000)

    }

    private val task = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}