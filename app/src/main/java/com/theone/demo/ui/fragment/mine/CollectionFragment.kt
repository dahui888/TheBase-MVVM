package com.theone.demo.ui.fragment.mine

import android.view.View
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.ui.fragment.mine.CollectionArticleFragment
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
class CollectionFragment :BaseTabInTitleFragment<BaseViewModel>() {

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.addLeftBackImageButton()?.setOnClickListener{
            finish()
        }
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
       tabs.add(QMUITabBean("文章"))

        fragments.add(CollectionArticleFragment())
    }

    override fun initData() {
    }

    override fun createObserver() {

    }

}