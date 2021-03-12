package com.theone.mvvm.base.ext.qmui

import android.text.TextUtils
import android.view.View
import android.widget.CompoundButton
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView.QMUICommonListItemAccessoryType
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView.QMUICommonListItemOrientation
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView


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
 * @describe QMUIGroupListView
 * @email 625805189@qq.com
 * @remark
 */

fun QMUIGroupListView.createItem(
    @DrawableRes drawable: Int = -1,
    title: CharSequence,
    detail: CharSequence?,
    @QMUICommonListItemOrientation position: Int,
    @QMUICommonListItemAccessoryType type: Int
): QMUICommonListItemView {
    val item = createItemView(title)
    return item.apply {
        if (!TextUtils.isEmpty(detail)) {
            detailText = detail
            orientation = position
        }
        accessoryType = type
        if (drawable != -1) {
            setImageDrawable(ContextCompat.getDrawable(context, drawable))
        }
    }
}

fun QMUIGroupListView.createNormalItem(
    title: CharSequence,
    @DrawableRes drawable: Int = -1
    , @QMUICommonListItemAccessoryType type: Int = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
): QMUICommonListItemView = createItem(
    drawable,
    title,
    null,
    QMUICommonListItemView.HORIZONTAL,
    type
)

fun QMUIGroupListView.createDetailItem(
    title: CharSequence,
    detail: CharSequence?,
    @DrawableRes drawable: Int = -1,
    @QMUICommonListItemOrientation position: Int = QMUICommonListItemView.HORIZONTAL,
    @QMUICommonListItemAccessoryType type: Int = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
): QMUICommonListItemView = createItem(
    drawable,
    title,
    detail,
    position,
    type
)

fun QMUIGroupListView.createSwitchItem(
    title: CharSequence,
    detail: CharSequence?,
    isCheck: Boolean,
    @DrawableRes drawable: Int = -1,
    listener: CompoundButton.OnCheckedChangeListener?
): QMUICommonListItemView {
    return createItem(
        drawable,
        title,
        detail,
        QMUICommonListItemView.VERTICAL,
        QMUICommonListItemView.ACCESSORY_TYPE_SWITCH
    ).apply {
        switch.isChecked = isCheck
        switch.setOnCheckedChangeListener(listener)
    }
}

fun QMUIGroupListView.createCustomViewItem(
    title: CharSequence,
    detail: CharSequence?,
    @DrawableRes drawable: Int = -1,
    view: View
): QMUICommonListItemView {
    return createItem(
        drawable,
        title,
        detail,
        QMUICommonListItemView.VERTICAL,
        QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM
    ).apply {
        addAccessoryCustomView(view)
    }
}

fun QMUICommonListItemView.removeIconTintColor() {
    val config = QMUICommonListItemView.SkinConfig()
    config.iconTintColorRes = 0
    setSkinConfig(config)
}

private fun showTips(
    showLeft: Boolean,
    isDot: Boolean,
    vararg items: QMUICommonListItemView
) {
    val p =
        if (showLeft) QMUICommonListItemView.TIP_POSITION_LEFT else QMUICommonListItemView.TIP_POSITION_RIGHT
    for (item in items) {
        item.setTipPosition(p)
        if (isDot)
            item.showRedDot(true)
        else
            item.showNewTip(true)
    }
}

fun showNewTips(
    showLeft: Boolean,
    vararg items: QMUICommonListItemView
) {
    showTips(showLeft, false, *items)
}

fun showRedDots(
    showLeft: Boolean,
    vararg items: QMUICommonListItemView
) {
    showTips(showLeft, false, *items)
}

fun QMUIGroupListView.addToGroup(
    title: CharSequence?,
    description: CharSequence?,
    listener: View.OnClickListener?,
    vararg items: QMUICommonListItemView
) {
    val section = QMUIGroupListView.newSection(context)
    if(null == title){
        section.setUseDefaultTitleIfNone(false).setUseTitleViewForSectionSpace(false)
    }else{
        section.setTitle(title)
    }
    if(null != description){
        section.setDescription(description)
    }
    for (item in items) {
        val li = if (null != item.switch) null else listener
        section.addItemView(item, li)
    }
    section.addTo(this)
}

fun QMUIGroupListView.addToGroup(
    title: CharSequence?,
    listener: View.OnClickListener?,
    vararg items: QMUICommonListItemView
) = addToGroup(title, null, listener, *items)

fun QMUIGroupListView.addToGroup(
    listener: View.OnClickListener?,
    vararg items: QMUICommonListItemView
) = addToGroup(null, null, listener, *items)
