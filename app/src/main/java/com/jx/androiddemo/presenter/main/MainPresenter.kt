package com.jx.androiddemo.presenter.main

import android.content.Context
import com.jx.androiddemo.BaseApplication
import com.jx.androiddemo.constant.EventBusTag
import com.jx.androiddemo.contract.main.MainContract
import com.jx.androiddemo.event.NoticeEvent
import com.jx.androiddemo.presenter.BaseRxPresenter
import com.jx.appfw.common.DefaultObserver
import com.jx.appfw.domain.interactor.main.TestAction
import com.jx.appfw.domain.request.main.TestBean
import org.greenrobot.eventbus.EventBus

class MainPresenter(val mContext: Context) :
    BaseRxPresenter<MainContract.View>(),
    MainContract.Presenter {
    private val mTestAction: TestAction? by lazy {
        BaseApplication.getActionManager()?.getTestAction()
    }

    override fun doDispose() {
    }

    override fun test() {
        val testBean = TestBean()
        testBean.str = "test"
        mTestAction?.execute(object : DefaultObserver<String>() {
            override fun onNext(str: String) {
                getView()?.showMsg(str)
            }
        }, testBean)
    }

    override fun test2() {
        EventBus.getDefault().post(NoticeEvent(EventBusTag.TAG_TEST, arrayOf("test2")))
    }

}