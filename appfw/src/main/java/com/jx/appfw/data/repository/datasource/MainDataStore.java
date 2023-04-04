package com.jx.appfw.data.repository.datasource;

import com.jx.appfw.domain.request.main.TestBean;

import io.reactivex.Observable;

/**
 * LoginDataStore for retrieving data from the network.
 */
public interface MainDataStore {
    Observable<String> test(TestBean testBean);
}
