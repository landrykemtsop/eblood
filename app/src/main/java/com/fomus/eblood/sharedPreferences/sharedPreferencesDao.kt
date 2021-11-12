package com.fomus.eblood.sharedPreferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.provider.Settings.Global.putString
import com.fomus.eblood.R

class sharedPreferencesDao() {
    private lateinit var prefs: SharedPreferences

    private val PREFS_NAME = "params"

    val ID_USER = "id_user"
    val TOKEN = "token"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var userEmail: String?
        get() = prefs.getString(ID_USER, null)
        set(value: String?) {
            val prefsEditor: SharedPreferences.Editor = prefs.edit()
            with(prefsEditor) {
                putString(ID_USER, value)
                commit()
            }
        }
    var userId: String?
        get() = prefs.getString(TOKEN, null)
        set(value: String?) {
            val prefsEditor: SharedPreferences.Editor = prefs.edit()
            with(prefsEditor) {
                putString(TOKEN, value)
                commit()
            }
        }


}