package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
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

    private val mResponse: UnPeekLiveData<T> = UnPeekLiveData.Builder<T>().setAllowNullValue(true).create()

    private val error :StringLiveData = StringLiveData()

    private val finally :BooleanLiveData = BooleanLiveData()

    fun getResponseLiveData(): UnPeekLiveData<T> = mResponse

    fun getErrorMsgLiveData():StringLiveData = error

    fun getFinallyLiveData():BooleanLiveData = finally

    open fun onSuccess(response:T?){
        getResponseLiveData().value = response
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