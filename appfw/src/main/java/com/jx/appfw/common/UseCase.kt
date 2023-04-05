package com.jx.appfw.common

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params>(
    val threadExecutor: JobExecutor,
    val postExecutionThread: com.jx.appfw.common.PostExecutionThread
) {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    open fun execute(observer: DisposableObserver<T>?, params: Params) {
        val observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        observable.subscribeWith(observer)?.let { addDisposable(it) }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    open fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    open fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}