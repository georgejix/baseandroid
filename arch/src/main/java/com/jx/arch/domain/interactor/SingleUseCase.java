package com.jx.arch.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;
import com.jx.arch.domain.executor.PostExecutionThread;
import com.jx.arch.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class SingleUseCase<Void, Params>
{

    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    private final CompositeDisposable disposables;

    public SingleUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread)
    {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link SingleUseCase}.
     */
    public abstract Single<Void> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     * <p>
     * by {@link #buildUseCaseObservable(Params)} ()} method.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(Params params)
    {
        final Single<Void> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        observable.subscribe();
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose()
    {
        if (!disposables.isDisposed())
        {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable)
    {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
