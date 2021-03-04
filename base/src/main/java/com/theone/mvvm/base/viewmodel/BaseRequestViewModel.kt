package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.base.net.error.ErrorInfo


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
 * @date 2021/2/25 0025
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseRequestViewModel<T>:BaseViewModel() {

    private val mResponse: MutableLiveData<T> = MutableLiveData()

    private val error :StringLiveData = StringLiveData()

    fun getResponse(): MutableLiveData<T> = mResponse

    fun getErrorMsg():StringLiveData = error

    open fun onSuccess(response:T?){
        "onSuccess ".logE()
        getResponse().postValue(response)
    }

    open fun onError(errorMsg:String?){
        "onError $errorMsg".logE()
        getErrorMsg().postValue(errorMsg)
    }

    open fun onError(error:Throwable?){
        onError(ErrorInfo(error).errorMsg)
    }

    abstract fun requestServer()

}