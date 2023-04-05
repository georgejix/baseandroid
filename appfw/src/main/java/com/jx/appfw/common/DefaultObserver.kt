package com.jx.appfw.common

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T>: DisposableObserver<T>() {
    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onComplete() {
    }
}