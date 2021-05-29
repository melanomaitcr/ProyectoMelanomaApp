package com.example.proyectomelanoma

class FamiliarOtroCancer {
    var id_cita = ""
    var rellenado_por_paciente = "1"
    var tipo_cancer = ""
    var parentesco = ""

    fun retornarFamiliarOtroCancer(id_cita_: String): String{
        var texto= "{id_cita: \"$id_cita_\", rellenado_por_paciente: \"$rellenado_por_paciente\", "
        texto+= "tipo_cancer: \"$tipo_cancer\", "
        texto+= "parentesco: \"$parentesco\"}"
        return texto
    }
}
