package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HistorialPersonal2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_personal2)
        val edadFumarCaja: TextInputLayout = findViewById(R.id.edadFumarCaja)
        val fumaActualmenteRadioGroup: RadioGroup = findViewById(R.id.fumaActualmenteRadioGroup)
        val fumaActualmenteTitulo: TextView = findViewById(R.id.fumaActualmenteTitulo)
        val siHaFumadoRadioButton: RadioButton = findViewById(R.id.siHaFumado)
        val noHaFumadoRadioButton: RadioButton = findViewById(R.id.noHaFumado)
        val noFumaActualmenteRadioButton: RadioButton = findViewById(R.id.noFumaActualmente)
        val siFumaActualmenteRadioButton: RadioButton = findViewById(R.id.siFumaActualmente)
        val mesesFumandoCaja: TextInputLayout = findViewById(R.id.mesesFumandoCaja)
        val mesesFumando: TextInputEditText = findViewById(R.id.mesesFumandoText)
        val edadFumar: TextInputEditText = findViewById(R.id.edadFumarText)

        edadFumar.setText(historialPersonalCita.edad_empezo_fumar)
        mesesFumando.setText(historialPersonalCita.periodo_fumado)

        if (historialPersonalCita.fuma_o_ha_fumado == "1"){
            siHaFumadoRadioButton.isChecked = true
            edadFumarCaja.isVisible=true
            fumaActualmenteRadioGroup.isVisible=true
            fumaActualmenteTitulo.isVisible=true
            mesesFumandoCaja.isVisible=true
        }else{
            edadFumarCaja.isVisible=false
            fumaActualmenteRadioGroup.isVisible=false
            fumaActualmenteTitulo.isVisible=false
            mesesFumandoCaja.isVisible=false
            if (historialPersonalCita.fuma_o_ha_fumado == "0")
                noHaFumadoRadioButton.isChecked = true
        }

        if (historialPersonalCita.fuma_actualmente == "1")
            siFumaActualmenteRadioButton.isChecked = true
        if (historialPersonalCita.fuma_actualmente == "0")
            noFumaActualmenteRadioButton.isChecked = true

        edadFumar.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                historialPersonalCita.edad_empezo_fumar = edadFumar.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        mesesFumando.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                historialPersonalCita.periodo_fumado = mesesFumando.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorHP2)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@HistorialPersonal2, HistorialPersonal1::class.java)
            startActivity(intent)
        }

        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteHP2)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@HistorialPersonal2, HistorialPersonal3::class.java)
            startActivity(intent)
        }
    }


    fun onRadioButtonHaFumadoClicked(view: View) {
        val noFumaActualmenteRadioButton: RadioButton = findViewById(R.id.noFumaActualmente)
        val siFumaActualmenteRadioButton: RadioButton = findViewById(R.id.siFumaActualmente)
        val mesesFumando: TextInputEditText = findViewById(R.id.mesesFumandoText)
        val edadFumar: TextInputEditText = findViewById(R.id.edadFumarText)
        val edadFumarCaja: TextInputLayout = findViewById(R.id.edadFumarCaja)
        val fumaActualmenteRadioGroup: RadioGroup = findViewById(R.id.fumaActualmenteRadioGroup)
        val fumaActualmenteTitulo: TextView = findViewById(R.id.fumaActualmenteTitulo)
        val mesesFumandoCaja: TextInputLayout = findViewById(R.id.mesesFumandoCaja)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siHaFumado ->
                    if (checked) {
                        historialPersonalCita.fuma_o_ha_fumado="1"
                        edadFumarCaja.isVisible=true
                        fumaActualmenteRadioGroup.isVisible=true
                        fumaActualmenteTitulo.isVisible=true
                        mesesFumandoCaja.isVisible=true
                    }
                R.id.noHaFumado ->
                    if (checked) {
                        historialPersonalCita.fuma_o_ha_fumado="0"
                        edadFumarCaja.isVisible=false
                        fumaActualmenteRadioGroup.isVisible=false
                        fumaActualmenteTitulo.isVisible=false
                        mesesFumandoCaja.isVisible=false
                        fumaActualmenteRadioGroup.clearCheck()
                        mesesFumando.setText("")
                        edadFumar.setText("")
                        historialPersonalCita.periodo_fumado=""
                        historialPersonalCita.edad_empezo_fumar=""
                        historialPersonalCita.fuma_actualmente=""
                    }
            }
        }
    }

    fun onRadioButtonFumaActualmenteClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siFumaActualmente ->
                    if (checked) {
                        historialPersonalCita.fuma_actualmente="1"
                        println("S")
                    }
                R.id.noFumaActualmente ->
                    if (checked) {
                        historialPersonalCita.fuma_actualmente="0"
                        println("N")
                    }
            }
        }
    }
}