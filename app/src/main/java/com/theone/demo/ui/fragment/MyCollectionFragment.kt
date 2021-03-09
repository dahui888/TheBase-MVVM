package com.theone.demo.ui.fragment

import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.mvvm.base.entity.QMUITabBean
import com.theone.mvvm.base.fragment.BaseTabInTitleFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel


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
class MyCollectionFragment :BaseTabInTitleFragment<BaseViewModel>() {

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
       tabs.add(QMUITabBean("文章"))
        tabs.add(QMUITabBean("网址"))
    }

    override fun initData() {
    }

    override fun createObserver() {

    }

}