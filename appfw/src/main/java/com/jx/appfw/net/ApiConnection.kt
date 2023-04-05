package com.jx.appfw.net

import okhttp3.*
import java.util.concurrent.TimeUnit

object ApiConnection {
    val DEFAULT_TIMEOUT = 5000L
    private fun buildHttpClient(timeout: Long): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .sslSocketFactory(SSLSocketManager.getSSLSocketFactory()) //配置
            .hostnameVerifier(SSLSocketManager.getHostnameVerifier()) //配置
            .build()
    }

    private fun buildHttpHeader(): Headers? {
        return Headers.Builder()
            .add("token", "token")
            .build()
    }

    fun createGet(url: String): String? {
        val httpClient = buildHttpClient(DEFAULT_TIMEOUT)
        val request: Request = Request.Builder()
            .url(url)
            .headers(buildHttpHeader())
            .get() //默认就是GET请求，可以不写
            .build()
        val resp = httpClient.newCall(request).execute()
        return if (resp.isSuccessful) {
            resp.body()?.string()
        } else {
            null
        }
    }

    fun createPost(url: String, req: String): String? {
        val JSON = MediaType.parse("application/json;charset=utf-8")
        val requestBody: RequestBody = RequestBody.create(JSON, req)
        val httpClient = buildHttpClient(DEFAULT_TIMEOUT)
        val request: Request = Request.Builder()
            .url(url)
            .headers(buildHttpHeader())
            .post(requestBody)
            .build()
        val resp = httpClient.newCall(request).execute()
        return if (resp.isSuccessful) {
            resp.body()?.string()
        } else {
            null
        }
    }
}