package com.theone.mvvm.core.ext

import androidx.lifecycle.rxLifeScope
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.common.ext.logE
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import kotlinx.coroutines.CoroutineScope


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
 * @date 2021/2/22 0022
 * @describe
 * @email 625805189@qq.com
 * @remark
 */

/**
 * 请求
 * @param block 请求的主函数体，得到数据后调用onSuccess方法
 * @param loadingMsg 请求时的提示语句，不为空时才开启弹窗提示
 * @param errorLiveData 接收错误的LiveData
 */
fun <T> BaseRequestViewModel<T>.request(
    block: suspend CoroutineScope.() -> Unit,
    loadingMsg: String? = null,
    errorLiveData: UnPeekLiveData<String>? = null
) {
    rxLifeScope.launch({
        block()
    }, {
        // 错误回调
        // 这里可以给不同的请求设置不同的错误接收
        it.printStackTrace()
        it.message.toString().logE()
        if(null != errorLiveData){
            onError(it,errorLiveData)
        }else{
            onError(it)
        }
    }, {
        // 请求开始
        // 求时的提示语句不为空时才开启弹窗提示
        loadingMsg?.let {
            showLoadingDialog(it)
        }
    }, {
        // 请求结束
        onFinally()
        // 关闭loading
        loadingMsg?.let {
            hideLoadingDialog()
        }
    }
    )
}
