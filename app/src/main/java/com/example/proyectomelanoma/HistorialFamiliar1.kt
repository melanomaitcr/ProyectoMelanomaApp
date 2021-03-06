package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton



class HistorialFamiliar1 : AppCompatActivity() {
    var listaParientes: MutableList<String> = mutableListOf()
    var listaCheckBox: MutableList<CheckBox> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_familiar1)

        val siFamiliaresMelanoma: RadioButton = findViewById(R.id.siFamiliaresMelanoma)
        val noFamiliaresMelanoma: RadioButton = findViewById(R.id.noFamiliaresMelanoma)
        val madreCheck: CheckBox = findViewById(R.id.madreCheck)
        val padreCheck: CheckBox = findViewById(R.id.padreCheck)
        val hermanaCheck: CheckBox = findViewById(R.id.hermanaCheck)
        val hermanoCheck: CheckBox = findViewById(R.id.hermanoCheck)
        val tiaMaternaCheck: CheckBox = findViewById(R.id.tiaMaternaCheck)
        val tioMaternoCheck: CheckBox = findViewById(R.id.tioMaternoCheck)
        val tiaPaternaCheck: CheckBox = findViewById(R.id.tiaPaternaCheck)
        val tioPaternoCheck: CheckBox = findViewById(R.id.tioPaternoCheck)
        val abuelaMaternaCheck: CheckBox = findViewById(R.id.abuelaMaternaCheck)
        val abueloMaternoCheck: CheckBox = findViewById(R.id.abueloMaternoCheck)
        val abuelaPaternaCheck: CheckBox = findViewById(R.id.abuelaPaternaCheck)
        val abueloPaternoCheck: CheckBox = findViewById(R.id.abueloPaternoCheck)

        val parentescoTitulo: TextView = findViewById(R.id.parentescoTitulo)
        listaCheckBox = mutableListOf(madreCheck, padreCheck, hermanaCheck, hermanoCheck, tiaMaternaCheck, tioMaternoCheck, tiaPaternaCheck, tioPaternoCheck, abuelaMaternaCheck, abueloMaternoCheck, abuelaPaternaCheck, abueloPaternoCheck)
        var listaPar = mutableListOf("Madre","Padre", "Hermana", "Hermano", "T??a Materna", "T??o Materno","T??a Paterna","T??o Paterno", "Abuela Materna","Abuelo Materno","Abuela Paterna", "Abuelo Paterno")

        if (historialFamiliarCita.familiares_con_cancer_melanoma == "1") {
            siFamiliaresMelanoma.isChecked = true
            for (num in listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                listaCheckBox[num].isVisible = true
            }
        }
        if (historialFamiliarCita.familiares_con_cancer_melanoma == "0")
            noFamiliaresMelanoma.isChecked=true

        if(historialFamiliarCita.familiares_con_cancer_melanoma != "1") {
            for (num in listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                listaCheckBox[num].isVisible = false
            }
            parentescoTitulo.isVisible = false
        }
        for (num in listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
            if (listaPar[num] in listaParientes)
                listaCheckBox[num].isChecked = true
        }
        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorHF1)
        botonAnterior.setOnClickListener {
            val intent = Intent(this@HistorialFamiliar1, HistorialPersonal3::class.java)
            startActivity(intent)
        }

        val botonSiguiente: MaterialButton = findViewById(R.id.botonSiguienteHF1)
        botonSiguiente.setOnClickListener {
            val intent = Intent(this@HistorialFamiliar1, HistorialFamiliar2::class.java)
            startActivity(intent)
        }
        if (historialFamiliarCita.parientes_con_cancer_melanoma.isNotEmpty()) {
            listaParientes =
                historialFamiliarCita.parientes_con_cancer_melanoma.split(',') as MutableList<String>
            for (num in listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                if (listaPar[num] in listaParientes)
                    listaCheckBox[num].isChecked = true
            }
        }
    }

    fun onRadioButtonFamiliaresMelanomaClicked(view: View) {
        val parentescoTitulo: TextView = findViewById(R.id.parentescoTitulo)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.siFamiliaresMelanoma ->
                    if (checked) {
                        historialFamiliarCita.familiares_con_cancer_melanoma = "1"
                        for (num in listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                            listaCheckBox[num].isVisible = true
                        }
                        parentescoTitulo.isVisible = true
                    }
                R.id.noFamiliaresMelanoma ->
                    if (checked) {
                        historialFamiliarCita.familiares_con_cancer_melanoma = "0"
                        for (num in listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                            listaCheckBox[num].isVisible = false
                            listaParientes = mutableListOf()
                            historialFamiliarCita.parientes_con_cancer_melanoma = ""
                            listaCheckBox[num].isChecked = false
                            parentescoTitulo.isVisible = false
                        }
                    }
            }
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.madreCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Madre"
                        listaParientes.add("Madre")
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Madre")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.padreCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Padre"
                        println(parientes)
                        listaParientes.add("Padre")
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Padre")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.hermanaCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Hermana"
                        listaParientes.add("Hermana")
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Hermana")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.hermanoCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Hermano"
                        println(parientes)
                        listaParientes.add("Hermano")
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Hermano")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.tiaMaternaCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="T??a Materna"
                        listaParientes.add("T??a Materna")
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("T??a Materna")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.tioMaternoCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="T??o Materno"
                        println(parientes)
                        listaParientes.add("T??o Materno")
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("T??o Materno")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.tiaPaternaCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="T??a Paterna"
                        listaParientes.add("T??a Paterna")
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("T??a Paterna")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.tioPaternoCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="T??o Paterno"
                        println(parientes)
                        listaParientes.add("T??o Paterno")
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("T??o Paterno")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.abuelaMaternaCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Abuela Materna"
                        listaParientes.add("Abuela Materna")
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Abuela Materna")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.abueloMaternoCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Abuelo Materno"
                        println(parientes)
                        listaParientes.add("Abuelo Materno")
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Abuelo Materno")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.abuelaPaternaCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Abuela Paterna"
                        listaParientes.add("Abuela Paterna")
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Abuela Paterna")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
                R.id.abueloPaternoCheck -> {
                    if (checked) {
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        parientes+="Abuelo Paterno"
                        println(parientes)
                        listaParientes.add("Abuelo Paterno")
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    } else {
                        listaParientes.remove("Abuelo Paterno")
                        var parientes = ""
                        for(pariente in listaParientes){
                            parientes+= "$pariente,"
                        }
                        if ( parientes.isNotEmpty()) {
                            parientes = parientes.substring(0, parientes.length - 1);
                        }
                        println(parientes)
                        historialFamiliarCita.parientes_con_cancer_melanoma = parientes
                    }
                }
            }
        }
    }
}