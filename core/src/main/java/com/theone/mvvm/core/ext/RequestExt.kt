package com.theone.mvvm.core.ext

import android.content.Context
import androidx.lifecycle.rxLifeScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.common.ext.notNull
import com.theone.mvvm.callback.livedata.StringLiveData
import com.theone.mvvm.core.base.fragment.IRecyclerPager
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.core.widge.loadsir.core.LoadService
import com.theone.mvvm.ext.qmui.showMsgTipsDialog
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
        if(null != errorLiveData){
            onError(it,errorLiveData)
        }else{
            onError(it)
        }
    }, {
        loadingMsg?.let {
            loadingChange.showDialog.value = loadingMsg
        }
    }, {
        onFinally()
        loadingMsg?.let {
            loadingChange.dismissDialog.value = false
        }
    }
    )
}
