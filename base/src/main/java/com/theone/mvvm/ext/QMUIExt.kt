package com.theone.mvvm.ext

import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.widget.QMUITopBarLayout
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
 * @date 2021/2/22 0022
 * @describe QMUI相关组件的一些扩展函数
 * @email 625805189@qq.com
 * @remark
 */

fun QMUITopBarLayout.setTitleWithBackBtn(title:String){
    setTitle(title)
    addLeftBackImageButton()
}

fun QMUITopBarLayout.setTitleWithBackBtn(resId:Int,fragment:BaseFragment){
    setTitle(resId)
    addLeftBackImageButton().setOnClickListener {
        fragment.finish()
    }
}