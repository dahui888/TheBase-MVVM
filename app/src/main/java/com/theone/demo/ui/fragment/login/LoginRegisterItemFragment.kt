package com.theone.demo.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.app.util.UserUtil
import com.theone.demo.viewmodel.LoginRegisterViewModel
import com.theone.demo.databinding.FragmentLoginRegisterBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.ext.getValueNonNull
import com.theone.mvvm.ext.qmui.showFailDialog
import com.theone.mvvm.ext.qmui.showSuccessExitDialog
import com.theone.mvvm.core.fragment.BaseCoreFragment

class LoginRegisterItemFragment :
    BaseCoreFragment<LoginRegisterViewModel, FragmentLoginRegisterBinding>() {

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

    private val isRegister :Boolean by getValueNonNull(TYPE)

    override fun getLayoutId(): Int = R.layout.fragment_login_register

    override fun initView(rootView: View) {
        mViewModel.isRegister.set(isRegister)
    }

    override fun onLazyInit() {
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@LoginRegisterItemFragment, Observer {
                mAppVm.userInfo.value = it
                UserUtil.setUser(it)
                showSuccessExitDialog(if (isRegister.get()) "注册成功" else "登录成功")
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