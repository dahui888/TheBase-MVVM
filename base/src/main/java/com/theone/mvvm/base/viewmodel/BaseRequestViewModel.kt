package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.base.net.error.ErrorInfo
import com.theone.mvvm.callback.livedata.BooleanLiveData


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

    private val finally :BooleanLiveData = BooleanLiveData()

    fun getResponse(): MutableLiveData<T> = mResponse

    fun getErrorMsg():StringLiveData = error

    fun getFinallyLiveData():BooleanLiveData = finally

    open fun onSuccess(response:T?){
        getResponse().value = response
    }

    open fun onError(errorMsg:String,liveData :StringLiveData){
        liveData.value = errorMsg
    }

    open fun onError(errorMsg:String){
        onError(errorMsg,error)
    }

    open fun onError(throwable:Throwable?,liveData :StringLiveData){
        onError(ErrorInfo(throwable).errorMsg,liveData)
    }

    open fun onError(throwable:Throwable?){
        onError(throwable,error)
    }

    abstract fun requestServer()

}