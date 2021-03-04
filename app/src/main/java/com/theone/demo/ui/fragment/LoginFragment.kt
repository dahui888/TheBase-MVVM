package com.theone.demo.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.theone.demo.R
import com.theone.demo.viewmodel.LoginViewModel
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.demo.databinding.FragmentLoginBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.util.logE
import java.util.logging.Handler

class LoginFragment : BaseVmDbFragment<LoginViewModel, FragmentLoginBinding>() {


    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

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
        mVm.getResponse().observe(viewLifecycleOwner, Observer {
            appViewModel.userinfo.postValue(it)
            showSuccessMsg("登录成功"){
                popBackStack()
            }
        })
        mVm.getErrorMsg().observe(viewLifecycleOwner, Observer {
            showFailMsg(it){}
        })
    }

    override fun initData() {
        mDB.vm = mVm
        mDB.click = ProxyClick()
    }

    private fun showSuccessMsg(msg: String,callback: () -> Unit) {
        showTipsDialog(msg, QMUITipDialog.Builder.ICON_TYPE_SUCCESS,callback)
    }

    private fun showFailMsg(msg: String,callback: () -> Unit) {
        showTipsDialog(msg, QMUITipDialog.Builder.ICON_TYPE_FAIL,callback)
    }

    private var mLoadingDialog: QMUITipDialog? = null

    private fun showLoadingDialog(msg: String) {
        mLoadingDialog = createQMUIDialog(msg,QMUITipDialog.Builder.ICON_TYPE_LOADING)
        mLoadingDialog?.run {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
    }

    private fun hideLoadingDialog(){
        mLoadingDialog?.dismiss()
    }

    private fun showTipsDialog(msg: String, type: Int,callback: () -> Unit) {
        hideLoadingDialog()
        val dialog = createQMUIDialog(msg, type)
        dialog.run {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
        android.os.Handler().postDelayed({
            dialog.dismiss()
            callback?.invoke()
        }, 1000)
    }

    private fun createQMUIDialog(msg: String, type: Int): QMUITipDialog {
        return QMUITipDialog.Builder(context)
            .setIconType(type)
            .setTipWord(msg)
            .create()
    }

    inner class ProxyClick {

        fun login() {
            when {
                mVm.account.value.isEmpty() -> showFailMsg("请填写账号"){}
                mVm.password.get().isEmpty() -> showFailMsg("请填写密码"){}
                else -> {
                    showLoadingDialog("登录中")
                    mVm.requestServer()
                }
            }
        }
    }

}