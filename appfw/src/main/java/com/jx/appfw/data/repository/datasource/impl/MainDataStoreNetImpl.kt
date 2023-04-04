package com.jx.appfw.data.repository.datasource.impl

import com.jx.appfw.data.db.MainDB
import com.jx.appfw.data.net.MainApi
import com.jx.appfw.data.repository.datasource.MainDataStore
import com.jx.appfw.domain.request.main.TestBean
import io.reactivex.Observable

class MainDataStoreNetImpl(val mMainApi: MainApi, val mMainDB: MainDB) : MainDataStore {
    override fun test(testBean: TestBean): Observable<String> {
        return mMainApi.test(testBean)
    }
}