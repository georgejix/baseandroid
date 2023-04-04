package com.jx.appfw.data.net;

import com.jx.appfw.domain.request.BaseRequestBean;
import com.jx.appfw.domain.request.main.TestBean;
import com.jx.arch.util.GsonHelper;

import io.reactivex.Observable;

/**
 * {@link MainApi} implementation for retrieving data from the network.
 */
public class MainApiImpl extends BaseApiImpl implements MainApi {
    @Override
    public Observable<String> test(TestBean testBean) {
        return Observable.create(emitter ->
        {
            String respStr = requestFromApi(TEST_URL, GsonHelper.toJson(new BaseRequestBean()));
            //StoreResponse storeResponse = GsonHelper.toType(respStr, StoreResponse.class);

            emitter.onNext(testBean.str);
        });
    }
    //return loginApi.requestLoginData(loginRequestBean).doOnNext(loginDB::put).map(loginDataMapper::transform);
}
