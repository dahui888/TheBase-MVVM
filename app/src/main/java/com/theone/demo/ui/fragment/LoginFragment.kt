package com.theone.demo.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.app.util.UserUtil
import com.theone.demo.viewmodel.LoginViewModel
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.demo.databinding.FragmentLoginBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.*

class LoginFragment : BaseVmDbFragment<LoginViewModel, FragmentLoginBinding>() {

    val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun translucentFull(): Boolean = true

    override fun initView(rootView: View) {
        getTopBar()?.run {
            addLeftImageButton(
                R.drawable.mz_comment_titlebar_ic_close_dark,
                R.id.topbar_left_button
            ).setOnClickListener {
                finish()
            }
            updateBottomDivider(0, 0, 0, 0)
        }
    }

    override fun onLazyInit() {
    }

    override fun createObserver() {
        mViewModel.getResponseLiveData().observe(viewLifecycleOwner, Observer {
            mAppVm.userInfo.value = it
            UserUtil.setUser(it)
            showSuccessExitDialog("登录成功")
        })
        mViewModel.getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
            showFailDialog(it)
        })
    }

    override fun initData() {
        mBinding.run {
            vm = mViewModel
            click = ProxyClick()
        }
    }

    inner class ProxyClick {

        fun login() {
            when {
                mViewModel.account.value.isEmpty() -> showFailDialog("请填写账号")
                mViewModel.password.get().isEmpty() -> showFailDialog("请填写密码")
                else -> {
                    mViewModel.requestServer()
                }
            }
        }
    }

    /**
     * 更改进出动画效果 QMUIFragment提供
     */
    override fun onFetchTransitionConfig(): TransitionConfig  = SCALE_TRANSITION_CONFIG

}