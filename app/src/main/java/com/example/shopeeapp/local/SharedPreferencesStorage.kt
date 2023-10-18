package com.example.shopeeapp.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.reflect.KClass

class SharedPreferencesStorage @Inject constructor(@ApplicationContext context: Context) : LocalStorage {

    private val sharedPref = context.getSharedPreferences("Special", Context.MODE_PRIVATE)

    override fun putString(key: String, value: String?) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    override fun remove(key: String) {
        with(sharedPref.edit()) {
            remove(key)
            apply()
        }
    }

    override fun <T : Any> putData(key: String, value: T?) {
        if (value != null) {
            val str = Gson().toJson(value)
            putString(key, str)
        } else putString(key, null)
    }

    override fun <T : Any> getData(key: String): T? {
        val string = getString(key) ?: return null
        try {
            return Gson().fromJson(string, object : TypeToken<T>() {}.type)
        } catch (_: Exception) {

        }
        return null
    }

    override fun <T : Any> getData(key: String, clazz: KClass<T>): T? {
        val string = getString(key) ?: return null
        try {
            return Gson().fromJson(string, clazz.java)
        } catch (_: Exception) {

        }
        return null
    }

    override var isFirstTime: Int
        get() = getData("first_time_open", Int::class) ?: 0
        set(value) {
            putData("first_time_open", value)
        }
}