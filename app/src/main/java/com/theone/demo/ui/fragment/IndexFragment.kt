package com.theone.demo.ui.fragment

import android.graphics.Typeface
import android.view.View
import androidx.fragment.app.Fragment
import com.theone.demo.R
import com.theone.mvvm.base.adapter.TabFragmentAdapter
import com.theone.mvvm.base.fragment.BaseFragment
import com.theone.mvvm.base.fragment.BaseHomeFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.entity.QMUITabBean
import com.theone.mvvm.ext.qmui.init
import kotlinx.android.synthetic.main.fragment_index.*


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
 * @date 2021/3/1 0001
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class IndexFragment : BaseHomeFragment<BaseViewModel>() {

    override fun isNeedChangeStatusBarMode(): Boolean = false

    override fun initTabAndFragments(tabs: MutableList<QMUITabBean>, fragments: MutableList<Fragment>) {
        tabs.add(QMUITabBean("首页",R.drawable.ic_home_normal,R.drawable.ic_home_selected))
        tabs.add(QMUITabBean("项目",R.drawable.ic_classification_normal,R.drawable.ic_classification_selected))
        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
    }

    override fun createObserver() {
    }

    override fun initData() {
    }




}