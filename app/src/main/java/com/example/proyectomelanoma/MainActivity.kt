package com.example.proyectomelanoma


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
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
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


var cedulaP = ""
var auth_token = ""
var id_cita = ""
var expediente = Expediente()
var historialPersonalCita = HistorialPersonalCita()
var historialFamiliarCita = HistorialFamiliarCita()
var familiarOtroCancerLista = mutableListOf<FamiliarOtroCancer>()
var archivoCitaLista = mutableListOf<ArchivoCita>()

class MainActivity : AppCompatActivity() {
    private var requestQueue:RequestQueue = RequestQueue(null, null)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestQueue = RequestQueue(DiskBasedCache(cacheDir, 1024 * 1024), BasicNetwork(HurlStack()))
        this.requestQueue.start()


        val entradaCodigo:TextInputEditText = findViewById(R.id.codigoCita)
        val cajaCodigo:TextInputLayout = findViewById(R.id.cajaCita)
        val botonIngreso:MaterialButton = findViewById(R.id.botonIngreso)
        val entradaCedula:TextInputEditText = findViewById(R.id.numeroCedula)
        val cajaCedula:TextInputLayout = findViewById(R.id.cajaCedula)
        entradaCodigo.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (entradaCodigo.text.toString().isEmpty()) {
                    cajaCodigo.error = "Debe ingresar un código de cita"
                    botonIngreso.isEnabled = false
                    botonIngreso.background.setTint(Color.parseColor("#DEDEDE"))
                } else {
                    cajaCodigo.error = null
                    if (entradaCedula.text.toString().isNotEmpty()) {
                        botonIngreso.isEnabled = true
                        botonIngreso.background.setTint(Color.parseColor("#AAAAAA"))
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
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
                    if (entradaCedula.text.toString().isNotEmpty()) {
                        botonIngreso.isEnabled = true
                        botonIngreso.background.setTint(Color.parseColor("#AAAAAA"))
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
        }
        /**
         * val url = "https://melanomaitcr.pythonanywhere.com/api/usuario"
         * botonIngreso.setOnClickListener(View.OnClickListener {
            println(url)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response: JSONObject ->
                    Toast.makeText(
                        applicationContext,
                        "Response :$response",
                        Toast.LENGTH_LONG
                    ).show() //display the response on screen
                    try {
                        //JSONArray arreglo = (JSONArray) response.get("data");
                        //JSONObject var = (JSONObject) arreglo.get(1);
                        //System.out.println(var.get("cedula"));
                        val x = JSONObject()
                        x.put("cedula", "32173216")
                        val jsonObjectRequest1 =
                            JsonObjectRequest(
                                Request.Method.DELETE,
                                url.toString() + "/" + "32173216",
                                x,
                                { response1: JSONObject? ->
                                    println(
                                        response
                                    )
                                }
                            ) { error: VolleyError ->
                                println(
                                    error.toString()
                                )
                            }
                        requestQueue.add(jsonObjectRequest1)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            ) { error: VolleyError -> println(error.networkResponse.statusCode) }

            // Access the RequestQueue through your singleton class.
            requestQueue.add(jsonObjectRequest)
        })**/

    }

    fun revisarIngresoCita(){
        val url = "https://melanomaitcr.pythonanywhere.com/api/ingreso-cita"
        val entradaCedula:TextInputEditText = findViewById(R.id.numeroCedula)
        val cedula:String = entradaCedula.text.toString()
        val entradaCodigo:TextInputEditText = findViewById(R.id.codigoCita)
        val codigo = entradaCodigo.text.toString()
        val datos = "{'cedula':'$cedula','clave':'$codigo'}"
        val data = JSONObject(datos)
        val datosPersonalesRequest = object: JsonObjectRequest(
            Request.Method.GET, "https://melanomaitcr.pythonanywhere.com/api/expediente/$cedula", null,
            {   response ->
                expediente.numeroCedula = response["cedula"] as String
                expediente.nombreCompleto = response["nombre"] as String +" "+ response["primer_apellido"] as String +" "+ response["segundo_apellido"] as String
                expediente.correoElectronico = response["correo_electronico"] as String
                expediente.fechaNacimiento = formatDateFromDateString("EEE, d MMM yyyy HH:mm:ss Z", "dd/MM/yyyy",response["fecha_nacimiento"] as String)
                expediente.nacionalidad = response["nacionalidad"] as String
                expediente.provincia = response["domicilio_provincia"] as String
                expediente.canton = response["domicilio_canton"] as String
                expediente.distrito = response["domicilio_distrito"] as String
                expediente.numeroTelefono = response["numero_telefono"] as Int
                expediente.identidadEtnica = response["identidad_etnica"] as String
                val intent = Intent(this@MainActivity, BienvenidaCita::class.java)
                startActivity(intent)
            }, {error->
        println(error)}) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["auth-token"] = auth_token
                return headers
            }
        }
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, data,
                { response ->
                    id_cita = response["id_cita"] as String
                    auth_token = response["auth_token"] as String
                    cedulaP = cedula
                    println("hola")
                    requestQueue.add(datosPersonalesRequest)

                },
                { error ->
                    println("quePutas")
                    val layoutInflater = LayoutInflater.from(this)
                    val promptView: View = layoutInflater.inflate(R.layout.datos_incorrectos_dialog, null)
                    val alertD = AlertDialog.Builder(this).create()
                    var botonEntedido: MaterialButton = promptView.findViewById(R.id.entendido)
                    botonEntedido.setOnClickListener {
                        alertD.dismiss()
                    }
                    alertD.setView(promptView);
                    alertD.show();
                })
        requestQueue.add(jsonObjectRequest)
    }
    public fun X(){

        val botonX:MaterialButton = findViewById(R.id.botonIngreso)
        botonX.setOnClickListener {
            //println(x)
            val url = "https://melanomaitcr.pythonanywhere.com/api/usuario"
            val jsonObjectRequest2 = object : JsonObjectRequest(
                    Method.GET, url, null, { response ->
                println(response)
            }, null
            ) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    //headers.put("auth-token",x)
                    return headers
                }
            }
            //requestQueue.add(jsonObjectRequest2)
        }
    }


}
@Throws(ParseException::class)
fun formatDateFromDateString(inputDateFormat: String?, outputDateFormat: String?,
                             inputDate: String?): String {
    val mParsedDate: Date
    val mOutputDateString: String
    val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
    mOutputDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    mParsedDate = mInputDateFormat.parse(inputDate)
    mOutputDateString = mOutputDateFormat.format(mParsedDate)

    return mOutputDateString
}
