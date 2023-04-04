package com.jx.appfw.data.repository.datasource.impl;

import com.jx.appfw.data.db.MainDB;
import com.jx.appfw.data.mapper.MainDataMapper;
import com.jx.appfw.data.repository.datasource.MainDataStore;
import com.jx.appfw.domain.request.main.TestBean;

import io.reactivex.Observable;

/**
 * {@link MainDataStore} implementation for retrieving data from the network.
 */
public class MainDataStoreCacheImpl implements MainDataStore {
    private final MainDB mainDB;

    private final MainDataMapper mainDataMapper;

    public MainDataStoreCacheImpl(final MainDB mainDB, MainDataMapper mainDataMapper) {
        this.mainDB = mainDB;
        this.mainDataMapper = mainDataMapper;
    }

    @Override
    public Observable<String> test(TestBean testBean) {
        return Observable.create(emitter -> {
            //DbConfiguration.setDbConfiguration(initConfig.adminId);
            emitter.onNext("123");
            emitter.onComplete();
        });
    }
}
