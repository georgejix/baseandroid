package com.jx.pullrefreshview.listener;

import androidx.annotation.NonNull;

import com.jx.pullrefreshview.api.RefreshLayout;

/**
 * 刷新监听器
 * Created by WANG on 2017/5/26.
 */

public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
