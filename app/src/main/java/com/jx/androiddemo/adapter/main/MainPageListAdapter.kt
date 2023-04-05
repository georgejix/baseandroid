package com.jx.androiddemo.adapter.main

import android.content.Context
import com.jx.androiddemo.R
import com.jx.androiddemo.bean.main.MainPageBean
import com.jx.rvhelper.adapter.CommonAdapter
import com.jx.rvhelper.base.ViewHolder

class MainPageListAdapter(val context: Context) :
    CommonAdapter<MainPageBean>(context, R.layout.item_main_page) {
    override fun convert(holder: ViewHolder?, bean: MainPageBean?, position: Int) {
        holder?.setText(R.id.tv_name, bean?.name)
    }
}