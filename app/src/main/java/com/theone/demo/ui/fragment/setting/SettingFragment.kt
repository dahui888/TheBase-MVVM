package com.theone.demo.ui.fragment.setting

import android.content.DialogInterface
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.util.QMUIPackageHelper
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.databinding.FragmentSettingBinding
import com.theone.demo.ui.fragment.web.WebExplorerFragment
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.SettingViewModel
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.core.base.fragment.BaseCoreFragment
import com.theone.mvvm.ext.qmui.*

class SettingFragment : BaseCoreFragment<SettingViewModel, FragmentSettingBinding>(),
    View.OnClickListener {

    /**
     * 可以改变一下这两个方法试试看看效果
     */
    override fun showTopBar(): Boolean = true
    override fun translucentFull(): Boolean = false

    private val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private lateinit var mLoginOut: QMUICommonListItemView
    private lateinit var mVersion: QMUICommonListItemView
    private lateinit var mAnimation: QMUICommonListItemView
    private lateinit var mTheme: QMUICommonListItemView
    private lateinit var mAuthor: QMUICommonListItemView
    private lateinit var mTheBase: QMUICommonListItemView
    private lateinit var mLauncherTips: QMUICommonListItemView


    private val mAnimationTypes: Array<String> by lazy {
        resources.getStringArray(R.array.setting_list_animations)
    }

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun initView(rootView: View) {
        mBinding.groupListView.run {
            mAnimation = createItem(
                "列表动画",
                mAnimationTypes[CacheUtil.getAnimationType()],
                R.drawable.svg_setting_animation
            )
            mTheme = createItem("主题颜色", drawable = R.drawable.svg_setting_theme)

            mLauncherTips = createSwitchItem("启动页文字效果",
                null,
                R.drawable.svg_setting_launcher_text,
                CacheUtil.isOpenLauncherText(),
                CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    CacheUtil.setLauncherText(isChecked)
                })

            mVersion = createItem(
                "当前版本",
                "Ver " + QMUIPackageHelper.getAppVersion(mActivity),
                R.drawable.svg_setting_version
            )
            mAuthor = createItem(
                "项目作者",
                "The one",
                R.drawable.svg_setting_author
            )

            mTheBase = createItem("项目地址", "TheBase-MVVM", R.drawable.svg_setting_project)

            addToGroup(
                mAnimation,
                mTheme,
                mLauncherTips,
                title = "个性化",
                listener = this@SettingFragment
            )
            addToGroup(mVersion, mAuthor, mTheBase, title = "关于", listener = this@SettingFragment)

            mLoginOut = createItem("退出账号", drawable = R.drawable.svg_setting_login_out)
            if (CacheUtil.isLogin()) {
                addToGroup(mLoginOut, listener = this@SettingFragment, title = "")
            }
        }
        getTopBar()?.run {
            setTitleWithBackBtn("设置", this@SettingFragment)
        }
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@SettingFragment, Observer {
                mAppVm.userInfo.value = null
                CacheUtil.loginOut()
                showSuccessTipsExitDialog("退出成功")
            })
            getErrorMsgLiveData().observeInFragment(this@SettingFragment, Observer {
                showFailTipsDialog(it)
            })
        }
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
                showMsgTipsDialog("开发中...")
            }
            mTheBase -> startFragment(
                WebExplorerFragment.newInstance(
                    BannerResponse(
                        title = "TheBase-MVVM",
                        url = "https://gitee.com/theoneee/the-base-mvvm"
                    )
                )
            )
        }
    }

    private fun showLoginOutDialog() {
        context?.showMsgDialog(
            "提示", "是否退出当前账号",
            listener = QMUIDialogAction.ActionListener { dialog, index ->
                dialog.dismiss()
                if (index > 0) {
                    mViewModel.loginOut()
                }
            }, prop = QMUIDialogAction.ACTION_PROP_NEGATIVE
        )
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

}