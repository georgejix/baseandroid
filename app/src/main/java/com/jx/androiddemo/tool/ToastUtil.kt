package com.jx.androiddemo.tool

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

object ToastUtil {
    private var toast: Toast? = null

    fun showToast(context: Context?, msg: String?) {
        if (null == msg) {
            return
        }
        try {
            toast?.cancel()
        } catch (e: Exception) {
        }
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
        toast?.setGravity(Gravity.CENTER, 0, 0)
        val v = toast?.getView()
        if (v is LinearLayout) {
            val v2 = v.getChildAt(0)
            if (v2 is TextView) {
                v2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            }
        }
        toast?.setText(msg)
        toast?.show()
    }
}