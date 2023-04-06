package com.jx.appfw.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

    /**
     * 状态栏背景透明
     */
    fun setStatusBarTheme(mActivity: Activity, dark: Boolean) {
        //刘海屏抢夺状态栏控制权
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mActivity.window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    /**
     * 隐藏导航栏
     */
    fun hideStatusBar(mActivity: Activity, view: View) {
        //刘海屏抢夺状态栏控制权
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mActivity.window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        val controller: WindowInsetsControllerCompat =
            ViewCompat.getWindowInsetsController(view) ?: return
        //隐藏状态栏
        controller.hide(WindowInsetsCompat.Type.statusBars())
        //隐藏导航栏
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        //导航栏文字颜色
        controller.isAppearanceLightNavigationBars = true
        //隐藏键盘
        controller.hide(WindowInsetsCompat.Type.ime())
    }

    /**
     * 获取状态栏高度
     */
    fun getToolBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }
}