package com.jx.androiddemo.activity.main

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.rxbinding2.view.RxView
import com.jx.androiddemo.BaseMvpActivity
import com.jx.androiddemo.R
import com.jx.androiddemo.constant.Constants
import com.jx.androiddemo.constant.EventBusTag
import com.jx.androiddemo.contract.main.MainContract
import com.jx.androiddemo.event.NoticeEvent
import com.jx.androiddemo.presenter.main.MainPresenter
import com.jx.androiddemo.tool.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("CheckResult")
    override fun initEventAndData() {
        initView()
    }

    private fun initView() {
        RxView.clicks(tv_test)
            .throttleFirst(Constants.CLICK_TIME.toLong(), TimeUnit.MILLISECONDS)
            .subscribe { o: Any? -> mPresenter?.test() }
        RxView.clicks(tv_test2)
            .throttleFirst(Constants.CLICK_TIME.toLong(), TimeUnit.MILLISECONDS)
            .subscribe { o: Any? -> mPresenter?.test2() }
    }

    override fun showMsg(str: String) {
        ToastUtil.showToast(mContext, str)

    }

    override fun initPresenter() {
        mPresenter = MainPresenter(this)
    }

    override fun onEventMainThread(event: NoticeEvent?) {
        super.onEventMainThread(event)
        event?.tag?.let {
            when (it) {
                EventBusTag.TAG_TEST -> {
                    if (true == event.args?.isNotEmpty() &&
                        event.args?.get(0) is String
                    ) {
                        showMsg("eventbus ${event.args[0]}")
                    } else {
                        showMsg("eventbus empty")
                    }
                }
            }
        }
    }
}