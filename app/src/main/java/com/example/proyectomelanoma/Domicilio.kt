package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Domicilio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domicilio)

        val provincia: TextInputEditText = findViewById(R.id.provinciaText)
        val canton: TextInputEditText = findViewById(R.id.cantonText)
        val distrito: TextInputEditText = findViewById(R.id.distritoText)
        provincia.setText(expediente.provincia)
        canton.setText(expediente.canton)
        distrito.setText(expediente.distrito)

        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorDomicilio)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@Domicilio, InformacionContacto::class.java)
            startActivity(intent)
        }

        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteDomicilio)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@Domicilio, HistorialPersonal1::class.java)
            startActivity(intent)
        }
    }
}