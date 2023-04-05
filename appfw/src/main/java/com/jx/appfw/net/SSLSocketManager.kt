package com.jx.appfw.net

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

object SSLSocketManager {
    /**
     * 获取这个SSLSocketFactory
     *
     * @return
     */
    fun getSSLSocketFactory(): SSLSocketFactory? {
        return try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, getTrustManager(), SecureRandom())
            sslContext.socketFactory
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * 获取trustManager
     *
     * @return
     */
    fun getTrustManager(): Array<TrustManager> {
        return arrayOf(
            object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
    }

    /**
     * 获取hostnameVerifier
     *
     * @return
     */
    fun getHostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { s, sslSession -> true }
    }
}