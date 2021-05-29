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
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.base.UiControllerImpl_Factory
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4ClassRunner::class)
class EDP12_R12{

    @Test
    fun R12_CP1() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.codigoCita)).perform(ViewActions.typeText("2bf3260c7e"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        sleep(2000)
        onView(ViewMatchers.withId(R.id.bienvenida)).check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    @Test
    fun R12_CP2() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.codigoCita)).perform(ViewActions.typeText("jdaskjdal"), ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        sleep(2000)
        onView(ViewMatchers.withId(R.id.datos_incorrectos)).check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    @Test
    fun R12_CP3() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("101xC0a57"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.codigoCita)).perform(ViewActions.typeText("2bf3260c7e"), ViewActions.closeSoftKeyboard())
        sleep(2000)
        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        onView(ViewMatchers.withText("La cédula debe contener únicamente números")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.botonIngreso)).check(ViewAssertions.matches(not(ViewMatchers.isEnabled())))
    }

    @Test
    fun R12_CP4() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.codigoCita)).perform( ViewActions.doubleClick(),ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"), ViewActions.closeSoftKeyboard())
        sleep(2000)
        onView(ViewMatchers.withId(R.id.botonIngreso)).perform(click())
        onView(ViewMatchers.withText("Debe ingresar un código de cita")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.botonIngreso)).check(ViewAssertions.matches(not(ViewMatchers.isEnabled())))
    }
}