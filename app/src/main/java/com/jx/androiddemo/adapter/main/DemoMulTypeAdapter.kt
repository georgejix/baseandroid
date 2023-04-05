package com.jx.androiddemo.adapter.main

import android.content.Context
import com.jx.androiddemo.R
import com.jx.androiddemo.bean.main.DemoMulTypeBean
import com.jx.rvhelper.adapter.MultiItemTypeAdapter
import com.jx.rvhelper.base.ItemViewDelegate
import com.jx.rvhelper.base.ViewHolder

class DemoMulTypeAdapter(mContext: Context) :
    MultiItemTypeAdapter<DemoMulTypeBean>(mContext) {
    init {
        addItemViewDelegate(HeadDelegate())
        addItemViewDelegate(ContentDelegate())
    }

    private class HeadDelegate : ItemViewDelegate<DemoMulTypeBean> {
        override fun getItemViewLayoutId(): Int {
            return R.layout.item_main_page
        }

        override fun isForViewType(item: DemoMulTypeBean?, position: Int): Boolean {
            return 2 == item?.type
        }

        override fun convert(holder: ViewHolder?, item: DemoMulTypeBean?, position: Int) {}
    }

    private class ContentDelegate : ItemViewDelegate<DemoMulTypeBean> {
        override fun getItemViewLayoutId(): Int {
            return R.layout.item_main_page
        }

        override fun isForViewType(item: DemoMulTypeBean?, position: Int): Boolean {
            return 1 == item?.type
        }

        override fun convert(holder: ViewHolder?, item: DemoMulTypeBean?, position: Int) {}
    }
}