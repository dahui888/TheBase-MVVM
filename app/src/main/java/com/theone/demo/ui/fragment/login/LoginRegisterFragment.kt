package com.theone.demo.ui.fragment.login

import android.view.View
import com.qmuiteam.qmui.arch.QMUIFragment
import com.theone.demo.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.data.entity.QMUITabBean
import com.theone.mvvm.core.fragment.BaseTabInTitleFragment


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
 * @date 2021/3/15 0015
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class LoginRegisterFragment:BaseTabInTitleFragment<BaseViewModel>() {

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            addLeftImageButton(
                R.drawable.mz_comment_titlebar_ic_close_dark,
                R.id.topbar_left_button
            ).setOnClickListener {
                popBackStack()
            }
            updateBottomDivider(0, 0, 0, 0)
        }
        startInit()
    }

    override fun onLazyInit() {
    }

    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {
        tabs.add(QMUITabBean("登录"))
        tabs.add(QMUITabBean("注册"))
        fragments.add(LoginRegisterItemFragment.newInstant(false))
        fragments.add(LoginRegisterItemFragment.newInstant(true))
    }

    override fun initData() {
    }

    override fun createObserver() {
    }

    /**
     * 更改进出动画效果 QMUIFragment提供
     */
    override fun onFetchTransitionConfig(): TransitionConfig  = SCALE_TRANSITION_CONFIG

}