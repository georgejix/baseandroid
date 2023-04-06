package com.jx.androiddemo.contract.main

import com.jx.androiddemo.BaseView
import com.jx.androiddemo.presenter.BasePresenter

class MainContract {
    interface View : BaseView {
        fun showMsg(str: String)
    }

    interface Presenter : BasePresenter {
        fun test()
        fun test2()
    }
}