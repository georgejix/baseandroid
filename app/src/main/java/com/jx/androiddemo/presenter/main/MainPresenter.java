package com.jx.androiddemo.presenter.main;

import android.content.Context;

import com.jx.androiddemo.contract.main.MainContract;
import com.jx.androiddemo.presenter.BaseRxPresenter;
import com.jx.androiddemo.tool.ToastUtil;
import com.jx.appfw.domain.interactor.main.TestAction;
import com.jx.appfw.domain.request.main.TestBean;
import com.jx.arch.domain.interactor.DefaultObserver;

import javax.inject.Inject;

public class MainPresenter extends BaseRxPresenter<MainContract.View> implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    private Context context;

    TestAction mTestAction;


    @Inject
    MainPresenter(Context context, TestAction testAction) {
        this.context = context;
        mTestAction = testAction;
    }

    @Override
    public void doDispose() {

    }

    public void test() {
        TestBean testBean = new TestBean();
        testBean.str = "233";
        mTestAction.execute(new DefaultObserver<String>() {
            @Override
            public void onNext(String str) {
                ToastUtil.showTextToast(str);
            }
        }, testBean);
    }
}
