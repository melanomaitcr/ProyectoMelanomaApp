package com.example.proyectomelanoma


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.android.volley.*
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {
    private var requestQueue:RequestQueue = RequestQueue(null, null)

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
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (entradaCodigo.text.toString().isEmpty()) {
                    cajaCodigo.error = "Debe ingresar un código de cita"
                    botonIngreso.isEnabled = false
                } else {
                    cajaCodigo.error = null
                    if (entradaCedula.text.toString().isNotEmpty()) botonIngreso.isEnabled = true
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
                }
            }
        }

        entradaCedula.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (entradaCedula.text.toString().isEmpty()) {
                    cajaCedula.error = "Debe ingresar un número de cédula"
                    botonIngreso.isEnabled = false
                } else if (!entradaCedula.text.toString().isDigitsOnly()) {
                    cajaCedula.error = "La cédula debe contener únicamente números"
                    botonIngreso.isEnabled = false
                } else {
                    cajaCedula.error = null
                    if (entradaCedula.text.toString().isNotEmpty()) botonIngreso.isEnabled = true
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
        println(datos)
        val data = JSONObject(datos)
        println(data["cedula"])
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, data,
                { response ->
                    //x= response["auth_token"] as String
                    println(response)
                    val intent = Intent(this@MainActivity, CitaPaciente::class.java).apply {
                        putExtra("auth_token", response["auth_token"] as String)
                        putExtra("id_cita", response["id_cita"] as String)
                        putExtra("cedula", cedula)
                    }
                    startActivity(intent)
                },
                { error ->

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
