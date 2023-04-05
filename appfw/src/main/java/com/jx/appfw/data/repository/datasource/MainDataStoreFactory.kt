package com.jx.appfw.data.repository.datasource

import android.content.Context
import com.jx.appfw.data.db.MainDB
import com.jx.appfw.data.db.MainDBImpl
import com.jx.appfw.data.mapper.MainDataMapper
import com.jx.appfw.data.net.MainApi
import com.jx.appfw.data.net.MainApiImpl
import com.jx.appfw.data.repository.datasource.impl.MainDataStoreCacheImpl
import com.jx.appfw.data.repository.datasource.impl.MainDataStoreNetImpl
import javax.inject.Inject
import javax.inject.Singleton

class MainDataStoreFactory(
    val mContext: Context,
    val mMainDataMapper: MainDataMapper
) {
    private var mMainDB: MainDB

    init {
        mMainDB = MainDBImpl()
    }

    fun createCacheMainDataStore(): MainDataStore {
        return MainDataStoreCacheImpl(mMainDB, mMainDataMapper)
    }

    fun createNetMainDataStore(): MainDataStore {
        val mainApi: MainApi = MainApiImpl()
        return MainDataStoreNetImpl(mainApi, mMainDB)
    }
}