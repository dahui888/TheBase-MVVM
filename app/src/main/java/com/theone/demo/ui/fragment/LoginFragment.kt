package com.theone.demo.ui.fragment

import android.view.View
import com.theone.demo.R
import com.theone.demo.viewmodel.LoginViewModel
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.demo.databinding.FragmentLoginBinding

class LoginFragment:BaseVmDbFragment<LoginViewModel,FragmentLoginBinding>() {

    override fun getLayoutId(): Int  = R.layout.fragment_login

    override fun translucentFull(): Boolean = true

    override fun initView(rootView: View) {
        getTopBar()?.run {
            addLeftImageButton(R.drawable.mz_comment_titlebar_ic_close_dark,R.id.topbar_left_button).setOnClickListener{
                finish()
            }
            updateBottomDivider(0,0,0,0)
        }
    }

    override fun onLazyInit() {

    }

    override fun createObserver() {
    }

    override fun initData() {
    }


}