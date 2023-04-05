package com.jx.appfw.config

object Hosts {
    /**
     * 修改环境变量
     */
    var hostType = HostType.PRODUCT
    var BASE_HOST = hostType.getBaseHost()

    enum class HostType : BaseHostType {
        //生产环境
        PRODUCT {
            override fun getBaseHost(): String {
                return HostsProduct.BASE_HOST
            }
        },  //Test
        TEST {
            override fun getBaseHost(): String {
                return HostsTest.BASE_HOST
            }
        }
    }

    fun updateUrls(type: HostType) {
        hostType = type
        BASE_HOST = hostType.getBaseHost()
    }
}