package com.example.proyectomelanoma

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class HistorialPersonal3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_personal3)

        val consumoTotalTitulo: TextView = findViewById(R.id.consumoTotalTitulo)
        val cantidadBebidasRadioGroup: RadioGroup = findViewById(R.id.cantidadBebidasRadioGroup)
        val cantidadBebidasOtroCaja: TextInputLayout = findViewById(R.id.otroCaja)
        val otroTipoCancerRadioGroup: RadioGroup = findViewById(R.id.OtroCancerRadioGroup)
        val otroTipoCancerTitulo: TextView = findViewById(R.id.otroTipoCancerTitulo)
        val tipoCancerCaja: TextInputLayout = findViewById(R.id.tipoCancerCaja)
        val siConsumeBebidas: RadioButton = findViewById(R.id.siConsumeBebidas)
        val noConsumeBebidas: RadioButton = findViewById(R.id.noConsumeBebidas)
        val siOtroCancer: RadioButton = findViewById(R.id.siOtroCancer)
        val noOtroCancer: RadioButton = findViewById(R.id.noOtroCancer)
        val bebidasSemanales3: RadioButton = findViewById(R.id.bebidasSemanales3)
        val bebidasDiarias2: RadioButton = findViewById(R.id.bebidasDiarias2)
        val otro: RadioButton = findViewById(R.id.otro)
        val otroText: TextInputEditText = findViewById(R.id.otroText)
        val ningunaAnteriores: RadioButton = findViewById(R.id.ningunaAnteriores)
        val tipoCancerText: TextInputEditText = findViewById(R.id.tipoCancerText)

        if(historialPersonalCita.consume_bebidas_alcoholicas_total=="0")
            bebidasSemanales3.isChecked=true
        else if(historialPersonalCita.consume_bebidas_alcoholicas_total=="1")
            bebidasDiarias2.isChecked=true
        else if(historialPersonalCita.consume_bebidas_alcoholicas_total=="2") {
            otro.isChecked=true
            otroText.setText(historialPersonalCita.consume_bebidas_alcoholicas_total_otro)
            val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
            otroTipoCancerTituloVertical.verticalBias = 0.693f
            val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
            otroTipoCancerRadioGroupVertical.verticalBias = 0.763f
            val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
            tipoCancerCajaVertical.verticalBias = 0.878f
        } else if (historialPersonalCita.consume_bebidas_alcoholicas_total=="3")
            ningunaAnteriores.isChecked=true

        if(historialPersonalCita.diagnostico_propio_cancer == "1"){
            tipoCancerCaja.isVisible = true
            siOtroCancer.isChecked=true
            tipoCancerText.setText(historialPersonalCita.tipos_cancer_propios)
        }
        else {
            tipoCancerCaja.isVisible = false
            if(historialPersonalCita.diagnostico_propio_cancer == "0"){
                tipoCancerCaja.isVisible = false
                noOtroCancer.isChecked = true
            }
        }

        if (historialPersonalCita.consume_bebidas_alcoholicas=="1"){
            consumoTotalTitulo.isVisible = true
            cantidadBebidasRadioGroup.isVisible = true
            siConsumeBebidas.isChecked = true
            if(historialPersonalCita.consume_bebidas_alcoholicas_total =="2"){
                cantidadBebidasOtroCaja.isVisible = true
                otroText.setText(historialPersonalCita.consume_bebidas_alcoholicas_total_otro)

            }else{
                cantidadBebidasOtroCaja.isVisible = false
                val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                otroTipoCancerTituloVertical.verticalBias = 0.629f
                val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                otroTipoCancerRadioGroupVertical.verticalBias = 0.699f
                val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                tipoCancerCajaVertical.verticalBias = 0.814f
            }
        }else{
            if(historialPersonalCita.consume_bebidas_alcoholicas=="0")
                noConsumeBebidas.isChecked = true
            consumoTotalTitulo.isVisible = false
            cantidadBebidasRadioGroup.isVisible = false
            siConsumeBebidas.isChecked = false
            cantidadBebidasOtroCaja.isVisible = false
            val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
            otroTipoCancerTituloVertical.verticalBias = 0.326f
            val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
            otroTipoCancerRadioGroupVertical.verticalBias = 0.396f
            val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
            tipoCancerCajaVertical.verticalBias = 0.511f
        }

        otroText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                historialPersonalCita.consume_bebidas_alcoholicas_total_otro = otroText.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        tipoCancerText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                historialPersonalCita.tipos_cancer_propios = tipoCancerText.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorHP3)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@HistorialPersonal3, HistorialPersonal2::class.java)
            startActivity(intent)
        }

        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteHP3)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@HistorialPersonal3, HistorialFamiliar1::class.java)
            startActivity(intent)
        }
    }

    fun onRadioButtonOtroCancerClicked(view: View) {
        val tipoCancerCaja: TextInputLayout = findViewById(R.id.tipoCancerCaja)
        val otroText: TextInputEditText = findViewById(R.id.otroText)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siOtroCancer ->
                    if (checked) {
                        historialPersonalCita.diagnostico_propio_cancer = "1"
                        tipoCancerCaja.isVisible = true
                    }
                R.id.noOtroCancer ->
                    if (checked) {
                        historialPersonalCita.diagnostico_propio_cancer = "0"
                        tipoCancerCaja.isVisible = false
                        otroText.setText("")
                        historialPersonalCita.tipos_cancer_propios=""
                    }
            }
        }
    }
    fun onRadioButtonConsumeBebidasClicked(view: View) {
        val consumoTotalTitulo: TextView = findViewById(R.id.consumoTotalTitulo)
        val cantidadBebidasRadioGroup: RadioGroup = findViewById(R.id.cantidadBebidasRadioGroup)
        val cantidadBebidasOtroCaja: TextInputLayout = findViewById(R.id.otroCaja)
        val otroTipoCancerRadioGroup: RadioGroup = findViewById(R.id.OtroCancerRadioGroup)
        val otroTipoCancerTitulo: TextView = findViewById(R.id.otroTipoCancerTitulo)
        val tipoCancerCaja: TextInputLayout = findViewById(R.id.tipoCancerCaja)
        val bebidasSemanales3: RadioButton = findViewById(R.id.bebidasSemanales3)
        val bebidasDiarias2: RadioButton = findViewById(R.id.bebidasDiarias2)
        val otro: RadioButton = findViewById(R.id.otro)
        val ningunaAnteriores: RadioButton = findViewById(R.id.ningunaAnteriores)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siConsumeBebidas ->
                    if (checked) {
                        historialPersonalCita.consume_bebidas_alcoholicas = "1"
                        consumoTotalTitulo.isVisible = true
                        cantidadBebidasRadioGroup.isVisible = true
                        cantidadBebidasOtroCaja.isVisible = false
                        bebidasSemanales3.isChecked=false
                        bebidasDiarias2.isChecked=false
                        otro.isChecked = false
                        ningunaAnteriores.isChecked = false
                        val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerTituloVertical.verticalBias = 0.629f
                        val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerRadioGroupVertical.verticalBias = 0.699f
                        val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                        tipoCancerCajaVertical.verticalBias = 0.814f
                    }
                R.id.noConsumeBebidas ->
                    if (checked) {
                        historialPersonalCita.consume_bebidas_alcoholicas = "0"
                        historialPersonalCita.consume_bebidas_alcoholicas_total=""
                        historialPersonalCita.consume_bebidas_alcoholicas_total_otro=""

                        consumoTotalTitulo.isVisible = false
                        cantidadBebidasRadioGroup.isVisible = false
                        cantidadBebidasOtroCaja.isVisible = false
                        val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerTituloVertical.verticalBias = 0.326f
                        val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerRadioGroupVertical.verticalBias = 0.396f
                        val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                        tipoCancerCajaVertical.verticalBias = 0.511f
                    }
            }
        }
    }

    fun onRadioButtonCantidadBebidasClicked(view: View) {
        val cantidadBebidasOtroCaja: TextInputLayout = findViewById(R.id.otroCaja)
        val otroTipoCancerRadioGroup: RadioGroup = findViewById(R.id.OtroCancerRadioGroup)
        val otroTipoCancerTitulo: TextView = findViewById(R.id.otroTipoCancerTitulo)
        val otroText: TextInputEditText = findViewById(R.id.otroText)
        val tipoCancerCaja: TextInputLayout = findViewById(R.id.tipoCancerCaja)
        val bebidasSemanales3: RadioButton = findViewById(R.id.bebidasSemanales3)
        val bebidasDiarias2: RadioButton = findViewById(R.id.bebidasDiarias2)
        val otro: RadioButton = findViewById(R.id.otro)
        val ningunaAnteriores: RadioButton = findViewById(R.id.ningunaAnteriores)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.bebidasSemanales3 ->
                    if (checked) {
                        historialPersonalCita.consume_bebidas_alcoholicas_total = "0"
                        historialPersonalCita.consume_bebidas_alcoholicas_total_otro = "Más de 3 bebidas semanales"
                        cantidadBebidasOtroCaja.isVisible = false
                        bebidasSemanales3.isChecked = true
                        val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerTituloVertical.verticalBias = 0.629f
                        val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerRadioGroupVertical.verticalBias = 0.699f
                        val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                        tipoCancerCajaVertical.verticalBias = 0.814f
                    }
                R.id.bebidasDiarias2 ->
                    if (checked) {
                        historialPersonalCita.consume_bebidas_alcoholicas_total = "1"
                        historialPersonalCita.consume_bebidas_alcoholicas_total_otro = "Más de 2 bebidas diarias"
                        cantidadBebidasOtroCaja.isVisible = false
                        val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerTituloVertical.verticalBias = 0.629f
                        val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerRadioGroupVertical.verticalBias = 0.699f
                        val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                        tipoCancerCajaVertical.verticalBias = 0.814f
                    }
                R.id.otro ->
                    if (checked) {
                        historialPersonalCita.consume_bebidas_alcoholicas_total = "2"
                        historialPersonalCita.consume_bebidas_alcoholicas_total_otro = ""
                        otroText.setText("")
                        cantidadBebidasOtroCaja.isVisible = true
                        val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerTituloVertical.verticalBias = 0.693f
                        val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerRadioGroupVertical.verticalBias = 0.763f
                        val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                        tipoCancerCajaVertical.verticalBias = 0.878f
                    }
                R.id.ningunaAnteriores ->
                    if (checked) {
                        historialPersonalCita.consume_bebidas_alcoholicas_total = "3"
                        historialPersonalCita.consume_bebidas_alcoholicas_total_otro = "Ninguna de las anteriores"
                        cantidadBebidasOtroCaja.isVisible = false
                        val otroTipoCancerTituloVertical = otroTipoCancerTitulo.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerTituloVertical.verticalBias = 0.629f
                        val otroTipoCancerRadioGroupVertical = otroTipoCancerRadioGroup.layoutParams as ConstraintLayout.LayoutParams
                        otroTipoCancerRadioGroupVertical.verticalBias = 0.699f
                        val tipoCancerCajaVertical = tipoCancerCaja.layoutParams as ConstraintLayout.LayoutParams
                        tipoCancerCajaVertical.verticalBias = 0.814f
                    }
            }
        }
    }
}