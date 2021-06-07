package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InformacionContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_contacto)

        val correoElectronico: TextInputEditText = findViewById(R.id.correoElectronicoText)
        val numeroTelefono: TextInputEditText = findViewById(R.id.numeroTelefonoText)
        correoElectronico.setText(expediente.correoElectronico)
        numeroTelefono.setText(expediente.numeroTelefono.toString())

        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorInfoContacto)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@InformacionContacto, InformacionPersonal::class.java)
            startActivity(intent)
        }

        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteInfoContacto)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@InformacionContacto, Domicilio::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {}
}