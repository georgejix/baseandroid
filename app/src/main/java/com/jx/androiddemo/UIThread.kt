package com.jx.androiddemo

import com.jx.appfw.common.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread() : PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}