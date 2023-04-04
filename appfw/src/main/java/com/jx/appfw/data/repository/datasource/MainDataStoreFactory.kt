package com.jx.appfw.data.repository.datasource

import android.content.Context
import com.jx.appfw.data.db.MainDB
import com.jx.appfw.data.db.MainDBImpl
import com.jx.appfw.data.mapper.MainDataMapper
import com.jx.appfw.data.net.MainApi
import com.jx.appfw.data.net.MainApiImpl
import com.jx.appfw.data.repository.datasource.impl.MainDataStoreCacheImpl
import com.jx.appfw.data.repository.datasource.impl.MainDataStoreNetImpl
import com.jx.arch.util.DeviceUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainDataStoreFactory
@Inject constructor(
    val mContext: Context,
    val mMainDataMapper: MainDataMapper
) {
    private var mMainDB: MainDB

    init {
        mMainDB = MainDBImpl()
    }

    fun createMainDataStore(): MainDataStore {
        val mainDataStore: MainDataStore
        mainDataStore = if (DeviceUtils.isThereInternetConnection()) {
            createNetMainDataStore()
        } else {
            createCacheMainDataStore()
        }
        return mainDataStore
    }

    fun createCacheMainDataStore(): MainDataStore {
        return MainDataStoreCacheImpl(mMainDB, mMainDataMapper)
    }

    fun createNetMainDataStore(): MainDataStore {
        val mainApi: MainApi = MainApiImpl()
        return MainDataStoreNetImpl(mainApi, mMainDB)
    }
}