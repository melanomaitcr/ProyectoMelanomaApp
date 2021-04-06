package com.example.proyectomelanoma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class CitaPaciente : AppCompatActivity() {
    private var auth_token:String = ""
    private var requestQueue: RequestQueue = RequestQueue(null, null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cita_paciente)
        this.requestQueue = RequestQueue(DiskBasedCache(cacheDir, 1024 * 1024), BasicNetwork(HurlStack()))
        this.requestQueue.start()
        this.auth_token = intent.getStringExtra("auth_token").toString()
        this.obtenerDatosCita()

        val botonSalir:ImageView = findViewById(R.id.imageView2)
        botonSalir.setOnClickListener {
            this.salir()
        }
    }

    @Throws(ParseException::class)
    fun formatDateFromDateString(inputDateFormat: String?, outputDateFormat: String?,
                                 inputDate: String?): String? {
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        mOutputDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        mParsedDate = mInputDateFormat.parse(inputDate)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)

        return mOutputDateString
    }

    private fun obtenerDatosCita(){
        val id_cita = intent.getStringExtra("id_cita").toString()
        val cedulaPaciente =intent.getStringExtra("cedula").toString()
        val numeroCedulaPF: TextInputEditText = findViewById(R.id.numeroCedulaPF)
        val numeroCedulaMF: TextInputEditText = findViewById(R.id.cedulaMedico)
        val fechaCita: TextInputEditText = findViewById(R.id.fechaCita)
        val horaCita: TextInputEditText = findViewById(R.id.horaCita)
        val nombrePaciente: TextInputEditText = findViewById(R.id.nombrePaciente)
        numeroCedulaPF.setText(cedulaPaciente)

        var url = "https://melanomaitcr.pythonanywhere.com/api/cita/$id_cita"
        val jsonObjectRequest = object : JsonObjectRequest(
                Method.GET, url, null,
                {   response ->
                    println(response)
                    numeroCedulaMF.setText(response["cedula_medico"] as String)
                    fechaCita.setText(formatDateFromDateString("EEE, d MMM yyyy HH:mm:ss Z", "dd/MM/yyyy",response["fecha_hora_cita"] as String))
                    horaCita.setText(formatDateFromDateString("EEE, d MMM yyyy HH:mm:ss Z", "hh:mm a",response["fecha_hora_cita"] as String))
                }, null) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["auth-token"] = auth_token
                return headers
            }
        }
        requestQueue.add(jsonObjectRequest)

        url = "https://melanomaitcr.pythonanywhere.com/api/expediente/$cedulaPaciente"
        val jsonObjectRequest2 = object : JsonObjectRequest(
                Method.GET, url, null,
                {   response ->
                    println(response)
                    nombrePaciente.setText(response["nombre"] as String +" "+ response["primer_apellido"] as String+" "+response["segundo_apellido"] as String)
                }, null) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["auth-token"] = auth_token
                return headers
            }
        }
        requestQueue.add(jsonObjectRequest2)

    }

    fun salir(){
        val intent = Intent(this@CitaPaciente, MainActivity::class.java)
        startActivity(intent)
    }
}

