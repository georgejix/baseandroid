package com.jx.androiddemo.constant

import android.app.Activity
import androidx.fragment.app.DialogFragment
import java.util.*

class PageManager() {
    /**
     * 本地activity集合
     */
    private val storeActivities = Stack<Activity>()

    private val storeDialogFragments = Stack<DialogFragment>()


    /**
     * <删除>
    </删除> */
    fun deleteActivity(activity: Activity) {
        storeActivities.remove(activity)
        activity.finish()
    }

    /**
     * <添加activity>
    </添加activity> */
    fun addActivity(activity: Activity) {
        storeActivities.add(activity)
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    fun getCurActivity(): Activity? {
        return if (storeActivities.isEmpty()) null else storeActivities.lastElement()
    }

    /**
     * <跳转登陆界面>
    </跳转登陆界面> */
    fun startToLoginActivity() {
        try {
            for (activity in storeActivities) {
                /*if (!(activity instanceof LoginActivity))
                {
                    activity.finish();
                }*/
            }
        } catch (e: Exception) {
        }
    }

    /**
     * <退出应用>
    </退出应用> */
    fun exitApplication() {
        try {
            for (activity in storeActivities) {
                activity.finish()
            }
        } catch (e: Exception) {
        } finally {
            System.exit(0)
        }
    }

    /**
     * <删除DialogFragment>
    </删除DialogFragment> */
    fun removeDialogFragment(dialogFragment: DialogFragment) {
        storeDialogFragments.remove(dialogFragment)
    }

    /**
     * <添加DialogFragment>
    </添加DialogFragment> */
    fun addDialogFragment(dialogFragment: DialogFragment) {
        storeDialogFragments.add(dialogFragment)
    }

    fun dismissAllDialogFragment() {
        if (storeDialogFragments.isEmpty()) {
            for (dialogFragment in storeDialogFragments) {
                dialogFragment?.dismiss()
            }
            storeDialogFragments.clear()
        }
    }

    fun getCurDialogFragment(): DialogFragment? {
        return if (storeDialogFragments.isEmpty()) null else storeDialogFragments.lastElement()
    }
}