package com.theone.demo.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.app.util.UserUtil
import com.theone.demo.app.util.checkLogin
import com.theone.demo.app.util.notNull
import com.theone.demo.data.model.bean.UserInfo
import com.theone.demo.databinding.FragmentMineBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.MineRequestViewModel
import com.theone.demo.viewmodel.MineViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.addToGroup
import com.theone.mvvm.base.ext.qmui.createDetailItem
import com.theone.mvvm.base.ext.qmui.showFailDialog
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import kotlinx.android.synthetic.main.fragment_mine.*


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
 * @date 2021/3/4 0004
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class MineFragment : BaseVmDbFragment<MineViewModel, FragmentMineBinding>(), View.OnClickListener {

    val appVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private val mRequestVm: MineRequestViewModel by viewModels()

    private lateinit var mShare: QMUICommonListItemView
    private lateinit var mCollection: QMUICommonListItemView
    private lateinit var mSetting: QMUICommonListItemView

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun translucentFull(): Boolean = true

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(rootView: View) {
        getTopBar()?.run {
            setBackgroundAlpha(0)
            updateBottomDivider(0, 0, 0, 0)
        }
        mCollection = groupListView.createDetailItem("我的收藏", "", R.drawable.svg_mine_collection)
        mShare = groupListView.createDetailItem("我的分享", "", R.drawable.svg_mine_share)

        mSetting = groupListView.createDetailItem("设置", "", R.drawable.svg_mine_setting)

        groupListView.addToGroup(null, this, mCollection, mShare)
        groupListView.addToGroup(null, null, this, mSetting)

        swipeRefresh.setOnRefreshListener {
            mRequestVm.requestServer()
        }
    }

    override fun onLazyInit() {
        appVm.userInfo.value?.let {
            mRequestVm.requestServer()
        }
    }

    override fun createObserver() {
        appVm.userInfo.observe(viewLifecycleOwner, Observer { it ->
            it.notNull({
                setUserInfo(it)
            }, {
                resetUserInfo()
            })
        })

        if (UserUtil.isLogin())
            mRequestVm.run {
                getResponseLiveData().observe(viewLifecycleOwner, Observer {
                    it.run {
                        mViewModel.integral.set("积分 $coinCount")
                        mViewModel.rank.set("排名 $rank")
                        mViewModel.level.set("等级 $level")
                    }
                })
                getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
                    showFailDialog(it)
                })
                getFinallyLiveData().observe(viewLifecycleOwner, Observer {
                    swipeRefresh.isRefreshing = false
                    mRequestVm.isFirst.set(false)
                })
            }
    }

    override fun initData() {
        mBinding.run {
            vm = mViewModel
            click = ProxyClick()
        }
//        appVm.userInfo.value.notNull(
//            {
//                setUserInfo(it)
//            }, {
//                resetUserInfo()
//            }
//        )
    }

    private fun setUserInfo(it: UserInfo) {
        "setUserInfo ".logE(TAG)
        mRequestVm.requestServer()
        swipeRefresh.isEnabled = true
        mViewModel.run {
            name.set(it.getUserName())
            id.set("ID " + it.id)
            if (it.icon.isNotEmpty())
                imageUrl.set(it.icon)
        }
    }

    private fun resetUserInfo() {
        "resetUserInfo ".logE(TAG)
        swipeRefresh.isEnabled = false
        mViewModel.run {
            name.set("请先登录~")
            id.set("ID")
            integral.set("积分")
            rank.set("排名")
            level.set("等级")
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            mShare -> checkLogin {
                startFragment(MyShareArticleFragment())
            }
            mCollection -> checkLogin {

            }
            mSetting -> {
                startFragment(SettingFragment())
            }
        }
    }

    inner class ProxyClick {

        fun doLogin() {
            checkLogin {

            }
        }

    }

}