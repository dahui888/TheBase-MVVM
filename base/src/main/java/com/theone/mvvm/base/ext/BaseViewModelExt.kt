package com.theone.mvvm.base.ext

import androidx.lifecycle.rxLifeScope
import com.theone.mvvm.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.callback.livedata.StringLiveData
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
 * @describe TODO
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