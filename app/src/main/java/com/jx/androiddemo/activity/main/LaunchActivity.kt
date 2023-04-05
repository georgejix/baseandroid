package com.jx.androiddemo.activity.main

import android.annotation.SuppressLint
import com.jx.androiddemo.BaseMvpActivity
import com.jx.androiddemo.R
import com.jx.androiddemo.constant.Navigator.navigateToMainActivity
import com.jx.androiddemo.contract.main.LaunchContract
import com.jx.androiddemo.presenter.main.LaunchPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class LaunchActivity : BaseMvpActivity<LaunchPresenter>(), LaunchContract.View {
    override fun getLayout(): Int {
        return R.layout.activity_launch
    }

    @SuppressLint("CheckResult")
    override fun initEventAndData() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { integer: Long? ->
                navigateToMainActivity(
                    this
                )
            }
    }

    override fun initPresenter() {
        mPresenter = LaunchPresenter(this)
    }
}