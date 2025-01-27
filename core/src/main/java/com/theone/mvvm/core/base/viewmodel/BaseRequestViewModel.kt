package com.theone.mvvm.core.base.viewmodel

import androidx.lifecycle.LiveData
import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.callback.livedata.BooleanLiveData
import com.theone.mvvm.core.net.error.ErrorInfo
import rxhttp.wrapper.cahce.CacheMode


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
    private val error: UnPeekLiveData<String> =
        UnPeekLiveData.Builder<String>().setAllowNullValue(true).create()

    /**
     * 请求无论成功或者失败之后的回调
     */
    private val finally: UnPeekLiveData<Boolean> = UnPeekLiveData()

    /**
     * 向 ui 层提供 ProtectedUnPeekLiveData，从而限制从 Activity/Fragment 篡改来自 "数据层" 的数据，数据层的数据务必通过 "唯一可信源" 来分发
     * from: KunMinX  https://xiaozhuanlan.com/topic/0168753249
     */
    fun getResponseLiveData(): ProtectedUnPeekLiveData<T> = response
    fun getErrorMsgLiveData(): ProtectedUnPeekLiveData<String> = error
    fun getFinallyLiveData(): ProtectedUnPeekLiveData<Boolean> = finally

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
    open fun onError(errorMsg: String?, errorLiveData: UnPeekLiveData<String>? = error) {
        errorLiveData?.value = errorMsg
    }

    open fun onError(throwable: Throwable, liveData: UnPeekLiveData<String>? = error) {
        onError(ErrorInfo(throwable).errorMsg, liveData)
    }

    fun onFinally() {
        finally.value = true
    }

    abstract fun requestServer()

}
