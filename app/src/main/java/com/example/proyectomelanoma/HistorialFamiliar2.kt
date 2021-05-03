package com.example.proyectomelanoma

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class HistorialFamiliar2 : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_familiar2)

        val ll: LinearLayout = findViewById(R.id.linearLayout);
        if (familiarOtroCancerLista.isNotEmpty()){
            println(familiarOtroCancerLista)
            for (familiar in familiarOtroCancerLista){
                nuevoParentesco(ll,familiar, true)
            }
        }
        val botonNuevoPariente: MaterialButton = findViewById(R.id.nuevoPariente);
        botonNuevoPariente.setOnClickListener {
            println(familiarOtroCancerLista)
             nuevoParentesco(ll,null, false)
            println(familiarOtroCancerLista)
        }
        val siFamiliaresOtroCancer: RadioButton = findViewById(R.id.siFamiliaresOtroCancer)
        val noFamiliaresOtroCancer: RadioButton = findViewById(R.id.noFamiliaresOtroCancer)
        if (historialFamiliarCita.familiares_con_otro_tipo_cancer == "1")
            siFamiliaresOtroCancer.isChecked=true
        if (historialFamiliarCita.familiares_con_otro_tipo_cancer == "0")
            noFamiliaresOtroCancer.isChecked=true
        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorHF2)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@HistorialFamiliar2, HistorialFamiliar1::class.java)
            startActivity(intent)
        }

        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteHF2)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@HistorialFamiliar2, HistorialFamiliar2::class.java)
            startActivity(intent)
        }


    }

    fun onRadioButtonOtroTipoCancerParentescoClicked(view: View){
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siFamiliaresOtroCancer ->
                    if (checked) {
                        historialFamiliarCita.familiares_con_otro_tipo_cancer = "1"
                    }
                R.id.noFamiliaresOtroCancer ->
                    if (checked) {
                        historialFamiliarCita.familiares_con_otro_tipo_cancer = "0"
                    }
            }
        }
    }

    fun nuevoParentesco(ll:LinearLayout, familiar: FamiliarOtroCancer?, previo:Boolean){

        var familiarOtroCancer = FamiliarOtroCancer()
        if (previo)
            familiarOtroCancer= familiar!!
        val textView = TextView(ll.context)
        textView.setText("Familiar")
        textView.textSize = 18F
        textView.setTextColor( Color.parseColor("#AAAAAA"))
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        ll.addView(textView)
        val textInputLayout = TextInputLayout(
                ContextThemeWrapper(
                        ll.context,
                        R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
                )
        )
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE;
        textInputLayout.boxBackgroundColor= Color.parseColor("#FFFFFF")
        textInputLayout.hint = "Parentesco"
        val clpTextInputLayout = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        clpTextInputLayout.bottomMargin = 50
        clpTextInputLayout.topMargin = 20
        textInputLayout.setLayoutParams(clpTextInputLayout)
        textInputLayout.setBoxCornerRadii(60f, 60f, 60f, 60f);
        val editText = TextInputEditText(textInputLayout.context)
        editText.setText(familiarOtroCancer.parentesco)
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                familiarOtroCancer.parentesco = editText.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
        textInputLayout.addView(editText)
        ll.addView(textInputLayout)
        val textInputLayout2 = TextInputLayout(
                ContextThemeWrapper(
                        ll.context,
                        R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
                )
        )
        textInputLayout2.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE;
        textInputLayout2.boxBackgroundColor= Color.parseColor("#FFFFFF")
        textInputLayout2.hint = "Tipo de Cáncer"
        val clpTextInputLayout2 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        clpTextInputLayout2.bottomMargin = 50
        textInputLayout2.setLayoutParams(clpTextInputLayout2)
        textInputLayout2.setBoxCornerRadii(60f, 60f, 60f, 60f);
        val editText2 = TextInputEditText(textInputLayout2.context)
        editText2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                familiarOtroCancer.tipo_cancer = editText2.text.toString()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
        editText2.setText(familiarOtroCancer.tipo_cancer)
        textInputLayout2.addView(editText2)
        ll.addView(textInputLayout2)
        if(!previo)
            familiarOtroCancerLista.add(familiarOtroCancer)

        val botonM = MaterialButton(ll.context)
        val clpTextInputLayout3 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        clpTextInputLayout3.bottomMargin = 100
        botonM.setLayoutParams(clpTextInputLayout3)
        botonM.cornerRadius = 60
        botonM.setBackgroundColor(Color.parseColor("#AAAAAA"))
        botonM.height=150
        botonM.text = "Borrar familiar"
        botonM.isAllCaps=false
        botonM.setOnClickListener {
            familiarOtroCancerLista.remove(familiarOtroCancer)
            println(familiarOtroCancerLista)
            ll.removeView(textInputLayout2)
            ll.removeView(textView)
            ll.removeView(textInputLayout)
            ll.removeView(botonM)
        }
        ll.addView(botonM)
    }
}