package com.jx.appfw.domain.repository;

import com.jx.appfw.domain.request.main.TestBean;

import io.reactivex.Observable;

interface MainRepository {
    fun test(testBean: TestBean): Observable<String>
}
