package com.theone.demo.ui.fragment

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.app.util.notNull
import com.theone.demo.databinding.FragmentMineBinding
import com.theone.demo.ui.activity.LoginActivity
import com.theone.demo.viewmodel.AppViewModel
import com.theone.demo.viewmodel.MineViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.ext.qmui.addToGroup
import com.theone.mvvm.base.ext.qmui.createDetailItem
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding
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
class MineFragment : BaseVmDbFragment<MineViewModel, FragmentMineBinding>(),View.OnClickListener {

    val appVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun translucentFull(): Boolean = true

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(rootView: View) {
        getTopBar()?.run {
            setBackgroundAlpha(0)
            updateBottomDivider(0,0,0,0)
        }
        val collection =groupListView.createDetailItem("我的收藏","",R.drawable.svg_mine_collection)
        val article =groupListView.createDetailItem("我的文章","",R.drawable.svg_mine_article)

        val setting =groupListView.createDetailItem("设置","",R.drawable.svg_mine_setting)

        groupListView.addToGroup(null,this,collection,article)
        groupListView.addToGroup(null,null,this,setting)
    }

    override fun onLazyInit() {
    }

    override fun createObserver() {
        appVm.userinfo.observe(viewLifecycleOwner, Observer {
            it.notNull({
                mVm.name.set(if (it.nickname.isEmpty()) it.username else it.nickname)
                mVm.requestServer()
            }, {
                mVm.name.set("请先登录~")
                mVm.integral.set("积分")
                mVm.rank.set("排名")
            })
        })
    }

    override fun initData() {
        mDB.run {
            vm = mVm
            click = ProxyClick()
        }
    }

    override fun onClick(p0: View?) {
    }

    inner class ProxyClick{

        fun doLogin(){
            startActivity(Intent(mActivity,LoginActivity::class.java))
        }

    }

}