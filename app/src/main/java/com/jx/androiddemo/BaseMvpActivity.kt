package com.jx.androiddemo

import android.app.Activity
import android.os.Bundle
import com.jx.androiddemo.event.NoticeEvent
import com.jx.androiddemo.presenter.BasePresenter
import com.jx.androiddemo.presenter.BaseRxPresenter
import me.yokeyword.fragmentation.SupportActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseMvpActivity<T : BasePresenter> : SupportActivity(), BaseView {
    var mPresenter: T? = null

    var mContext: Activity? = null
    abstract fun initPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        EventBus.getDefault().register(this)
        setContentView(getLayout())
        //设置状态栏颜色
        //setStatusBarColor(getResources().getColor(R.color.head_view_bg));
        mContext = this
        initEventAndData()
        mPresenter?.attachView(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEventMainThread(event: NoticeEvent?) {
    }

    override fun onDestroy() {
        BaseApplication.getPageManager()?.deleteActivity(this)
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        mPresenter?.detachView()
        if (mPresenter != null && mPresenter is BaseRxPresenter) {
            (mPresenter as BaseRxPresenter).doDispose()
        }
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initEventAndData()

}