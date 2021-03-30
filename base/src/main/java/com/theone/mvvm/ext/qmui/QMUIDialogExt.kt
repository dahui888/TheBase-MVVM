package com.theone.mvvm.ext.qmui

import android.content.Context
import android.content.DialogInterface
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialog.MenuDialogBuilder
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction.ACTION_PROP_POSITIVE
import com.qmuiteam.qmui.widget.dialog.QMUIDialogBuilder


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
 * @date 2021/3/15 0015
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

/**
 * 消息类型弹窗
 */
fun Context.showMsgDialog(
    title: String?,
    message: CharSequence?,
    leftAction: String? = "取消",
    rightAction: String? = "确定",
    listener: QMUIDialogAction.ActionListener,
    @QMUIDialogAction.Prop prop: Int = ACTION_PROP_POSITIVE
): QMUIDialog {
    val builder = QMUIDialog.MessageDialogBuilder(this)
    return builder.run {
        setTitleNonNull(title)
        if (!message.isNullOrEmpty()) {
            setMessage(message)
        }
        leftAction?.let {
            addAction(it, listener)
        }
        rightAction?.let {
            addAction(0, it, prop, listener)
        }
        show()
    }
}

/**
 * 菜单类型弹窗
 */
fun Context.showMenuDialog(
    title: String? = null,
    items: Array<CharSequence?>,
    listener: DialogInterface.OnClickListener
): QMUIDialog {
    val builder = MenuDialogBuilder(this)
    return builder.run {
        setTitleNonNull(title)
        addItems(items, listener)
        show()
    }
}

/**
 * 单选类型弹窗
 */
fun <T:CharSequence> Context.showSingleChoiceDialog(
    title: String? = null,
    items: Array<T>,
    checkedIndex: Int,
    listener: DialogInterface.OnClickListener? = null
): QMUIDialog {
    val builder = QMUIDialog.CheckableDialogBuilder(this)
    return builder.run {
        setTitleNonNull(title)
        setCheckedIndex(checkedIndex)
        addItems(items, listener)
        show()
    }
}

/**
 * 多选类型弹窗
 */
fun <T:CharSequence>  Context.showMultiChoiceDialog(
    title: String? = null,
    items: Array<T>,
    checkedItems: IntArray,
    leftAction: String,
    rightAction: String,
    listener: OnMultiChoiceConfirmClickListener
): QMUIDialog {
    val builder = QMUIDialog.MultiCheckableDialogBuilder(this)
    with(builder){
        setTitleNonNull(title)
        addItems(items,null)
        setCheckedItems(checkedItems)
        addAction(leftAction) { dialog, _ -> dialog?.dismiss() }
        addAction(rightAction) { dialog, _ -> listener.onItemSelected(dialog,builder.checkedItemIndexes) }
    }
    return builder.show()
}


private fun <T : QMUIDialogBuilder<T>> T.setTitleNonNull(
    title: String?
) {
    if (!title.isNullOrEmpty()) {
        setTitle(title)
    }
}

interface OnMultiChoiceConfirmClickListener{
    fun onItemSelected(dialog:QMUIDialog,checkedItems: IntArray)
}
