package com.example.assignment.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

fun saveStringToPref(context: Context, prefName: String, key: String, value: String) {
    val pref: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    pref.edit {
        putString(key, value)
        apply()
    }
}

fun getStringFromPref(context: Context, prefName: String, key: String) : String? {
    val pref: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    return pref.getString(key, null)
}