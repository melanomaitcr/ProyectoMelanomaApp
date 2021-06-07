package com.example.proyectomelanoma

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.*
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout



var listaBotones = mutableListOf<MaterialButton>()

class HistorialFamiliar2 : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaBotones = mutableListOf<MaterialButton>()
        setContentView(R.layout.activity_historial_familiar2)
        val botonNuevoPariente: MaterialButton = findViewById(R.id.botonNuevoPariente);

        botonNuevoPariente.isEnabled = historialFamiliarCita.familiares_con_otro_tipo_cancer == "1"

        val ll: LinearLayout = findViewById(R.id.linearLayout);
        if (familiarOtroCancerLista.isNotEmpty()){
            for (familiar in familiarOtroCancerLista){
                println(listaBotones)
                nuevoParentesco(ll, familiar, true)
            }
        }

        botonNuevoPariente.setOnClickListener {
            println(listaBotones)
             nuevoParentesco(ll, null, false)
            println(listaBotones)
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
            val intent = Intent(this@HistorialFamiliar2, Archivos::class.java)
            startActivity(intent)
        }
    }

    fun onRadioButtonOtroTipoCancerParentescoClicked(view: View){
        val botonNuevoPariente: MaterialButton = findViewById(R.id.botonNuevoPariente);
        val ll: LinearLayout = findViewById(R.id.linearLayout);
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siFamiliaresOtroCancer ->
                    if (checked) {
                        historialFamiliarCita.familiares_con_otro_tipo_cancer = "1"
                        botonNuevoPariente.isEnabled = true
                    }

                R.id.noFamiliaresOtroCancer ->
                    if (checked) {
                        historialFamiliarCita.familiares_con_otro_tipo_cancer = "0"
                        botonNuevoPariente.isEnabled = false
                        listaBotones = mutableListOf<MaterialButton>()
                        familiarOtroCancerLista = mutableListOf<FamiliarOtroCancer>()
                        ll.removeAllViews()
                    }
            }
        }
    }

    fun nuevoParentesco(ll: LinearLayout, familiar: FamiliarOtroCancer?, previo: Boolean){

        var familiarOtroCancer = FamiliarOtroCancer()
        if (previo)
            familiarOtroCancer= familiar!!
        val linearLayout= LinearLayout(ll.context)

        val clpTextInputLayout0 = LinearLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.layoutParams = clpTextInputLayout0
        ll.addView(linearLayout)
        val textView = TextView(ll.context)

        textView.setText("Familiar")
        textView.textSize = 18F
        textView.setTextColor(parseColor("#036262"))
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        linearLayout.addView(textView)
        val textInputLayout = TextInputLayout(
            ContextThemeWrapper(
                ll.context,
                R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
            )
        )
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE;
        textInputLayout.boxBackgroundColor= parseColor("#FFFFFF")
        textInputLayout.boxStrokeColor= parseColor("#036262")
        textInputLayout.defaultHintTextColor = ColorStateList.valueOf(parseColor("#036262"))
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
        editText.setHintTextColor(parseColor("#036262"));
        editText.setTextColor(parseColor("#036262"))
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
        textInputLayout2.boxBackgroundColor= parseColor("#FFFFFF")
        textInputLayout2.defaultHintTextColor = ColorStateList.valueOf(parseColor("#036262"))

        textInputLayout2.boxStrokeColor= parseColor("#036262")
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
        editText2.setTextColor(parseColor("#036262"))
        textInputLayout2.addView(editText2)
        ll.addView(textInputLayout2)
        if(!previo)
            familiarOtroCancerLista.add(familiarOtroCancer)

        val botonM = MaterialButton(linearLayout.context, null, R.attr.borderlessButtonStyle)
        val clpTextInputLayout3 = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        clpTextInputLayout3.width = 60
        clpTextInputLayout3.leftMargin = 25
        AppCompatResources.getDrawable(this,R.drawable.baseline_delete_24)?.let {
            DrawableCompat.setTint(
                it, Color.parseColor("#036262"))
        };

        botonM.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_delete_24, 0, 0, 0);
        botonM.setLayoutParams(clpTextInputLayout3)
        botonM.cornerRadius = 60
        botonM.setPadding(0,0,1,0)
        botonM.setBackgroundColor(Color.parseColor("#FFFFFF"))
        listaBotones.add(listaBotones.size,botonM)
        botonM.setOnClickListener {
            familiarOtroCancerLista.remove(familiarOtroCancer)
            println(familiarOtroCancerLista)
            ll.removeView(textInputLayout2)
            listaBotones.remove(botonM)
            ll.removeView(textInputLayout)
            ll.removeView(linearLayout)

        }
        linearLayout.addView(botonM)
    }
    override fun onBackPressed() {}
}