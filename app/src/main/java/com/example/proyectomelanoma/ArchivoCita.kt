package com.example.proyectomelanoma

import android.net.Uri

class ArchivoCita {
   var id_archivo = ""
   var id_cita = ""
   var nombre_archivo = ""
   var url_archivo= ""
   var tipo_archivo = ""
   lateinit var uri: Uri

   fun registrarArchivo(id_cita:String): String {
      var texto= "{id_cita: \"$id_cita\", id_archivo: \"1\", "
      if (nombre_archivo.isNotEmpty())
         texto+= "nombre_archivo: \"$nombre_archivo\", "
      else
         texto+= "nombre_archivo: \"\","

      if (url_archivo.isNotEmpty())
         texto+= "url_archivo: \"${url_archivo}\", "
      else
         texto+= "url_archivo: \"\","
      if (tipo_archivo.isNotEmpty())
         texto+= "tipo_archivo: \"$tipo_archivo\""
      else
         texto+= "tipo_archivo: \"\""
      texto+=",imagen_prediagnostico: null}"

      return texto
   }


}