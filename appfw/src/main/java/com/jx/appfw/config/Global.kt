package com.jx.appfw.config

import android.content.Context
import com.jx.appfw.util.SharePref

object Global {
    /**
     * 加密串
     */
    private val SP_USER_TOKEN = "USER_TOKEN"

    /**
     * 加密串
     */
    fun getUserToken(context: Context): String? {
        return SharePref.getString(context, SP_USER_TOKEN, "")
    }

    fun saveUserToken(context: Context, userToken: String) {
        SharePref.saveString(context, SP_USER_TOKEN, userToken)
    }

    /**
     * 清空 SharePrefSetting 的  SP
     */
    fun clearAll(context: Context) {
        SharePref.clear(context)
    }
}