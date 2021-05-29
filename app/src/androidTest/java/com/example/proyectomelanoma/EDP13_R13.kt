package com.example.proyectomelanoma

import android.app.Activity
import android.app.Instrumentation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.base.UiControllerImpl_Factory
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.hamcrest.Matchers.not
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4ClassRunner::class)
class EDP13_R13{

    @Test
    fun R13_CP1() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.codigoCita)).perform(ViewActions.typeText("2bf3260c7e"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        sleep(2000)
        onView(ViewMatchers.withId(R.id.botonBienvenida)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonInfoPersonal)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteInfoContacto)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteDomicilio)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.pesoText)).perform(ViewActions.typeText("55"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.estaturaText)).perform(ViewActions.typeText("166"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.IMCText)).check(ViewAssertions.matches(ViewMatchers.withText("19.959")))
        onView(ViewMatchers.withId(R.id.siActividadFisica)).perform(click())
        onView(ViewMatchers.withId(R.id.cantidadMinutosText)).perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonSiguienteHP1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.siHaFumado)).perform(click())
        onView(ViewMatchers.withId(R.id.noFumaActualmente)).perform(click())
        onView(ViewMatchers.withId(R.id.edadFumarText)).perform(ViewActions.typeText("26"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.mesesFumandoText)).perform(ViewActions.typeText("6"),ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonSiguienteHP2)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.siConsumeBebidas)).perform(click())
        onView(ViewMatchers.withId(R.id.bebidasDiarias2)).perform(click())
        onView(ViewMatchers.withId(R.id.siOtroCancer)).perform(click())
        onView(ViewMatchers.withId(R.id.tipoCancerText)).perform(ViewActions.typeText("cuello"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonSiguienteHP3)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHF1)).perform(click())
        sleep(1000)
        enviarHistorialesFamiliares = false
        enviarHistorialesPersonales = true
        onView(ViewMatchers.withId(R.id.botonSiguienteHF2)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonFinalizarA1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSalir)).perform(click())
        val jsonObjectTest = JSONObject("{\"consume_bebidas_alcoholicas\":\"1\",\"consume_bebidas_alcoholicas_total\":1,\"consume_bebidas_alcoholicas_total_otro\":\"Más de 2 bebidas diarias\",\"diagnostico_propio_cancer\":\"1\",\"edad_empezo_fumar\":26,\"estatura_cm\":166.0,\"fuma_actualmente\":\"0\",\"fuma_o_ha_fumado\":\"1\",\"id_cita\":\"2\",\"imc\":19.959,\"minutos_semana_actividad_fisica\":100,\"periodo_fumado\":\"6\",\"peso_kg\":55.0,\"realizacion_actividad_fisica\":\"1\",\"rellenado_por_paciente\":\"1\",\"tipos_cancer_propios\":\"cuello\"}")
        val url = "https://melanomaitcr.pythonanywhere.com/api/historialPersonalCita/2/1"
        var jsonObjectTestRequested = JSONObject()
        val jsonObjectTestRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                jsonObjectTestRequested = response
                assertEquals(jsonObjectTest.toString(),jsonObjectTestRequested.toString())
            }, { error ->
                println(error)
            })
        requestQueue.add(jsonObjectTestRequest)


    }

    @Test
    fun R13_CP2() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.codigoCita)).perform(ViewActions.typeText("2bf3260c7e"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonBienvenida)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonInfoPersonal)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteInfoContacto)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteDomicilio)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHP1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHP2)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHP3)).perform(click())
        enviarHistorialesPersonales = false
        sleep(1000)
        onView(ViewMatchers.withId(R.id.siFamiliaresMelanoma)).perform(click())
        onView(ViewMatchers.withId(R.id.madreCheck)).perform(click())
        onView(ViewMatchers.withId(R.id.abueloPaternoCheck)).perform(click())
        onView(ViewMatchers.withId(R.id.hermanaCheck)).perform(click())
        onView(ViewMatchers.withId(R.id.botonSiguienteHF1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.siFamiliaresOtroCancer)).perform(click())
        onView(ViewMatchers.withId(R.id.botonNuevoPariente)).perform(click())
        onView(ViewMatchers.withHint("Parentesco")).perform(ViewActions.typeText("Madre"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withHint("Tipo de Cáncer")).perform(ViewActions.typeText("Mama"), ViewActions.closeSoftKeyboard())
        enviarHistorialesFamiliares = true
        onView(ViewMatchers.withId(R.id.botonSiguienteHF2)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonFinalizarA1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSalir)).perform(click())
        val jsonObjectTest = JSONObject("{\"familiares_con_cancer_melanoma\":\"1\",\"familiares_con_otro_tipo_cancer\":\"1\",\"id_cita\":\"2\",\"parientes_con_cancer_melanoma\":\"Madre,Abuelo Paterno,Hermana\",\"rellenado_por_paciente\":\"1\"}")
        var jsonObjectTestRequested = JSONObject()
        val url = "https://melanomaitcr.pythonanywhere.com/api/historialFamiliarCita/2/1"
        val jsonObjectTestRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                jsonObjectTestRequested = response
                assertEquals(jsonObjectTest.toString(),jsonObjectTestRequested.toString())
            }, { error ->
                println(error)
            })
        requestQueue.add(jsonObjectTestRequest)
    }

    @Test
    fun R13_CP3() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.codigoCita)).perform(ViewActions.typeText("2bf3260c7e"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonBienvenida)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonInfoPersonal)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteInfoContacto)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteDomicilio)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHP1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHP2)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHP3)).perform(click())
        enviarHistorialesPersonales = false
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHF1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSiguienteHF2)).perform(click())
        enviarHistorialesFamiliares = false
        sleep(1000)
        //onView(ViewMatchers.withId(R.id.nuevaImagen)).perform(click, pressBack())
        sleep(2000)
        //onView(ViewMatchers.withText("Imagen JPG.jpg")).perform(click())
        onView(ViewMatchers.withId(R.id.botonFinalizarA1)).perform(click())
        sleep(1000)
        onView(ViewMatchers.withId(R.id.botonSalir)).perform(click())
    }
}