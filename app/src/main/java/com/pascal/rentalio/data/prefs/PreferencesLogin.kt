package com.pascal.rentalio.data.prefs

import android.content.Context

object PreferencesLogin {

    private const val PREFS_NAME = "login_prefs"
    private const val IS_ADMIN = "is_admin"

    fun getIsAdmin(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_ADMIN, false)
    }
}
