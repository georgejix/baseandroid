package com.jx.appfw.data.repository.datasource.impl

import com.jx.appfw.data.db.MainDB
import com.jx.appfw.data.mapper.MainDataMapper
import com.jx.appfw.data.repository.datasource.MainDataStore
import com.jx.appfw.domain.request.main.TestBean
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class MainDataStoreCacheImpl(val mMainDB: MainDB, val mMainDataMapper: MainDataMapper) :
    MainDataStore {
    override fun test(testBean: TestBean): Observable<String> {
        return Observable.create { emitter: ObservableEmitter<String> ->
            emitter.onNext("123")
            emitter.onComplete()
        }
    }
}