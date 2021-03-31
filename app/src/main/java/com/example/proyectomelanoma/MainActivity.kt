package com.example.proyectomelanoma


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.button.MaterialButton
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var x:String =""
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val requestQueue: RequestQueue
        val cache: Cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
        val network: Network = BasicNetwork(HurlStack())
        requestQueue = RequestQueue(cache, network)
        requestQueue.start()
        val url = "https://melanomaitcr.pythonanywhere.com/api/inicio-sesion"
        val botonIngreso:MaterialButton = findViewById(R.id.botonIngreso)
        val data = JSONObject("{'cedula':'101110111','contrasenna':'melanoma2021'}");

        println(data);
        botonIngreso.setOnClickListener( View.OnClickListener{
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, data, { response ->
                    x= response["auth_token"] as String
                    println(response["auth_token"])
                }, { error-> print(error)
                })
            requestQueue.add(jsonObjectRequest)
        })

        val botonX:MaterialButton = findViewById(R.id.b)
        botonX.setOnClickListener( View.OnClickListener {
            println(x)
            val url = "https://melanomaitcr.pythonanywhere.com/api/usuario"
            val jsonObjectRequest2 = object : JsonObjectRequest(
                Method.GET, url, null, { response ->
                    println(response)
                }, null
            ) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("auth-token",x)
                    return headers
                }
            }
            requestQueue.add(jsonObjectRequest2)
        })

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




}
