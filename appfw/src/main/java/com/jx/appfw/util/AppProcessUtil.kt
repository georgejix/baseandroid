package com.jx.appfw.util

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.os.Process
import android.util.Log

object AppProcessUtil {
    /**
     * 获取运行的进程id
     *
     * @return
     */
    fun getProcessId(): Int {
        return Process.myPid()
    }


    /**
     * 根据进程id获取进程名
     *
     * @param context
     * @param pID
     * @return
     */
    fun getProcessName(context: Context?, pID: Int): String? {
        if (context == null) {
            return null
        }
        var processName: String? = null
        val appContext = context.applicationContext
        val am = appContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val l: List<*> = am.runningAppProcesses
        val i = l.iterator()
        val pm = appContext.packageManager
        while (i.hasNext()) {
            val info = i.next() as RunningAppProcessInfo
            try {
                if (info.pid == pID) {
                    processName = info.processName
                    return processName
                }
            } catch (e: Exception) {
            }
        }
        return processName
    }

    /**
     * 获取运行该方法的进程的进程名
     *
     * @param context
     * @return
     */
    fun getProcessName(context: Context?): String? {
        if (context == null) {
            return null
        }
        val pID = getProcessId()
        var processName: String? = null
        val appContext = context.applicationContext
        val am = appContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val l: List<*> = am.runningAppProcesses
        val i = l.iterator()
        val pm = appContext.packageManager
        while (i.hasNext()) {
            val info = i.next() as RunningAppProcessInfo
            try {
                if (info.pid == pID) {
                    processName = info.processName
                    return processName
                }
            } catch (e: Exception) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName
    }

    /**
     * 判断该进程是app进程还是远程service进程
     *
     * @param c
     * @return
     */
    fun isAppProcess(c: Context?): Boolean {
        var c = c
        if (c == null) {
            return false
        }
        c = c.applicationContext
        val processName = getProcessName(c)
        return !(processName == null || !processName.equals(c.packageName, ignoreCase = true))
    }


    /**
     * app是否在前台运行  后台运行指的是只有所属的service运行
     * 需要权限:android.permission.GET_TASKS
     *
     * @param context
     * @return
     */
    fun isAppRunningForeground(context: Context): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = am.getRunningTasks(1)
        if (tasks != null && !tasks.isEmpty()) {
            val topActivity = tasks[0].topActivity
            return if (!topActivity!!.packageName.equals(context.packageName, ignoreCase = true)) {
                false
            } else {
                true
            }
        }
        return false
    }


    /**
     * 判断服务是否运行
     *
     * @param context
     * @param serviceName
     * @return
     */
    fun isServiceRunning(context: Context, serviceName: String): Boolean {
        var isRunning = false
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val lists = am.getRunningServices(100)
        for (info in lists) { //判断服务
            if (info.service.className == serviceName) {
                Log.i("Service1进程", "" + info.service.className)
                isRunning = true
            }
        }
        return isRunning
    }


    /**
     * 判断进程是否在运行
     *
     * @param context
     * @param proessName
     * @return
     */
    fun isProessRunning(context: Context, proessName: String): Boolean {
        var isRunning = false
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val lists = am.runningAppProcesses
        for (info in lists) {
            if (info.processName == proessName) {
                //Log.i("Service2进程", ""+info.processName);
                isRunning = true
            }
        }
        return isRunning
    }

    fun killAppProcess(context: Context) {
        //注意：不能先杀掉主进程，否则逻辑代码无法继续执行，需先杀掉相关进程最后杀掉主进程
        val mActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mList = mActivityManager.runningAppProcesses
        for (runningAppProcessInfo in mList) {
            if (runningAppProcessInfo.pid != Process.myPid()) {
                Process.killProcess(runningAppProcessInfo.pid)
            }
        }
        Process.killProcess(Process.myPid())
        System.exit(0)
    }
}