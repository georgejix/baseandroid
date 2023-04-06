package com.jx.androiddemo.presenter

interface BasePresenter<T> {
    fun attachView(view: T)

    fun detachView()
}