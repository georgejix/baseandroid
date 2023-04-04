package com.jx.appfw.domain.repository;

import com.jx.appfw.domain.request.main.TestBean;

import io.reactivex.Observable;

public interface MainRepository {
    Observable<String> test(TestBean testBean);
}
