package com.jx.appfw.util

import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import java.util.*

object DeviceUtil {
    fun getDeviceUUid(context: Context): String? {
        val androidId: String =
            Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        if (TextUtils.isEmpty(androidId)) {
            return null
        }
        val deviceUuid = UUID(
            androidId.hashCode().toLong(),
            androidId.hashCode().toLong() shl 32
        )
        return deviceUuid.toString()
    }
}