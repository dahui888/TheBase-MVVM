package com.theone.mvvm.base.ext

import androidx.lifecycle.rxLifeScope
import androidx.lifecycle.viewModelScope
import com.theone.mvvm.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.base.viewmodel.BaseViewModel
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
    loadingMsg: String? = null
){
    rxLifeScope.launch({
        block()
    }, {
        onError(it)
    }, {
        loadingMsg?.let {
            loadingChange.showDialog.postValue(loadingMsg)
        }
    }, {
        getFinallyLiveData().postValue(true)
        loadingMsg?.let {
            loadingChange.dismissDialog.postValue(false)
        }
    }
    )
}