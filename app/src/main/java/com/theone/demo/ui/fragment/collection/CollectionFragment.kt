package com.theone.demo.ui.fragment.collection

import android.view.View
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.data.entity.QMUITabBean
import com.theone.mvvm.core.ext.qmui.addTab
import com.theone.mvvm.core.base.fragment.BaseTabInTitleFragment


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
       tabs.addTab("文章")

        fragments.add(CollectionArticleFragment())
    }

}