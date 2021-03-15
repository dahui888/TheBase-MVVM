package com.theone.demo.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.app.util.UserUtil
import com.theone.demo.viewmodel.LoginRegisterViewModel
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.demo.databinding.FragmentLoginRegisterBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.*

class LoginRegisterItemFragment :
    BaseVmDbFragment<LoginRegisterViewModel, FragmentLoginRegisterBinding>() {

    companion object {
        fun newInstant(isRegister: Boolean): LoginRegisterItemFragment {
            return LoginRegisterItemFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(TYPE, isRegister)
                }
            }
        }

        const val TYPE = "TYPE"
    }

    val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }


    override fun getLayoutId(): Int = R.layout.fragment_login_register

    override fun initView(rootView: View) {
        val isRegister = requireArguments().getBoolean(TYPE, false)
        mViewModel.isRegister.set(isRegister)
    }

    override fun onLazyInit() {
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@LoginRegisterItemFragment, Observer {
                mAppVm.userInfo.value = it
                UserUtil.setUser(it)
                showSuccessExitDialog(if (mViewModel.isRegister.get()) "注册成功" else "登录成功")
            })
            getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
                showFailDialog(it)
            })
        }

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
                mViewModel.isRegister.get() && mViewModel.repassword.get()
                    .isEmpty() -> showFailDialog("请填写确认密码")
                else -> {
                    mViewModel.requestServer()
                }
            }
        }
    }


}