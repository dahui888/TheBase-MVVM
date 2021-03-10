package com.theone.demo.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialog.MessageDialogBuilder
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.app.util.UserUtil
import com.theone.demo.databinding.FragmentSettingBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.SettingViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.goneViews
import com.theone.mvvm.base.ext.qmui.*
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : BaseVmDbFragment<SettingViewModel,FragmentSettingBinding>(), View.OnClickListener,
    QMUIDialogAction.ActionListener {

    val appVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private lateinit var mLoginOut: QMUICommonListItemView

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun initView(rootView: View) {
        mLoginOut = groupListView.createNormalItem("退出账号")

        groupListView.addToGroup(this, mLoginOut)

        if (!UserUtil.isLogin()) {
            goneViews(mLoginOut)
        }

        getTopBar()?.run {
            setTitleWithBackBtn("设置", this@SettingFragment)
        }
    }

    override fun onLazyInit() {

    }

    override fun initData() {

    }

    override fun createObserver() {
        mViewModel.getResponseLiveData().observe(viewLifecycleOwner, Observer {
            appVm.userInfo.value = null
            UserUtil.loginOut()
            showSuccessExitDialog("退出成功")
        })
        mViewModel.getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
            showFailDialog(it)
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            mLoginOut -> {
                showLoginOutDialog()
            }
        }
    }

    private fun showLoginOutDialog() {
        MessageDialogBuilder(context)
            .setTitle("提示")
            .setMessage("是否退出当前账号？")
            .addAction("取消", this)
            .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEGATIVE, this)
            .show()
    }

    override fun onClick(dialog: QMUIDialog?, index: Int) {
        dialog?.dismiss()
        if (index > 0) {
            mViewModel.loginOut()
        }
    }

}