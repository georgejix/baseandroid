package com.jx.androiddemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jx.androiddemo.event.NoticeEvent;
import com.jx.androiddemo.presenter.BaseRxPresenter;
import com.jx.appfw.util.DeviceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseMvpActivity<T extends BaseRxPresenter> extends SupportActivity implements BaseView {
    public T mPresenter;
    public Activity mContext;
    public int mToolBarHeight = 0;

    public abstract void initPresenter();

    public abstract int getLayout();

    public abstract void initEventAndData();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        EventBus.getDefault().register(this);
        setContentView(getLayout());
        mToolBarHeight = DeviceUtil.INSTANCE.getToolBarHeight(this);
        //设置状态栏颜色
        //setStatusBarColor(getResources().getColor(R.color.head_view_bg));
        mContext = this;
        initEventAndData();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
        if (null != BaseApplication.Companion.getMPageManager()) {
            BaseApplication.Companion.getMPageManager().addActivity(this);
        }
        DeviceUtil.INSTANCE.fullScreen(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NoticeEvent event) {
    }

    protected void onDestroy() {
        if (null != BaseApplication.Companion.getMPageManager()) {
            BaseApplication.Companion.getMPageManager().deleteActivity(this);
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter.doDispose();
            mPresenter = null;
        }
        super.onDestroy();
    }


}
