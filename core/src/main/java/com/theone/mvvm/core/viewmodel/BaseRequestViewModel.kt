package com.theone.mvvm.core.viewmodel

import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.callback.livedata.BooleanLiveData
import com.theone.mvvm.core.net.error.ErrorInfo


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
 * @describe 请求ViewModel基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseRequestViewModel<T> : BaseViewModel() {

    /**
     * 请求返回的数据
     */
    private val response: UnPeekLiveData<T> =
        UnPeekLiveData.Builder<T>().setAllowNullValue(true).create()

    /**
     * 错误原因
     */
    private val error: StringLiveData = StringLiveData()

    /**
     * 请求无论成功或者失败之后的回调
     */
    private val finally: BooleanLiveData = BooleanLiveData()

    fun getResponseLiveData(): ProtectedUnPeekLiveData<T> = response

    fun getErrorMsgLiveData(): StringLiveData = error

    fun getFinallyLiveData(): BooleanLiveData = finally

    /**
     * 请求成功后设置数据调用此方法
     * @param response
     */
    open fun onSuccess(response: T?) {
        this.response.value = response
    }

    /**
     * 请求错误时调用此方法
     * @param errorMsg 错误信息
     * @param errorLiveData 错误接收的LiveData
     */
    open fun onError(errorMsg: String?, errorLiveData: StringLiveData = error) {
        errorMsg?.let {
            errorLiveData.value = it
        }
    }

    open fun onError(throwable: Throwable, liveData: StringLiveData = error) {
        onError(ErrorInfo(throwable).errorMsg, liveData)
    }

    abstract fun requestServer()

}