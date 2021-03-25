package com.theone.mvvm.ext.qmui

import android.content.Context
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.base.fragment.BaseQMUIFragment


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

fun BaseQMUIFragment.showLoadingDialog(msg: CharSequence) {
    loadingDialog =  context?.createQMUIDialog(msg, QMUITipDialog.Builder.ICON_TYPE_LOADING)
}

fun BaseQMUIFragment.hideLoadingDialog(){
    loadingDialog?.dismiss()
    loadingDialog = null
}

fun showMsgDialog(msg: CharSequence, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    BaseApplication.INSTANCE.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_NOTHING, delay,callback)
}

fun BaseQMUIFragment.showMsgDialog(msg: CharSequence, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_NOTHING, delay,callback)
}

fun BaseQMUIFragment.showInfoMsgDialog(msg: CharSequence, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_INFO,delay, callback)
}

fun BaseQMUIFragment.showSuccessExitDialog(msg: CharSequence) {
    showSuccessDialog(msg){
        finish()
    }
}

fun BaseQMUIFragment.showSuccessDialog(msg: CharSequence, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_SUCCESS, delay,callback)
}

fun BaseQMUIFragment.showFailDialog(msg: CharSequence, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDialogDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_FAIL, delay,callback)
}

fun Context.showTipsDialogDelayedDismiss(
    msg: CharSequence,
    type: Int,
    delay: Long = 1000,
    callback: (() -> Unit?)? = null
) {
    val dialog = createQMUIDialog(msg, type)
    android.os.Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, delay)
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