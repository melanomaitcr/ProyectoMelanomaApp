package com.example.proyectomelanoma

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.pow

class HistorialPersonal1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_personal1)
        val peso: TextInputEditText = findViewById(R.id.pesoText)
        val estatura: TextInputEditText = findViewById(R.id.estaturaText)
        val siRadioButton: RadioButton = findViewById(R.id.siActividadFisica)
        val noRadioButton: RadioButton = findViewById(R.id.noActividadFisica)
        val cantidadMinutos: TextInputEditText = findViewById(R.id.cantidadMinutosText)
        val imc: TextInputEditText = findViewById(R.id.IMCText)
        val cantidadMinutosCaja: TextInputLayout = findViewById(R.id.cantidadMinutosCaja)
        peso.setText(historialPersonalCita.peso_kg)
        estatura.setText(historialPersonalCita.estatura_cm)
        cantidadMinutos.setText(historialPersonalCita.minutos_semana_actividad_fisica)
        imc.setText(historialPersonalCita.imc)

        if (historialPersonalCita.realizacion_actividad_fisica =="1"){
            siRadioButton.isChecked = true
            cantidadMinutosCaja.isVisible=true
        }else{
            cantidadMinutosCaja.isVisible=false
            if (historialPersonalCita.realizacion_actividad_fisica =="0")
                noRadioButton.isChecked = true
        }

        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorHP1)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@HistorialPersonal1, Domicilio::class.java)
            startActivity(intent)
        }



        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteHP1)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@HistorialPersonal1, HistorialPersonal2::class.java)
            startActivity(intent)
        }

        estatura.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(estatura.text.toString().isNotEmpty() ) {
                    historialPersonalCita.estatura_cm = estatura.text.toString()
                    if (peso.text.toString().isNotEmpty() && peso.text.toString().toFloat() > 0 && estatura.text.toString().toFloat() > 0) {
                        val valor = String.format("%.3f",(historialPersonalCita.peso_kg.toFloat() / (historialPersonalCita.estatura_cm.toFloat() / 100).pow(2)))
                        imc.setText(valor)
                        historialPersonalCita.imc = valor
                    }else{
                        imc.setText("0")
                        historialPersonalCita.imc = "0"
                    }
                }else{
                    if(peso.text.toString().isEmpty()) {
                        imc.setText("")
                        historialPersonalCita.imc = ""
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        peso.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(peso.text.toString().isNotEmpty()) {
                    historialPersonalCita.peso_kg = peso.text.toString()
                    if (estatura.text.toString().isNotEmpty() && estatura.text.toString().toFloat() > 0 && peso.text.toString().toFloat() > 0) {
                        val valor = String.format("%.3f",(historialPersonalCita.peso_kg.toFloat() / (historialPersonalCita.estatura_cm.toFloat() / 100).pow(2)))
                        imc.setText(valor)
                        historialPersonalCita.imc = valor
                    }else{
                        imc.setText("0")
                        historialPersonalCita.imc = "0"
                    }
                }else{
                    if(estatura.text.toString().isEmpty()) {
                        imc.setText("")
                        historialPersonalCita.imc = ""
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        cantidadMinutos.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    historialPersonalCita.minutos_semana_actividad_fisica = cantidadMinutos.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun onRadioButtonAcitividadFisicaClicked(view: View) {
        val cantidadMinutosCaja: TextInputLayout = findViewById(R.id.cantidadMinutosCaja)
        val cantidadMinutos: TextInputEditText = findViewById(R.id.cantidadMinutosText)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siActividadFisica ->
                    if (checked) {
                        historialPersonalCita.realizacion_actividad_fisica="1"
                        cantidadMinutosCaja.isVisible = true
                    }
                R.id.noActividadFisica ->
                    if (checked) {
                        historialPersonalCita.realizacion_actividad_fisica="0"
                        cantidadMinutosCaja.isVisible = false
                        cantidadMinutos.setText("")
                        historialPersonalCita.minutos_semana_actividad_fisica=""
                    }
            }
        }
    }
    override fun onBackPressed() {}
}