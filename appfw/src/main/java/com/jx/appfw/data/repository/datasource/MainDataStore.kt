package com.jx.appfw.data.repository.datasource

import com.jx.appfw.domain.request.main.TestBean
import io.reactivex.Observable

interface MainDataStore {
    fun test(testBean: TestBean): Observable<String>
}