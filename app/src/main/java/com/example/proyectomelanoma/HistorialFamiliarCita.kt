package com.example.proyectomelanoma

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class HistorialFamiliarCita {
    var id_cita = ""
    var rellenado_por_paciente = "1"
    var familiares_con_cancer_melanoma = ""
    var parientes_con_cancer_melanoma = ""
    var familiares_con_otro_tipo_cancer = ""

    fun registrarHistorialFamiliarCita(): String {
        var texto= "{id_cita: \"$id_cita\", rellenado_por_paciente: \"$rellenado_por_paciente\", "
        if (familiares_con_cancer_melanoma.isNotEmpty())
            texto+= "familiares_con_cancer_melanoma: \"$familiares_con_cancer_melanoma\", "
        else
            texto+= "familiares_con_cancer_melanoma: \"\","

        if (parientes_con_cancer_melanoma.isNotEmpty())
            texto+= "parientes_con_cancer_melanoma: \"$parientes_con_cancer_melanoma\", "
        else
            texto+= "parientes_con_cancer_melanoma: \"\","
        if (familiares_con_otro_tipo_cancer.isNotEmpty())
            texto+= "familiares_con_otro_tipo_cancer: \"$familiares_con_otro_tipo_cancer\""
        else
            texto+= "familiares_con_otro_tipo_cancer: \"\""
        texto+="}"

        println(texto)
        return texto
    }
}