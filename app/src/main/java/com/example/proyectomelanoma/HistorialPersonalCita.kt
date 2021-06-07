package com.example.proyectomelanoma

class HistorialPersonalCita {
    var id_cita = ""
    var rellenado_por_paciente = "1"
    var peso_kg = ""
    var estatura_cm = ""
    var imc = ""
    var realizacion_actividad_fisica = ""
    var minutos_semana_actividad_fisica = ""
    var diagnostico_propio_cancer = ""
    var tipos_cancer_propios = ""
    var fuma_o_ha_fumado = ""
    var edad_empezo_fumar = ""
    var fuma_actualmente = ""
    var periodo_fumado = ""
    var consume_bebidas_alcoholicas = ""
    var consume_bebidas_alcoholicas_total = ""
    var consume_bebidas_alcoholicas_total_otro = ""

    fun retornarHistorialPersonalCita(): String {
        imc = imc.replace(",",".")
        var texto= "{id_cita: \"$id_cita\", rellenado_por_paciente: \"$rellenado_por_paciente\", "
        if (peso_kg.isNotEmpty())
            texto+= "peso_kg: $peso_kg, "
        else
            texto+= "peso_kg: 0,"

        if (estatura_cm.isNotEmpty())
            texto+= "estatura_cm: $estatura_cm, "
        else
            texto+= "estatura_cm: 0,"
        if (imc.isNotEmpty())
            texto+= "imc: $imc, "
        else
            texto+= "imc: 0,"
        if (realizacion_actividad_fisica.isNotEmpty())
            texto+= "realizacion_actividad_fisica: \"$realizacion_actividad_fisica\", "
        else
            texto+= "realizacion_actividad_fisica: \"\", "

        if (minutos_semana_actividad_fisica.isNotEmpty())
            texto+= "minutos_semana_actividad_fisica: $minutos_semana_actividad_fisica, "
        else
            texto+= "minutos_semana_actividad_fisica: 0,"

        if (diagnostico_propio_cancer.isNotEmpty())
            texto+= "diagnostico_propio_cancer: \"$diagnostico_propio_cancer\", "
        else
            texto+= "diagnostico_propio_cancer: \"\","
        if (tipos_cancer_propios.isNotEmpty())
            texto+= "tipos_cancer_propios: \"$tipos_cancer_propios\", "
        else
            texto+= "tipos_cancer_propios: \"\","

        if (fuma_o_ha_fumado.isNotEmpty())
            texto+= "fuma_o_ha_fumado: \"$fuma_o_ha_fumado\", "
        else
            texto+= "fuma_o_ha_fumado: \"\", "

        if (edad_empezo_fumar.isNotEmpty())
            texto+= "edad_empezo_fumar: $edad_empezo_fumar, "
        else
            texto+= "edad_empezo_fumar: \"\", "

        if (fuma_actualmente.isNotEmpty())
            texto+= "fuma_actualmente: \"$fuma_actualmente\", "
        else
            texto+= "fuma_actualmente: \"\", "

        if (periodo_fumado.isNotEmpty())
            texto+= "periodo_fumado: \"$periodo_fumado\", "
        else
            texto+= "periodo_fumado: \"\", "

        if (consume_bebidas_alcoholicas.isNotEmpty())
            texto+= "consume_bebidas_alcoholicas: \"$consume_bebidas_alcoholicas\", "
        else
            texto+= "consume_bebidas_alcoholicas: \"\", "

        if (consume_bebidas_alcoholicas_total.isNotEmpty())
            texto+= "consume_bebidas_alcoholicas_total: \"$consume_bebidas_alcoholicas_total\", "
        else
            texto+= "consume_bebidas_alcoholicas_total: \"\", "

        if (consume_bebidas_alcoholicas_total_otro.isNotEmpty())
            texto+= "consume_bebidas_alcoholicas_total_otro: \"$consume_bebidas_alcoholicas_total_otro\""
        else
            texto+= "consume_bebidas_alcoholicas_total_otro: \"\""
        texto+="}"

        println(texto)
        return texto
    }
}