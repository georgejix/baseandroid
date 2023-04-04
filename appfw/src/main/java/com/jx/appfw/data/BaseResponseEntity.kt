package com.jx.appfw.data

import java.io.Serializable

class BaseResponseEntity : Serializable {
    var status: String? = null
    var message: String? = null
}