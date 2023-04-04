package com.jx.appfw.data.net

import com.jx.appfw.domain.request.main.TestBean
import io.reactivex.Observable

interface MainApi {
    fun test(testBean: TestBean): Observable<String>
}