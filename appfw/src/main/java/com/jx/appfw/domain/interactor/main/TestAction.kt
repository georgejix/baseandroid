package com.jx.appfw.domain.interactor.main;

import com.jx.appfw.common.JobExecutor
import com.jx.appfw.common.PostExecutionThread
import com.jx.appfw.common.UseCase
import com.jx.appfw.domain.repository.MainRepository
import com.jx.appfw.domain.request.main.TestBean
import io.reactivex.Observable

class TestAction(
    val mMainRepository: MainRepository,
    mThreadExecutor: JobExecutor,
    mPostExecutionThread: PostExecutionThread
) : UseCase<String, TestBean>(mThreadExecutor, mPostExecutionThread) {

    @Override
    override fun buildUseCaseObservable(testBean: TestBean): Observable<String> {
        return mMainRepository.test(testBean)
    }
}
