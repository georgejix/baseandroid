package com.jx.appfw.domain.interactor.main;

import com.jx.appfw.domain.repository.MainRepository
import com.jx.appfw.domain.request.main.TestBean
import com.jx.arch.domain.executor.PostExecutionThread
import com.jx.arch.domain.executor.ThreadExecutor
import com.jx.arch.domain.interactor.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class TestAction @Inject constructor(
    val mMainRepository: MainRepository,
    mThreadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread
) : UseCase<String, TestBean>(mThreadExecutor, mPostExecutionThread) {

    @Override
    override fun buildUseCaseObservable(testBean: TestBean): Observable<String> {
        return mMainRepository.test(testBean)
    }
}
