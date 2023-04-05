package com.jx.androiddemo.presenter

import com.jx.androiddemo.BaseView
import java.lang.ref.Reference
import java.lang.ref.WeakReference

abstract class BaseRxPresenter : BasePresenter {
    var mView: Reference<BaseView>? = null
    abstract fun doDispose()
    open fun getView(): BaseView? = mView?.get()
    open fun isViewAttached(): Boolean = null != mView?.get()

    override fun attachView(view: BaseView) {
        mView = WeakReference(view)
    }

    override fun detachView() {
        mView?.clear()
        mView = null
    }
}