package com.jx.androiddemo

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.multidex.MultiDexApplication
import com.jx.androiddemo.constant.ActionManager
import com.jx.androiddemo.constant.EventBusTag
import com.jx.androiddemo.constant.PageManager
import com.jx.androiddemo.event.NoticeEvent
import com.jx.appfw.util.AppProcessUtil
import com.jx.appfw.util.LogUtil.i
import org.greenrobot.eventbus.EventBus

class BaseApplication : MultiDexApplication() {
    companion object {
        var mInstance: BaseApplication? = null
        var mHandler: Handler? = null
        var mPageManager: PageManager? = null
        var mActionManager: ActionManager? = null
        fun getInstance(): BaseApplication? = mInstance
        fun getPageManager(): PageManager? = mPageManager
        fun getHandler(): Handler? = mHandler
        fun getActionManager(): ActionManager? = mActionManager
    }

    /**
     * 记录Activity的总个数
     */
    var count = 0
    override fun onCreate() {
        super.onCreate()
        if (AppProcessUtil.isAppProcess(this)) {
            mInstance = this
            mHandler = Handler()
            mPageManager = PageManager()
            mActionManager = ActionManager(applicationContext)
            registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                    mPageManager?.addActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {
                    if (count == 0) { //后台切换到前台
                        i("info", ">>>>>>>>>>>>>>>>>>>App切到前台")
                        EventBus.getDefault().post(NoticeEvent(EventBusTag.TAG_SHOW_FONT))
                    }
                    count++
                }

                override fun onActivityResumed(activity: Activity) {}
                override fun onActivityPaused(activity: Activity) {}
                override fun onActivityStopped(activity: Activity) {
                    count--
                    if (count == 0) { //前台切换到后台
                        i("info", ">>>>>>>>>>>>>>>>>>>App切到后台")
                        EventBus.getDefault()
                            .post(NoticeEvent(EventBusTag.TAG_HIDDEN_BACKGROUND))
                    }
                }

                override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
                override fun onActivityDestroyed(activity: Activity) {
                    mPageManager?.deleteActivity(activity)
                }
            })
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        mPageManager?.exitApplication()
    }

}