package com.jx.appfw.data.net

import com.jx.appfw.domain.request.BaseRequestBean
import com.jx.appfw.domain.request.main.TestBean
import com.jx.arch.config.Hosts
import com.jx.arch.util.GsonHelper
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class MainApiImpl : BaseApiImpl(), MainApi {
    val TEST_URL = "${Hosts.BASE_HOST}api/store/list/all"

    override fun test(testBean: TestBean): Observable<String> {
        return Observable.create { emitter: ObservableEmitter<String> ->
            val respStr =
                requestFromApi(TEST_URL, GsonHelper.toJson(BaseRequestBean()))
            //StoreResponse storeResponse = GsonHelper.toType(respStr, StoreResponse.class)
            emitter.onNext(testBean.str ?: "")
        }
    }
}