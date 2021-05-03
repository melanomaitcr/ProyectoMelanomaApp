package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InformacionPersonal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_personal)
        val nombre: TextInputEditText = findViewById(R.id.nombrePacienteText)
        val numeroCedula: TextInputEditText = findViewById(R.id.numeroCedulaText)
        val fechaNacimiento: TextInputEditText = findViewById(R.id.fechaNacimientoText)
        val nacionalidad: TextInputEditText = findViewById(R.id.nacionalidadText)
        val identidadEtnica: TextInputEditText = findViewById(R.id.identidadEtnicaText)
        nombre.setText(expediente.nombreCompleto)
        numeroCedula.setText(expediente.numeroCedula)
        fechaNacimiento.setText(expediente.fechaNacimiento)
        nacionalidad.setText(expediente.nacionalidad)
        identidadEtnica.setText(expediente.identidadEtnica)

        val botonInfoPersonal: MaterialButton = findViewById(R.id.botonInfoPersonal)
        botonInfoPersonal.setOnClickListener {
            val intent = Intent(this@InformacionPersonal, InformacionContacto::class.java)
            startActivity(intent)
        }

    }
}