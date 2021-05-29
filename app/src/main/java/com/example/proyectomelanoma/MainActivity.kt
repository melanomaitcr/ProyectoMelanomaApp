package com.example.proyectomelanoma


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import com.google.gson.Gson
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.android.volley.*
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.lang.Thread.sleep
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

var enviarHistorialesFamiliares = true
var enviarHistorialesPersonales = true
var cedulaP = ""
var auth_token = ""
var id_cita = ""
var expediente = Expediente()
var historialPersonalCita = HistorialPersonalCita()
var historialFamiliarCita = HistorialFamiliarCita()
var familiarOtroCancerLista = mutableListOf<FamiliarOtroCancer>()
var archivoCitaLista = mutableListOf<ArchivoCita>()
var cita = Cita()
var requestQueue:RequestQueue = RequestQueue(null, null)
class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val entradaCodigo:TextInputEditText = findViewById(R.id.codigoCita)
        val cajaCodigo:TextInputLayout = findViewById(R.id.cajaCita)
        val botonIngreso:MaterialButton = findViewById(R.id.botonIngreso)
        val entradaCedula:TextInputEditText = findViewById(R.id.numeroCedula)
        val cajaCedula:TextInputLayout = findViewById(R.id.cajaCedula)
        entradaCodigo.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (entradaCodigo.text.toString().isEmpty()) {
                    cajaCodigo.error = "Debe ingresar un código de cita"
                    botonIngreso.isEnabled = false
                    botonIngreso.background.setTint(Color.parseColor("#DEDEDE"))
                } else {
                    cajaCodigo.error = null
                    if (entradaCodigo.text.toString().isNotEmpty() && entradaCedula.text.toString().isDigitsOnly() && entradaCedula.text.toString().isNotEmpty()) {
                        botonIngreso.isEnabled = true
                        botonIngreso.background.setTint(Color.parseColor("#036262"))
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        entradaCodigo.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
            } else {
                if (entradaCodigo.text.toString().isEmpty()) {
                    cajaCodigo.error = "Debe ingresar un código de cita"
                    botonIngreso.isEnabled = false
                    botonIngreso.background.setTint(Color.parseColor("#DEDEDE"))
                }
            }
        }

        entradaCedula.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (entradaCedula.text.toString().isEmpty()) {
                    cajaCedula.error = "Debe ingresar un número de cédula"
                    botonIngreso.isEnabled = false
                    botonIngreso.background.setTint(Color.parseColor("#DEDEDE"))
                } else if (!entradaCedula.text.toString().isDigitsOnly()) {
                    cajaCedula.error = "La cédula debe contener únicamente números"
                    botonIngreso.isEnabled = false
                    botonIngreso.background.setTint(Color.parseColor("#DEDEDE"))

                } else {
                    cajaCedula.error = null
                    if (entradaCedula.text.toString().isNotEmpty() && entradaCodigo.text.toString().isNotEmpty()) {
                        botonIngreso.isEnabled = true
                        botonIngreso.background.setTint(Color.parseColor("#036262"))
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        entradaCedula.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
            } else {
                if (entradaCedula.text.toString().isEmpty()) {
                    cajaCedula.error = "Debe ingresar un número de cédula"
                    botonIngreso.isEnabled = false
                    botonIngreso.background.setTint(Color.parseColor("#DEDEDE"))
                }
            }
        }

        botonIngreso.setOnClickListener {
            this.revisarIngresoCita()
            //this.openFile()
        }
    }

    fun revisarIngresoCita(){
        requestQueue = RequestQueue(DiskBasedCache(cacheDir, 1024 * 1024), BasicNetwork(HurlStack()))
        requestQueue.start()
        val url = "https://melanomaitcr.pythonanywhere.com/api/ingreso-cita"
        val entradaCedula:TextInputEditText = findViewById(R.id.numeroCedula)
        val cedula:String = entradaCedula.text.toString()
        val entradaCodigo:TextInputEditText = findViewById(R.id.codigoCita)
        val codigo = entradaCodigo.text.toString()
        val datos = "{'cedula':'$cedula','clave':'$codigo'}"
        val data = JSONObject(datos)





        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, data,
                { response ->

                    id_cita = response["id_cita"] as String
                    auth_token = response["auth_token"] as String

                    cedulaP = cedula
                    cita.datos_ingresados_paciente = "1"
                    val gson = Gson()


                    val url2 = "https://melanomaitcr.pythonanywhere.com/api/cita/$id_cita"
                    val jsonObjectRequest2 = object: JsonObjectRequest(
                        Request.Method.GET, url2, null,
                        { response ->
                            cita = gson.fromJson(response.toString(), Cita::class.java)
                            cita.fecha_hora_cita=fechaReal(cita.fecha_hora_cita)
                            cita.datos_ingresados_paciente = "1"
                            val datosPersonalesRequest = object: JsonObjectRequest(
                                Request.Method.GET, "https://melanomaitcr.pythonanywhere.com/api/expediente/$cedula", null,
                                { response ->
                                    historialFamiliarCita.id_cita = id_cita
                                    historialPersonalCita.id_cita = id_cita
                                    expediente.numeroCedula = response["cedula"] as String
                                    expediente.nombreCompleto = response["nombre"] as String + " " + response["primer_apellido"] as String + " " + response["segundo_apellido"] as String
                                    expediente.correoElectronico = response["correo_electronico"] as String
                                    expediente.fechaNacimiento = formatDateFromDateString("E, dd MMM yyyy HH:mm:ss Z", "dd/MM/yyyy", response["fecha_nacimiento"] as String)
                                    expediente.nacionalidad = response["nacionalidad"] as String
                                    expediente.provincia = response["domicilio_provincia"] as String
                                    expediente.canton = response["domicilio_canton"] as String
                                    expediente.distrito = response["domicilio_distrito"] as String
                                    expediente.numeroTelefono = response["numero_telefono"] as Int
                                    expediente.identidadEtnica = response["identidad_etnica"] as String
                                    val intent = Intent(this, BienvenidaCita::class.java)
                                    startActivity(intent)
                                }, null) {
                                override fun getHeaders(): Map<String, String> {
                                    val headers = HashMap<String, String>()
                                    headers["auth-token"] = auth_token
                                    return headers
                                }
                            }
                            requestQueue.add(datosPersonalesRequest)
                        }, null
                    ){
                        override fun getHeaders(): Map<String, String> {
                            val headers = HashMap<String, String>()
                            headers["auth-token"] = auth_token
                            return headers
                        }
                    }

                    requestQueue.add(jsonObjectRequest2)


                },
                { error ->
                    if(error.message == "org.json.JSONException: Value FORMULARIO_RELLENADO of type java.lang.String cannot be converted to JSONObject") {
                        val layoutInflater2 = LayoutInflater.from(this)
                        val promptView2: View = layoutInflater2.inflate(R.layout.formulario_llenado, null)
                        val alertA = AlertDialog.Builder(this).create()
                        var botonEntendido2: MaterialButton = promptView2.findViewById(R.id.entendidoLlenado)
                        botonEntendido2.setOnClickListener {
                            alertA.dismiss()
                        }
                        alertA.setView(promptView2);
                        alertA.show();
                    }else {
                        val layoutInflater = LayoutInflater.from(this)
                        val promptView: View =
                            layoutInflater.inflate(R.layout.datos_incorrectos_dialog, null)
                        val alertD = AlertDialog.Builder(this).create()
                        var botonEntedido: MaterialButton = promptView.findViewById(R.id.entendido)
                        botonEntedido.setOnClickListener {
                            alertD.dismiss()
                        }
                        alertD.setView(promptView);
                        alertD.show();
                    }
                })
        requestQueue.add(jsonObjectRequest)
    }



}
@Throws(ParseException::class)
fun formatDateFromDateString(inputDateFormat: String, outputDateFormat: String,
                             inputDate: String): String {
    val mOutputDateString: String
    val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
    val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.ENGLISH)
    mOutputDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    val mParsedDate = mInputDateFormat.parse(inputDate) as Date
    mOutputDateString = mOutputDateFormat.format(mParsedDate as Date)

    return mOutputDateString
}

fun fechaReal(inputDate: String): String{
    val mParsedDate: Date
    val mInputDateFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
    mInputDateFormat.timeZone = TimeZone.getTimeZone("GMT");
    val mOutputDateFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss Z", Locale.ENGLISH)
    mOutputDateFormat.timeZone = TimeZone.getTimeZone("GMT+0000");
    mParsedDate = mInputDateFormat.parse(inputDate)
    var mOutputDateString = mOutputDateFormat.format(mParsedDate)
    mOutputDateString = mOutputDateString.replace("+0000","GMT-0600 (Central Standard Time)")

    return mOutputDateString
}


