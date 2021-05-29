package com.example.proyectomelanoma

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule

class BienvenidaCitaTest{
    @get: Rule
    val activity =ActivityScenarioRule(BienvenidaCita::class.java)
}