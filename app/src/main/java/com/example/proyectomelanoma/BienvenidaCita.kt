package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class BienvenidaCita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida_cita)
        val botonBienvenida: MaterialButton = findViewById(R.id.botonBienvenida)
        botonBienvenida.setOnClickListener {
            val intent = Intent(this@BienvenidaCita, InformacionPersonal::class.java)
            startActivity(intent)
        }
    }
}