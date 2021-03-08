package com.theone.demo.ui.fragment

import androidx.fragment.app.Fragment
import com.theone.demo.R
import com.theone.mvvm.base.fragment.BaseHomeFragment
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.base.entity.QMUITabBean


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
        tabs.add(QMUITabBean("首页",R.drawable.svg_home,R.drawable.svg_home_select))
        tabs.add(QMUITabBean("项目",R.drawable.svg_project,R.drawable.svg_project_selected))
        tabs.add(QMUITabBean("分类",R.drawable.svg_classfication,R.drawable.svg_classification_selected))
        tabs.add(QMUITabBean("公众号",R.drawable.svg_wx_gzh,R.drawable.svg_wx_gzh_selected))
        tabs.add(QMUITabBean("我的",R.drawable.svg_mine,R.drawable.svg_mine_selected))

        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
        fragments.add(ClassificationFragment())
        fragments.add(WxGzhFragment())
        fragments.add(MineFragment())
    }

    override fun createObserver() {
    }

    override fun initData() {
    }




}