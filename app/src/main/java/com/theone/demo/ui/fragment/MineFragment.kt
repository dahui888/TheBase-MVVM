package com.theone.demo.ui.fragment

import android.view.View
import com.theone.demo.R
import com.theone.demo.databinding.FragmentMineBinding
import com.theone.demo.viewmodel.MineViewModel
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
    }

    override fun initData() {
    }

    override fun onClick(p0: View?) {
    }

}