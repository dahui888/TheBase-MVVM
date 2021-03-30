package com.theone.mvvm.ext.qmui

import androidx.annotation.StringRes
import com.qmuiteam.qmui.widget.QMUITopBarLayout
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
 * @date 2021/2/22 0022
 * @describe QMUI相关组件的一些扩展函数
 * @email 625805189@qq.com
 * @remark
 */

fun QMUITopBarLayout.setTitleWithBackBtn(title:String, fragment:BaseQMUIFragment){
    setTitle(title)
    addLeftBackImageButton().setOnClickListener {
        fragment.finish()
    }
}

fun QMUITopBarLayout.setTitleWithBackBtn(@StringRes resId:Int, fragment:BaseQMUIFragment){
    setTitleWithBackBtn(context.getString(resId),fragment)
}