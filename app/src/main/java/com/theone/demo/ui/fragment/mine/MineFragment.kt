package com.theone.demo.ui.fragment.mine

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.common.ext.notNull
import com.theone.demo.R
import com.theone.demo.app.ext.joinQQGroup
import com.theone.demo.app.net.Url
import com.theone.demo.app.ext.checkLogin
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.data.model.bean.UserInfo
import com.theone.demo.databinding.FragmentMineBinding
import com.theone.demo.ui.fragment.sample.SampleFragment
import com.theone.demo.ui.fragment.setting.SettingFragment
import com.theone.demo.ui.fragment.web.WebExplorerFragment
import com.theone.demo.ui.fragment.collection.CollectionFragment
import com.theone.demo.ui.fragment.integral.IntegralHistoryFragment
import com.theone.demo.ui.fragment.integral.IntegralRankFragment
import com.theone.demo.ui.fragment.share.ShareArticleFragment
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.MineRequestViewModel
import com.theone.demo.viewmodel.MineViewModel
import com.theone.mvvm.base.IClick
import com.theone.mvvm.core.base.fragment.BaseCoreFragment
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.ext.qmui.*


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
class MineFragment : BaseCoreFragment<MineViewModel, FragmentMineBinding>(), View.OnClickListener {

    val appVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private val mRequestVm: MineRequestViewModel by viewModels()

    private lateinit var mShare: QMUICommonListItemView
    private lateinit var mCollection: QMUICommonListItemView
    private lateinit var mSetting: QMUICommonListItemView
    private lateinit var mAPI: QMUICommonListItemView
    private lateinit var mJoinUs: QMUICommonListItemView
    private lateinit var mSample: QMUICommonListItemView

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun translucentFull(): Boolean = true

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(root: View) {
        getTopBar()?.run {
            setBackgroundAlpha(0)
            updateBottomDivider(0, 0, 0, 0)
        }
        mBinding.groupListView.run {
            mCollection = createItem("我的收藏", drawable =  R.drawable.svg_mine_collection)
            mShare = createItem("我的分享", drawable =R.drawable.svg_mine_share)

            mAPI = createItem("开源网站", "玩Android", R.drawable.svg_mine_web)
            mJoinUs = createItem("加入我们", "QQ群：761201022", R.drawable.svg_mine_qq)
            mSetting = createItem("系统设置", "", R.drawable.svg_mine_setting)

            mSample = createItem("一些示例",drawable = R.drawable.svg_mine_sample)

            showTips(mSample)

            addToGroup( mCollection, mShare,listener = this@MineFragment)
            addToGroup( mAPI, mJoinUs,mSample,title = "", listener = this@MineFragment)
            addToGroup( mSetting,title = "", listener = this@MineFragment)

        }

        mBinding.swipeRefresh.setOnRefreshListener {
            mRequestVm.requestServer()
        }
    }

    override fun initData() {
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
            getErrorMsgLiveData().observeInFragment(this@MineFragment, Observer {
                showFailTipsDialog(it)
            })
            getFinallyLiveData().observeInFragment(this@MineFragment, Observer {
                mBinding.swipeRefresh.isRefreshing = false
                mRequestVm.isFirst.set(false)
            })
        }
        appVm.userInfo.observeInFragment(this, Observer { it ->
            mRequestVm.isFirst.set(false)
            setUserInfo(it)
        })
    }

    private fun requestIntegral() {
        mBinding.swipeRefresh.isRefreshing = !mRequestVm.isFirst.get()
        mRequestVm.requestServer()
    }

    private fun setUserInfo(it: UserInfo?) {
        it.notNull({
            Log.e(TAG, "setUserInfo: 111" )
            requestIntegral()
            mViewModel.run {
                name.set(it.getUserName())
                id.set("ID " + it.id)
                if (it.icon.isNotEmpty())
                    imageUrl.set(it.icon)
            }
            mBinding.swipeRefresh.isEnabled = true
        },{
            Log.e(TAG, "setUserInfo: 222" )
            mBinding.swipeRefresh.isEnabled = false
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
            mJoinUs -> {
                context?.joinQQGroup("26hK_GKmpQJbBHpfPIMlJztVmzTRyzZp")
            }
            mSetting -> {
                startFragment(SettingFragment())
            }
            mSample -> {
                startFragment(SampleFragment())
            }
        }
    }

    override fun getBindingClick(): IClick? = ProxyClick()

    inner class ProxyClick:IClick {

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