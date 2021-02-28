package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.net.IResponse
import com.theone.mvvm.net.error.ErrorInfo


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

    private val mResponse: MutableLiveData<IResponse<T>> = MutableLiveData()

    private val error :StringLiveData = StringLiveData()

    fun getResponse(): MutableLiveData<IResponse<T>> = mResponse

    fun getErrorMsg():StringLiveData = error

    open fun onSuccess(response:IResponse<T>){
        getResponse().postValue(response)
    }

    open fun onError(errorMsg:String?){
        getErrorMsg().postValue(errorMsg)
    }

    open fun onError(error:Throwable?){
        onError(ErrorInfo(error).errorMsg)
    }

}