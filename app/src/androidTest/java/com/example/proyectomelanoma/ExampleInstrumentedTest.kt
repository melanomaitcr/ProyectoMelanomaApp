package com.example.proyectomelanoma

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class  ExampleInstrumentedTest{

    @Test
    fun R12_CP1() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.numeroCedula)).perform(ViewActions.typeText("301420857"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.codigoCita)).perform(ViewActions.typeText("2bf3260c7e"),ViewActions.closeSoftKeyboard())


        onView(withId(R.id.botonIngreso)).perform(click())

        onView(withId(R.id.bienvenida)).check(matches(isDisplayed()))

    }
}