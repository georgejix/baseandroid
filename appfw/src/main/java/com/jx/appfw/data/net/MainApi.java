package com.jx.appfw.data.net;

import com.jx.appfw.domain.request.main.TestBean;
import com.jx.arch.config.Hosts;

import io.reactivex.Observable;

public interface MainApi {
    String TEST_URL = Hosts.BASE_HOST + "api/store/list/all";

    Observable<String> test(TestBean testBean);
}
