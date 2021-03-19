package com.theone.demo.ui.fragment.mine

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.app.ext.joinQQGroup
import com.theone.demo.app.net.Url
import com.theone.demo.app.util.checkLogin
import com.theone.demo.app.util.notNull
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.data.model.bean.UserInfo
import com.theone.demo.databinding.FragmentMineBinding
import com.theone.demo.ui.fragment.SettingFragment
import com.theone.demo.ui.fragment.WebExplorerFragment
import com.theone.demo.ui.fragment.collection.CollectionFragment
import com.theone.demo.ui.fragment.integral.IntegralHistoryFragment
import com.theone.demo.ui.fragment.integral.IntegralRankFragment
import com.theone.demo.ui.fragment.share.ShareArticleFragment
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.MineRequestViewModel
import com.theone.demo.viewmodel.MineViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.addToGroup
import com.theone.mvvm.base.ext.qmui.createDetailItem
import com.theone.mvvm.base.ext.qmui.showFailDialog
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
    private lateinit var mAPI: QMUICommonListItemView
    private lateinit var mTheBase: QMUICommonListItemView
    private lateinit var mJoinUs: QMUICommonListItemView

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun translucentFull(): Boolean = true

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(rootView: View) {
        getTopBar()?.run {
            setBackgroundAlpha(0)
            updateBottomDivider(0, 0, 0, 0)
        }
        groupListView.run {
            mCollection = createDetailItem("我的收藏", "", R.drawable.svg_mine_collection)
            mShare = createDetailItem("我的分享", "", R.drawable.svg_mine_share)

            mAPI = createDetailItem("开源网站", "玩Android", R.drawable.svg_mine_web)
            mTheBase = createDetailItem("项目地址", "TheBase-MVVM", R.drawable.svg_mine_project_address)
            mJoinUs = createDetailItem("加入我们", "QQ群：761201022", R.drawable.svg_mine_qq)
            mSetting = createDetailItem("系统设置", "", R.drawable.svg_mine_setting)
        }
        groupListView.addToGroup(this, mCollection, mShare)
        groupListView.addToGroup("", this, mAPI, mTheBase, mJoinUs)
        groupListView.addToGroup("", this, mSetting)

        swipeRefresh.setOnRefreshListener {
            mRequestVm.requestServer()
        }
        setUserInfo(appVm.userInfo.value)
    }

    override fun onLazyInit() {
        appVm.userInfo.value?.run {
            requestIntegral()
        }
    }

    override fun createObserver() {
        mRequestVm.run {
            getResponseLiveData().observeInFragment(this@MineFragment, Observer {
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
                swipeRefresh.isEnabled = true
                mRequestVm.isFirst.set(false)
            })
        }
        appVm.userInfo.observeInFragment(this, Observer { it ->
            mRequestVm.isFirst.set(false)
            setUserInfo(it)
        })
    }

    override fun initData() {
        mBinding.run {
            vm = mViewModel
            click = ProxyClick()
        }
    }

    private fun requestIntegral() {
        swipeRefresh.isRefreshing = !mRequestVm.isFirst.get()
        mRequestVm.requestServer()
    }

    private fun setUserInfo(it: UserInfo?) {
        it.notNull({
            requestIntegral()
            mViewModel.run {
                name.set(it.getUserName())
                id.set("ID " + it.id)
                if (it.icon.isNotEmpty())
                    imageUrl.set(it.icon)
            }
        },{
            mViewModel.run {
                name.set("请先登录~")
                id.set("ID")
                integral.set("积分")
                rank.set("排名")
                level.set("等级")
            }
        })

    }

    override fun onClick(p0: View?) {
        when (p0) {
            mShare -> checkLogin {
                startFragment(ShareArticleFragment())
            }
            mCollection -> checkLogin {
                startFragment(CollectionFragment())
            }
            mAPI -> startFragment(
                WebExplorerFragment.newInstance(
                    BannerResponse(
                        title = "玩Android",
                        url = Url.BASE_URL
                    )
                )
            )
            mTheBase -> startFragment(
                WebExplorerFragment.newInstance(
                    BannerResponse(
                        title = "TheBase-MVVM",
                        url = "https://gitee.com/theoneee/the-base-mvvm"
                    )
                )
            )
            mJoinUs -> {
                joinQQGroup("26hK_GKmpQJbBHpfPIMlJztVmzTRyzZp")
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

        /**
         * 积分历史
         */
        fun integralHistory() {
            checkLogin {
                startFragment(IntegralHistoryFragment())
            }
        }

        /**
         * 积分排行
         */
        fun integralRank() {
            checkLogin {
                mRequestVm.getResponseLiveData().value?.let {
                    startFragment(IntegralRankFragment.newInstance(it))
                }
            }
        }

    }

}