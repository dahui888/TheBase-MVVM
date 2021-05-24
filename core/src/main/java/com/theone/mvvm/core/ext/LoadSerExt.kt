package com.theone.mvvm.core.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.theone.mvvm.core.widge.loadsir.core.LoadService
import com.theone.mvvm.core.widge.loadsir.core.LoadSir
import com.theone.mvvm.core.R
import com.theone.mvvm.core.base.activity.BaseCoreActivity
import com.theone.mvvm.core.base.fragment.BaseCoreFragment
import com.theone.mvvm.core.callback.ICore
import com.theone.mvvm.core.widge.loadsir.callback.ErrorCallback
import com.theone.mvvm.core.widge.loadsir.callback.LoadingCallback
import com.theone.mvvm.core.widge.loadsir.callback.SuccessCallback


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
 * @date 2021/2/24 0024
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */


fun ICore.registerLoadSir(): LoadService<Any> =
    loadSirRegister(loadSirRegisterView()) { onPageReLoad() }

/**
 * 提供默认的init方法
 */
fun initLoadSir() {
    LoadSir.beginBuilder()
        .addCallback(LoadingCallback())
        .addCallback(ErrorCallback())
        .setDefaultCallback(SuccessCallback::class.java)
        .commit()
}

fun loadSirRegister(view: View, callback: () -> Unit): LoadService<Any> {
    return LoadSir.getDefault().register(view) {
        //点击重试时触发的操作
        callback.invoke()
    }
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showError(
    message: String?,
    imageRes: Int = R.drawable.status_loading_view_loading_fail
) {
    this.setCallBack(ErrorCallback::class.java) { _, view ->
        view.findViewById<TextView>(R.id.stateContentTextView).text = message
        view.findViewById<ImageView>(R.id.stateImageView).setImageResource(imageRes)
    }
    this.showCallback(ErrorCallback::class.java)
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showEmpty(
    message: String,
    imageRes: Int = R.drawable.status_search_result_empty
) {
    showError(message, imageRes)
}

/**
 * 设置空布局
 */
fun LoadService<*>.showEmpty() {
    showEmpty("暂无此内容")
}

/**
 * 设置加载中
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}

fun ICore.showSuccessPage() {
    getLoadSir()?.showSuccess()
}

fun ICore.showLoadingPage() {
    getLoadSir()?.showLoading()
}

fun ICore.showErrorPage(
    message: String?,
    imageRes: Int = R.drawable.status_loading_view_loading_fail
) {
    getLoadSir()?.showError(message, imageRes)
}

fun ICore.showEmptyPage(
    message: String,
    imageRes: Int = R.drawable.status_search_result_empty
) {
    getLoadSir()?.showEmpty(message, imageRes)
}

fun ICore.showEmptyPage() {
    getLoadSir()?.showEmpty()
}