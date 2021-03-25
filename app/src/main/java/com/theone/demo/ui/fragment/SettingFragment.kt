package com.theone.demo.ui.fragment

import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.util.QMUIPackageHelper
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.app.util.UserUtil
import com.theone.demo.databinding.FragmentSettingBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.SettingViewModel
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.core.fragment.BaseCoreFragment
import com.theone.mvvm.ext.qmui.*

class SettingFragment : BaseCoreFragment<SettingViewModel, FragmentSettingBinding>(),
    View.OnClickListener,
    QMUIDialogAction.ActionListener {

    private val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private lateinit var mLoginOut: QMUICommonListItemView
    private lateinit var mVersion: QMUICommonListItemView
    private lateinit var mAnimation: QMUICommonListItemView
    private lateinit var mTheme: QMUICommonListItemView
    private lateinit var mAuthor: QMUICommonListItemView

    private val mAnimationTypes: Array<String> by lazy {
        resources.getStringArray(R.array.setting_list_animations)
    }

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun initView(rootView: View) {
        mBinding.groupListView.run {
            mAnimation = createDetailItem(
                "列表动画",
                mAnimationTypes[CacheUtil.getAnimationType()],
                R.drawable.svg_setting_animation
            )
            mTheme = createNormalItem("主题颜色", R.drawable.svg_setting_theme)
            mVersion = createDetailItem(
                "当前版本",
                "Ver " + QMUIPackageHelper.getAppVersion(mActivity),
                R.drawable.svg_setting_version
            )
            mAuthor = createDetailItem(
                "项目作者",
                "The one",
                R.drawable.svg_setting_author
            )

            addToGroup("个性化", this@SettingFragment, mAnimation, mTheme)
            addToGroup("关于", this@SettingFragment, mVersion, mAuthor)

            if (UserUtil.isLogin()) {
                mLoginOut = createNormalItem("退出账号", R.drawable.svg_setting_login_out)
                addToGroup("", this@SettingFragment, mLoginOut)
            }
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
        mViewModel.getResponseLiveData().observeInFragment(this, Observer {
            mAppVm.userInfo.value = null
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
            mAnimation -> {
                showAnimationSelectDialog()
            }
            mTheme -> {
                showMsgDialog("开发中...")
            }
        }
    }

    private fun showLoginOutDialog() {
        context?.showMsgDialog("提示", "是否退出当前账号", this, QMUIDialogAction.ACTION_PROP_NEGATIVE)
    }

    private fun showAnimationSelectDialog() {
        context?.showSingleChoiceDialog("列表动画", mAnimationTypes, CacheUtil.getAnimationType(),
            DialogInterface.OnClickListener { dialog, index ->
                dialog.dismiss()
                mAppVm.appAnimation.value = index
                mAnimation.detailText = mAnimationTypes[index]
                CacheUtil.setAnimationType(index)
            })
    }

    override fun onClick(dialog: QMUIDialog?, index: Int) {
        dialog?.dismiss()
        if (index > 0) {
            mViewModel.loginOut()
        }
    }

}