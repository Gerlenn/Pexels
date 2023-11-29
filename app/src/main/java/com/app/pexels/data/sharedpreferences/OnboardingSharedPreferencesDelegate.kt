package com.app.pexels.data.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class OnboardingSharedPreferencesDelegate @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ReadWriteProperty<Any?, Boolean> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean =
        sharedPreferences.getBoolean(STATE_SCREEN, false)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        sharedPreferences.edit().putBoolean(STATE_SCREEN, value).apply()
    }

    companion object {

        private const val STATE_SCREEN = "stateScreen"
    }
}