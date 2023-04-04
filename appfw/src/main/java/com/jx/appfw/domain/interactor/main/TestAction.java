package com.jx.appfw.domain.interactor.main;

import com.jx.appfw.domain.repository.MainRepository;
import com.jx.appfw.domain.request.main.TestBean;
import com.jx.arch.domain.executor.PostExecutionThread;
import com.jx.arch.domain.executor.ThreadExecutor;
import com.jx.arch.domain.interactor.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TestAction extends UseCase<String, TestBean> {

    private final MainRepository repository;

    @Inject
    TestAction(MainRepository repository,
               ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(TestBean testBean) {
        return repository.test(testBean);

    }
}
