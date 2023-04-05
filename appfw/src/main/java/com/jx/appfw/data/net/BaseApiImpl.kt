package com.jx.appfw.data.net

import com.jx.appfw.net.ApiConnection
import java.net.MalformedURLException

open class BaseApiImpl {
    /**
     * POST请求
     *
     * @param url
     * @param requestJson
     * @return
     * @throws MalformedURLException
     */
    @Throws(MalformedURLException::class)
    fun requestFromApi(url: String, requestJson: String): String? {
        return ApiConnection.createPost(url, requestJson)
    }

    /**
     * GET请求
     *
     * @param url
     * @return
     * @throws MalformedURLException
     */
    @Throws(MalformedURLException::class)
    fun requestFromApi(url: String): String? {
        return ApiConnection.createGet(url)
    }
}