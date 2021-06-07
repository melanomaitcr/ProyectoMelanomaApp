package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class FinalizacionCita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalizacion_cita)
        val botonSalir: MaterialButton = findViewById(R.id.botonSalir)
        botonSalir.setOnClickListener {
            val intent = Intent(this@FinalizacionCita, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {}
}