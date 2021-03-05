package com.theone.demo.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.demo.R
import com.theone.demo.app.util.checkLogin
import com.theone.demo.app.util.notNull
import com.theone.demo.data.model.bean.UserInfo
import com.theone.demo.databinding.FragmentMineBinding
import com.theone.demo.viewmodel.AppViewModel
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

    private lateinit var mShare : QMUICommonListItemView
    private lateinit var mCollection : QMUICommonListItemView
    private lateinit var mSetting : QMUICommonListItemView

    val appVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun translucentFull(): Boolean = true

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(rootView: View) {
        getTopBar()?.run {
            setBackgroundAlpha(0)
            updateBottomDivider(0, 0, 0, 0)
        }
        mCollection = groupListView.createDetailItem("我的收藏", "", R.drawable.svg_mine_collection)
        mShare = groupListView.createDetailItem("我的分享", "", R.drawable.svg_mine_article)

        mSetting = groupListView.createDetailItem("设置", "", R.drawable.svg_mine_setting)

        groupListView.addToGroup(null, this, mCollection, mShare)
        groupListView.addToGroup(null, null, this, mSetting)

        swipeRefresh.isEnabled = false
        swipeRefresh.setOnRefreshListener {
            mVm.requestServer()
        }
    }

    override fun onLazyInit() {
        appVm.userinfo.value?.let {
            mVm.requestServer()
        }
    }

    override fun createObserver() {
        appVm.userinfo.observe(viewLifecycleOwner, Observer {
            it.notNull({
                setUserInfo(it)
                mVm.requestServer()
            }, {
                mVm.name.set("请先登录~")
                mVm.integral.set("积分")
                mVm.rank.set("排名")
            })
        })
        mVm.getResponse().observe(viewLifecycleOwner, Observer {
            it.run {
                mVm.integral.set("积分 $coinCount")
                mVm.rank.set("排名 $rank")
                mVm.level.set("等级 $level")
            }
        })
        mVm.getErrorMsg().observe(viewLifecycleOwner, Observer {
            showFailDialog(it)
        })
        mVm.getFinallyLiveData().observe(viewLifecycleOwner, Observer {
            onRefreshingEnd()
            mVm.isFirst.set(false)
        })
    }

    override fun initData() {
        mDB.run {
            vm = mVm
            click = ProxyClick()
        }
        appVm.userinfo.value?.let {
            setUserInfo(it)
            mVm.requestServer()
        }
    }

    private fun setUserInfo(it: UserInfo) {
        onRefreshingEnd()
        mVm.name.set(it.getUserName())
        mVm.id.set("ID "+ it.id)
        if (it.icon.isNotEmpty())
            mVm.imageUrl.set(it.icon)
    }

    private fun onRefreshingEnd(){
        swipeRefresh.isEnabled = true
        swipeRefresh.isRefreshing = false
    }

    override fun onClick(p0: View?) {
        when(p0){
            mShare -> checkLogin{
                startFragment(MyShareArticleFragment())
            }
            mCollection -> checkLogin{

            }
            mSetting ->{

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