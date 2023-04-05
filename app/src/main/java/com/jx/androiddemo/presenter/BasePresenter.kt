package com.jx.androiddemo.presenter

import com.jx.androiddemo.BaseView

interface BasePresenter<T : BaseView> {
    fun attachView(view: T)

    fun detachView()
}