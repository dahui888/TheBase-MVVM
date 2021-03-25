package com.theone.mvvm.core.ext.qmui

import android.content.Context
import androidx.viewpager.widget.ViewPager
import com.qmuiteam.qmui.widget.tab.QMUITab
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.mvvm.ext.util.getDrawable
import com.theone.mvvm.core.data.entity.QMUITabBean


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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

fun QMUITabBuilder.createTab(
    context: Context,
    text: CharSequence,
    normal: Int,
    select: Int
): QMUITab {
    setText(text)
    if (normal != -1)
        setNormalDrawable(getDrawable(context, normal))
    if (normal != -1)
        setSelectedDrawable(
            getDrawable(
                context,
                select
            )
        )
    return build(context)
}

fun QMUITabBuilder.createTab(context: Context, tab: QMUITabBean): QMUITab {
    return createTab(context, tab.title, tab.normal, tab.select)
}

fun QMUITabSegment.init(viewPager:ViewPager,tabs: List<QMUITabBean>, builder: QMUITabBuilder) {
    for (tab in tabs) {
        addTab(builder.createTab(context, tab))
    }
    setupWithViewPager(viewPager,false)
}