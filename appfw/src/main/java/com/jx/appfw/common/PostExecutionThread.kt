package com.jx.appfw.common

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}