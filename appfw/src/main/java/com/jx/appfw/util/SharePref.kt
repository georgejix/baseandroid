package com.jx.appfw.util

import android.content.Context
import android.content.SharedPreferences
import com.jx.appfw.config.Config

object SharePref {
    fun saveBoolean(context: Context, key: String, value: Boolean) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply()
    }

    fun getBoolean(context: Context, key: String, defvalue: Boolean): Boolean {
        return getSharedPreferences(context).getBoolean(key, defvalue)
    }

    fun saveString(context: Context, key: String, value: String) {
        getSharedPreferences(context).edit().putString(key, value).apply()
    }

    fun getString(context: Context, key: String, def: String): String? {
        return getSharedPreferences(context).getString(key, def)
    }

    fun saveInt(context: Context, key: String, value: Int) {
        getSharedPreferences(context).edit().putInt(key, value).apply()
    }

    fun getInt(context: Context, key: String, def: Int): Int {
        return getSharedPreferences(context).getInt(key, def)
    }

    /**
     * 清空SP
     */
    fun clear(context: Context) {
        getSharedPreferences(context).edit().clear().apply()
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Config.BASE_PROJECT, Context.MODE_PRIVATE)
    }
}