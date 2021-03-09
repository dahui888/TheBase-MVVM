package com.theone.mvvm.base.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.theone.mvvm.widge.loadsir.core.LoadService
import com.theone.mvvm.widge.loadsir.core.LoadSir
import com.theone.mvvm.R
import com.theone.mvvm.base.fragment.BaseFragment
import com.theone.mvvm.widge.loadsir.callback.ErrorCallback
import com.theone.mvvm.widge.loadsir.callback.LoadingCallback


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


fun loadSirInit(view: View, callback: () -> Unit): LoadService<Any> {
    return LoadSir.getDefault().register(view) {
        //点击重试时触发的操作
        callback.invoke()
    }
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showError(message: String, imageRes:Int = R.drawable.status_loading_view_loading_fail) {
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
fun LoadService<*>.showEmpty(message: String,imageRes:Int = R.drawable.status_search_result_empty) {
    showError(message,imageRes)
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

fun BaseFragment.showContentPage(){
    mLoadSir.showSuccess()
}

fun BaseFragment.showLoadingPage(){
    mLoadSir.showLoading()
}

fun BaseFragment.showErrorPage(message: String, imageRes:Int = R.drawable.status_loading_view_loading_fail){
    mLoadSir.showError(message,imageRes)
}

fun BaseFragment.showEmptyPage(message: String, imageRes:Int = R.drawable.status_search_result_empty){
    mLoadSir.showEmpty(message,imageRes)
}

fun BaseFragment.showEmptyPage(){
    mLoadSir.showEmpty()
}