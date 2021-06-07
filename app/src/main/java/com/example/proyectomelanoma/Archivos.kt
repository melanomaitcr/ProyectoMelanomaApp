package com.example.proyectomelanoma

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Base64
import android.view.Gravity
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import org.json.JSONObject

class Archivos : AppCompatActivity() {
    lateinit var lli: LinearLayout
    lateinit var lla: LinearLayout
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archivos)
        val botonNuevaImagen: MaterialButton = findViewById(R.id.nuevaImagen);
        val botonNuevoArchivo: MaterialButton = findViewById(R.id.nuevoArchivo)
        val botonFinalizarA1: MaterialButton = findViewById(R.id.botonFinalizarA1)
        val botonAnterior: MaterialButton = findViewById(R.id.botonAnteriorA1)
        lli = findViewById(R.id.linearLayoutImagenes);

        lla = findViewById(R.id.linearLayoutArchivos);
        botonNuevaImagen.setOnClickListener {
            openFile()
        }
        botonNuevoArchivo.setOnClickListener {
            openFile()
        }
        botonFinalizarA1.setOnClickListener {
            enviarDatos()
        }

        botonAnterior.setOnClickListener{
            val intent = Intent(this@Archivos, HistorialFamiliar2::class.java)
            startActivity(intent)
        }

        if (archivoCitaLista.isNotEmpty()){
            for (archivo in archivoCitaLista){
                if (archivo.tipo_archivo == "A"){
                    nuevoArchivo(lla, archivo, true)
                }else{
                    nuevaImagen(lli, archivo, true, archivo.uri)
                }
            }
        }
    }

    fun nuevoArchivo(ll: LinearLayout, archivo: ArchivoCita?, previo: Boolean){
        var archivoCita = ArchivoCita()
        if (previo)
            archivoCita= archivo!!
        val linearLayout= LinearLayout(ll.context)

        val clpTextInputLayout0 = LinearLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.layoutParams = clpTextInputLayout0
        ll.addView(linearLayout)
        val clpTextInputLayout = LinearLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,

        )
        val textView = TextView(linearLayout.context)
        textView.setText(if (archivoCita.nombre_archivo.length<25) archivoCita.nombre_archivo else archivoCita.nombre_archivo.substring(0,21)+"..."+archivoCita.nombre_archivo.substringAfter('.'))
        textView.layoutParams = clpTextInputLayout
        textView.textSize = 18F

        textView.setTextColor(Color.parseColor("#036262"))
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        linearLayout.addView(textView)
        val botonM = MaterialButton(linearLayout.context, null, R.attr.borderlessButtonStyle)
        val clpTextInputLayout3 = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        clpTextInputLayout3.bottomMargin = 15
        clpTextInputLayout3.leftMargin = 25
        clpTextInputLayout3.width = 60
        AppCompatResources.getDrawable(this,R.drawable.baseline_delete_24)?.let {
            DrawableCompat.setTint(
                it, Color.parseColor("#036262"))
        };

        botonM.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_delete_24, 0, 0, 0);
        botonM.setLayoutParams(clpTextInputLayout3)
        botonM.cornerRadius = 60
        botonM.setPadding(0,0,1,0)
        botonM.setBackgroundColor(Color.parseColor("#FFFFFF"))
        botonM.isAllCaps=false
        var archivosTexto: TextView = findViewById(R.id.archivosTexto)
        archivosTexto.isVisible = false
        botonM.setOnClickListener {
            archivoCitaLista.remove(archivoCita)
            println(archivoCitaLista)
            ll.removeView(linearLayout)
            if(ll.childCount==1) {
                var archivosTexto: TextView = findViewById(R.id.archivosTexto)
                archivosTexto.isVisible = true
            }
        }
        linearLayout.addView(botonM)

    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun nuevaImagen(ll: LinearLayout, archivo: ArchivoCita?, previo: Boolean, bitmap:Uri){

        var archivoCita = ArchivoCita()
        if (previo)
            archivoCita= archivo!!
        val linearLayoutNombre = LinearLayout(ll.context)
        val clpTextInputLayoutNombre = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayoutNombre.layoutParams = clpTextInputLayoutNombre
        linearLayoutNombre.gravity = Gravity.CENTER_HORIZONTAL
        ll.addView(linearLayoutNombre)
        val textView = TextView(linearLayoutNombre.context)
        textView.setText(if (archivoCita.nombre_archivo.length<25) archivoCita.nombre_archivo else archivoCita.nombre_archivo.substring(0,21)+"..."+archivoCita.nombre_archivo.substringAfter('.'))
        val clpTextInputLayout = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
        )
        textView.setLayoutParams(clpTextInputLayout)
        textView.textSize = 18F
        textView.setTextColor(Color.parseColor("#036262"))
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        linearLayoutNombre.addView(textView)
        var imgView = ImageView(ll.context)
        imgView.setImageURI(bitmap)
        val clpTextInputLayout2 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        //clpTextInputLayout2.width = 300
        imgView.layoutParams = clpTextInputLayout2
        imgView.layoutParams.height = 350
        imgView.layoutParams.width = 350


        ll.addView(imgView)
        val botonM = MaterialButton(linearLayoutNombre.context, null, R.attr.borderlessButtonStyle)
        val clpTextInputLayout3 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        clpTextInputLayout3.leftMargin = 25
        clpTextInputLayout3.width = 60
        AppCompatResources.getDrawable(this,R.drawable.baseline_delete_24)?.let {
            DrawableCompat.setTint(
                it, Color.parseColor("#036262"))
        };
        botonM.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_delete_24, 0, 0, 0);
        botonM.cornerRadius = 60
        botonM.setPadding(0,0,1,0)
        botonM.setBackgroundColor(Color.parseColor("#FFFFFF"))
        botonM.setLayoutParams(clpTextInputLayout3)
        botonM.isAllCaps=false
        var imagenesTexto: TextView = findViewById(R.id.imagenesTexto)
        imagenesTexto.isVisible = false
        botonM.setOnClickListener {
            archivoCitaLista.remove(archivoCita)
            println(archivoCitaLista)
            ll.removeView(imgView)

            ll.removeView(linearLayoutNombre)
            if(ll.childCount==1) {
                var imagenesTexto: TextView = findViewById(R.id.imagenesTexto)
                imagenesTexto.isVisible = true
            }
        }
        linearLayoutNombre.addView(botonM)
    }

    fun enviarDatos (){
        var texto = ""
        var data: JSONObject
        var url = ""
        var jsonObjectRequest: JsonObjectRequest
        url = "https://melanomaitcr.pythonanywhere.com/api/historialFamiliarCita"
        texto = historialFamiliarCita.registrarHistorialFamiliarCita()
        data = JSONObject(texto);
        jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, data, { response ->
                println(response);
            }, { error ->
                println(error)
            })
        requestQueue.add(jsonObjectRequest)
        historialFamiliarCita.rellenado_por_paciente = "0"
        texto = historialFamiliarCita.registrarHistorialFamiliarCita()
        data = JSONObject(texto);
        jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, data, { response ->
                println(response);
            }, { error ->
                println(error)
            })
        requestQueue.add(jsonObjectRequest)
        if (familiarOtroCancerLista.isNotEmpty()) {
            for (familiar in familiarOtroCancerLista) {
                if (familiar.parentesco.isNotEmpty() || familiar.tipo_cancer.isNotEmpty()) {
                    url = "https://melanomaitcr.pythonanywhere.com/api/familiarOtroCancer"
                    texto = familiar.retornarFamiliarOtroCancer(id_cita)
                    data = JSONObject(texto);
                    jsonObjectRequest = JsonObjectRequest(
                        Request.Method.POST, url, data, { response ->
                            println(response);
                        }, { error ->
                            println(error)
                        })
                    requestQueue.add(jsonObjectRequest)
                    familiar.rellenado_por_paciente = "0"
                    texto = familiar.retornarFamiliarOtroCancer(id_cita)
                    data = JSONObject(texto);
                    jsonObjectRequest = JsonObjectRequest(
                        Request.Method.POST, url, data, { response ->
                            println(response);
                        }, { error ->
                            println(error)
                        })
                    requestQueue.add(jsonObjectRequest)
                }
            }
        }
        historialFamiliarCita = HistorialFamiliarCita()
        familiarOtroCancerLista = mutableListOf<FamiliarOtroCancer>()

        url = "https://melanomaitcr.pythonanywhere.com/api/historialPersonalCita"
        texto = historialPersonalCita.retornarHistorialPersonalCita()
        data = JSONObject(texto);
        jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, data, { response ->
                println(response);
            }, { error ->
                println(error)
            })
        requestQueue.add(jsonObjectRequest)
        historialPersonalCita.rellenado_por_paciente = "0"
        texto = historialPersonalCita.retornarHistorialPersonalCita()
        data = JSONObject(texto);
        jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, data, { response ->
                println(response);
            }, { error ->
                println(error)
            })
        requestQueue.add(jsonObjectRequest)
        historialPersonalCita = HistorialPersonalCita()
        if (archivoCitaLista.isNotEmpty()) {
            for (archivo in archivoCitaLista) {
                url = "https://melanomaitcr.pythonanywhere.com/api/archivoCita"
                data = JSONObject(archivo.registrarArchivo(id_cita))
                jsonObjectRequest = JsonObjectRequest(
                        Request.Method.POST, url, data,
                        { response ->
                            println(response)
                        }, null
                )
                requestQueue.add(jsonObjectRequest)
            }
        }
        archivoCitaLista =  mutableListOf()
        val gson= Gson()
        val datosIngresadosRequest = object: JsonObjectRequest(

            Request.Method.PUT, "https://melanomaitcr.pythonanywhere.com/api/cita/$id_cita",JSONObject(gson.toJson(cita)) ,
            { response ->
                println(response)
            }, {error->println(error)}
        ){
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["auth-token"] = auth_token
                return headers
            }
        }
        requestQueue.add(datosIngresadosRequest)

        val intent = Intent(this@Archivos, FinalizacionCita::class.java)
        startActivity(intent)
    }

    fun openFile() {
        val intent = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Intent(Intent.ACTION_OPEN_DOCUMENT)
        } else {
            TODO("VERSION.SDK_INT < KITKAT")
        }
        //intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        val REQUEST_SAF_PICK_IMAGE = 1
        startActivityForResult(intent, REQUEST_SAF_PICK_IMAGE)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                println(resultData.clipData != null)
                if (resultData.clipData != null) {

                    for (index in 0 until resultData.clipData!!.itemCount) {
                        println(index)
                        resultData.clipData!!.getItemAt(index).uri.let { returnUri ->
                            contentResolver.query(returnUri, null, null, null, null)
                        }?.use { cursor ->
                            cursor.moveToFirst()
                            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                            try {
                                var filePath = resultData.clipData!!.getItemAt(index).uri
                                var inputStream = contentResolver.openInputStream(filePath!!)
                                var xy = Base64.encodeToString(inputStream!!.readBytes(), Base64.DEFAULT)
                                var archivo = ArchivoCita()
                                archivo.nombre_archivo = cursor.getString(nameIndex)
                                println(archivo.nombre_archivo)
                                if (".jpg" in archivo.nombre_archivo || ".jpeg" in archivo.nombre_archivo || ".png" in archivo.nombre_archivo || ".svg" in archivo.nombre_archivo || ".gif" in archivo.nombre_archivo) {
                                    archivo.tipo_archivo = "I"
                                    archivo.url_archivo = xy
                                    archivoCitaLista.add(archivo)
                                    archivo.uri = resultData.clipData!!.getItemAt(index).uri
                                    nuevaImagen(lli, archivo, true, archivo.uri)
                                } else {
                                    archivo.tipo_archivo = "A"
                                    archivo.url_archivo = xy
                                    archivoCitaLista.add(archivo)
                                    nuevoArchivo(lla, archivo, true)
                                }

                                /*val url = "https://melanomaitcr.pythonanywhere.com/api/archivoCita"
                                val json = JSONObject(archivo.registrarArchivo("2"))
                                val jsonObjectRequest = JsonObjectRequest(
                                        Request.Method.POST, url, json,
                                        { response ->
                                            println(response)
                                        }, null
                                )
                                requestQueue.add(jsonObjectRequest)
                                cursor.moveToNext()*/
                            } catch (ex: Exception) {

                            }
                        }
                    }
                } else {
                    resultData.data?.let { returnUri ->
                        contentResolver.query(returnUri, null, null, null, null)
                    }?.use { cursor ->
                        cursor.moveToFirst()
                        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        try {
                            var filePath = resultData.data
                            var inputStream = contentResolver.openInputStream(filePath!!)

                            //var x  = encodeBitmapImage(bitmap)
                            //println(x.size)
                            var xy = Base64.encodeToString(inputStream!!.readBytes(), Base64.DEFAULT)
                            var archivo = ArchivoCita()
                            archivo.nombre_archivo = cursor.getString(nameIndex)
                            println(archivo.nombre_archivo)
                            if (".jpg" in archivo.nombre_archivo || ".jpeg" in archivo.nombre_archivo || ".png" in archivo.nombre_archivo || ".svg" in archivo.nombre_archivo || ".gif" in archivo.nombre_archivo) {
                                var bitmap = BitmapFactory.decodeStream(inputStream)
                                archivo.tipo_archivo = "I"
                                archivo.url_archivo = xy
                                archivoCitaLista.add(archivo)
                                archivo.uri = resultData.data!!
                                nuevaImagen(lli, archivo, true, resultData.data!!)
                            } else {
                                archivo.tipo_archivo = "A"
                                archivo.url_archivo = xy
                                archivoCitaLista.add(archivo)
                                nuevoArchivo(lla, archivo, true)
                            }
                        } catch (ex: Exception) {

                        }
                    }
                }
            }
        }
    }
    override fun onBackPressed() {}
}