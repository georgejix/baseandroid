package com.jx.appfw.common

import java.util.concurrent.*

class JobExecutor() : Executor {
    private var threadPoolExecutor: ThreadPoolExecutor? = null
    private val CORE_SIZE = Runtime.getRuntime().availableProcessors()

    init {
        threadPoolExecutor = ThreadPoolExecutor(
            CORE_SIZE, CORE_SIZE * 3,
            5, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>(), JobThreadFactory()
        )
        threadPoolExecutor?.allowCoreThreadTimeOut(true)
    }

    override fun execute(runnable: Runnable?) {
        threadPoolExecutor?.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0
        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}