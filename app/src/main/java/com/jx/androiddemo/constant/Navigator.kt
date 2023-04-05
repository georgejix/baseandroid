package com.jx.androiddemo.constant

import android.content.Context
import android.content.Intent
import com.jx.androiddemo.activity.main.MainActivity

object Navigator {
    fun navigateToMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}