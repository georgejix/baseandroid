package com.jx.appfw.common

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Void, Params>(
    val threadExecutor: JobExecutor,
    val postExecutionThread: com.jx.appfw.common.PostExecutionThread
) {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    /**
     * Builds an [Observable] which will be used when executing the current [SingleUseCase].
     */
    abstract fun buildUseCaseObservable(params: Params): Single<Void>

    /**
     * Executes the current use case.
     *
     *
     * by [.buildUseCaseObservable] ()} method.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(params: Params) {
        val observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        observable.subscribe()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}