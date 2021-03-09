package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.base.ext.util.logI
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

    fun getResponseLiveData(): MutableLiveData<T> = mResponse

    fun getErrorMsgLiveData():StringLiveData = error

    fun getFinallyLiveData():BooleanLiveData = finally

    open fun onSuccess(response:T?){
        "$TAG  onSuccess ".logI(TAG)
        getResponseLiveData().value = response
    }

    open fun onError(errorMsg:String,liveData :StringLiveData){
        "$TAG  onError $errorMsg ".logI(TAG)
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