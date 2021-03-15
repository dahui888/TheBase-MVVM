package com.theone.demo.ui.fragment

import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.theone.demo.R
import com.theone.demo.ui.fragment.category.ClassificationFragment
import com.theone.demo.ui.fragment.home.HomeFragment
import com.theone.demo.ui.fragment.mine.MineFragment
import com.theone.demo.ui.fragment.project.ProjectFragment
import com.theone.demo.ui.fragment.gzh.WxGzhFragment
import com.theone.mvvm.base.fragment.BaseIndexTabFragment
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
class IndexFragment : BaseIndexTabFragment<BaseViewModel>() {

    override fun isNeedChangeStatusBarMode(): Boolean = false

    override fun createTabBuilder(): QMUITabBuilder? {
        return super.createTabBuilder().also {
            // 选中图标的颜色可以在这里设置
            it?.skinChangeWithTintColor(true)
        }
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
        // 选中图标的颜色也可以在这里设置 - R.drawable.svg_home_select 本身的颜色的是灰色的，选中变色是因为 skinChangeWithTintColor = true
        tabs.add(QMUITabBean("首页", R.drawable.svg_home, R.drawable.svg_home_select))
        // skinChangeWithTintColor = false 时   颜色直接使用的是选中图标本身的颜色 使用 ?attr/app_skin_primary_color 填充
        tabs.add(QMUITabBean("项目", R.drawable.svg_project, R.drawable.svg_project_selected))
        tabs.add(QMUITabBean("分类", R.drawable.svg_classfication, R.drawable.svg_classification_selected))
        tabs.add(QMUITabBean("公众号", R.drawable.svg_wx_gzh, R.drawable.svg_wx_gzh_selected))
        tabs.add(QMUITabBean("我的", R.drawable.svg_mine, R.drawable.svg_mine_selected))

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