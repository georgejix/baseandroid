package com.jx.androiddemo.presenter

import com.jx.androiddemo.BaseView

interface BasePresenter {
    fun attachView(view: BaseView)

    fun detachView()
}