package com.jx.appfw.data.repository

import com.jx.appfw.data.repository.datasource.MainDataStore
import com.jx.appfw.data.repository.datasource.MainDataStoreFactory
import com.jx.appfw.domain.repository.MainRepository
import com.jx.appfw.domain.request.main.TestBean
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainDataRepository
@Inject constructor(val mMainDataStoreFactory: MainDataStoreFactory) : MainRepository {
    private val mainNetDataStore: MainDataStore
    private val mainDataStore: MainDataStore
    private val mainCacheDataStore: MainDataStore

    init {
        mainDataStore = mMainDataStoreFactory.createMainDataStore()
        mainNetDataStore = mMainDataStoreFactory.createNetMainDataStore()
        mainCacheDataStore = mMainDataStoreFactory.createCacheMainDataStore()
    }

    override fun test(testBean: TestBean): Observable<String> {
        return this.mainNetDataStore.test(testBean)
    }
}