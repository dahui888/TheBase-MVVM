package com.theone.mvvm.core.net.error

import android.text.TextUtils
import com.google.gson.JsonSyntaxException
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021-03-29 10:18
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ErrorInfo(throwable: Throwable) {

    //仅指服务器返回的错误码
    var errorCode: Int = 0

    //错误文案，网络错误、请求失败错误、服务器返回的错误等文案
    var errorMsg: String? = null

    init {
        when (throwable) {
            is HttpStatusCodeException -> {
                errorMsg = if (throwable.localizedMessage == "416") {
                    "请求范围不符合要求"
                } else {
                    throwable.message
                }
            }
            is JsonSyntaxException -> "数据解析失败,请稍后再试"
            is ParseException -> {
                // ParseException异常表明请求成功，但是数据不正确
                val code = throwable.errorCode
                errorCode = code.toInt()
                errorMsg = throwable.message
                if(TextUtils.isEmpty(errorMsg)){
                    errorMsg = code
                }
            }
            else -> errorMsg = handleException(throwable)
        }
    }

}