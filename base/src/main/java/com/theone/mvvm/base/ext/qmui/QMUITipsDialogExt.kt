package com.theone.mvvm.base.ext.qmui

import android.content.Context
import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.theone.mvvm.base.fragment.BaseFragment


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
 * @date 2021/3/5 0005
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */


private var loadingDialog: QMUITipDialog? = null

fun BaseFragment.showLoadingDialog(msg: CharSequence) {
    loadingDialog =  context?.createQMUIDialog(msg, QMUITipDialog.Builder.ICON_TYPE_LOADING)
}

fun BaseFragment.hideLoadingDialog(){
    loadingDialog?.dismiss()
    loadingDialog = null
}

fun BaseFragment.showMsgDialog(msg: CharSequence, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_NOTHING, callback)
}

fun BaseFragment.showInfoMsgDialog(msg: CharSequence, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_INFO, callback)
}

fun BaseFragment.showSuccessExitDialog(msg: CharSequence) {
    showSuccessDialog(msg){
        finish()
    }
}

fun BaseFragment.showSuccessDialog(msg: CharSequence, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_SUCCESS, callback)
}

fun BaseFragment.showFailDialog(msg: CharSequence, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_FAIL, callback)
}

fun Context.showTipsDialogDelayedDismiss(
    msg: CharSequence,
    type: Int,
    callback: (() -> Unit?)? = null
) {
    val dialog = createQMUIDialog(msg, type)
    android.os.Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1000)
}

fun Context.createQMUIDialog(msg: CharSequence, type: Int): QMUITipDialog {
    val dialog = QMUITipDialog.Builder(this)
        .setIconType(type)
        .setTipWord(msg)
        .create()
    dialog.run {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        show()
        return this
    }
}