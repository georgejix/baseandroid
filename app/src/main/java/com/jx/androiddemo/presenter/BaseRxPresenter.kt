package com.jx.androiddemo.presenter

import java.lang.ref.Reference
import java.lang.ref.WeakReference

abstract class BaseRxPresenter<T> : BasePresenter<T> {
    var mView: Reference<T>? = null
    abstract fun doDispose()
    open fun getView(): T? = mView?.get()
    open fun isViewAttached(): Boolean = null != mView?.get()

    override fun attachView(view: T) {
        mView = WeakReference(view)
    }

    override fun detachView() {
        mView?.clear()
        mView = null
    }
}