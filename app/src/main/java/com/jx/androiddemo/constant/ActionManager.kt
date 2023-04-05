package com.jx.androiddemo.constant

import android.content.Context
import com.jx.androiddemo.UIThread
import com.jx.appfw.common.JobExecutor
import com.jx.appfw.data.mapper.MainDataMapper
import com.jx.appfw.data.repository.MainDataRepository
import com.jx.appfw.data.repository.datasource.MainDataStoreFactory
import com.jx.appfw.domain.interactor.main.TestAction

class ActionManager(val mContext: Context) {
    private val mUIThread: UIThread
    private val mJobExecutor: JobExecutor
    private val mMainDataRepository: MainDataRepository
    private val mMainDataStoreFactory: MainDataStoreFactory

    init {
        mMainDataStoreFactory = MainDataStoreFactory(mContext, MainDataMapper())
        mMainDataRepository = MainDataRepository(mMainDataStoreFactory)
        mJobExecutor = JobExecutor()
        mUIThread = UIThread()
    }

    fun getTestAction(): TestAction = TestAction(mMainDataRepository, mJobExecutor, mUIThread)
}