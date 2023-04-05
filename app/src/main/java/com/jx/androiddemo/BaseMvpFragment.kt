package com.jx.androiddemo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jx.androiddemo.event.NoticeEvent
import com.jx.androiddemo.presenter.BasePresenter
import com.jx.androiddemo.presenter.BaseRxPresenter
import me.yokeyword.fragmentation.SupportFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseMvpFragment<T : BasePresenter> : SupportFragment(), BaseView {
    private var mPresenter: T? = null
    protected var mView: View? = null

    protected var mActivity: Activity? = null

    protected var mContext: Context? = null

    private var mInBack = true
    abstract fun initPresenter()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(getLayoutId(), null)
        initInject()
        return mView
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEventMainThread(event: NoticeEvent?) {
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mPresenter?.detachView()
        if (mPresenter is BaseRxPresenter) {
            (mPresenter as BaseRxPresenter).doDispose()
        }
        super.onDestroy()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            initEventAndData()
            mPresenter?.attachView(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun initInject()

    abstract fun getLayoutId(): Int

    abstract fun initEventAndData()

    override fun onResume() {
        super.onResume()
        mInBack = false
    }

    override fun onPause() {
        super.onPause()
        mInBack = true
    }

    fun isInBack(): Boolean {
        return mInBack
    }

}