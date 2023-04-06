package com.jx.androiddemo.presenter.main

import android.content.Context
import com.jx.androiddemo.contract.main.LaunchContract
import com.jx.androiddemo.presenter.BaseRxPresenter

class LaunchPresenter(val mContext: Context) :
    BaseRxPresenter<LaunchContract.View>(),
    LaunchContract.Presenter {
    override fun doDispose() {
    }
}