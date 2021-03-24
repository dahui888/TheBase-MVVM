package com.theone.mvvm.core.ext

import androidx.lifecycle.rxLifeScope
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.core.viewmodel.BaseRequestViewModel
import kotlinx.coroutines.*


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
 * @date 2021/3/5 0005
 * @describe 请求统一弹窗开启结束封装
 * @email 625805189@qq.com
 * @remark
 */

fun <T> BaseRequestViewModel<T>.request(
    block: suspend CoroutineScope.() -> Unit,
    loadingMsg: String? = null,
    errorLiveData: StringLiveData= getErrorMsgLiveData()
    ){
    rxLifeScope.launch({
        block()
    }, {
        onError(it,errorLiveData)
    }, {
        loadingMsg?.let {
            loadingChange.showDialog.value = loadingMsg
        }
    }, {
        getFinallyLiveData().value = true
        loadingMsg?.let {
            loadingChange.dismissDialog.value = false
        }
    }
    )
}