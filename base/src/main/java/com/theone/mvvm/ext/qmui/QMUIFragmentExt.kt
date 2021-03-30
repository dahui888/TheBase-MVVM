package com.theone.mvvm.ext.qmui

import android.content.Context
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
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
 * @date 2021-03-30 13:15
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

fun QMUIFragment.showLoadingDialog(msg: CharSequence?) {
    context?.showLoadingDialog(msg)
}

fun QMUIFragment.showMsgTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_NOTHING, delay, callback)
}

fun QMUIFragment.showInfoTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_INFO, delay, callback)
}

fun BaseQMUIFragment.showSuccessTipsExitDialog(msg: CharSequence?) {
    context?.showSuccessTipsDialog(msg) {
        finish()
    }
}

fun QMUIFragment.showSuccessTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_SUCCESS, delay, callback)
}

fun QMUIFragment.showFailTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_FAIL, delay, callback)
}
