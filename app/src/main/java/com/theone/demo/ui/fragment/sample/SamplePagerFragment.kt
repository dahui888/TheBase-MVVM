package com.theone.demo.ui.fragment.sample

import android.content.DialogInterface
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.R
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.HomeViewModel
import com.theone.mvvm.core.base.fragment.BasePagerPullRefreshFragment
import com.theone.mvvm.core.databinding.BasePullFreshFragmentBinding
import com.theone.mvvm.core.widge.pullrefresh.PullRefreshLayout
import com.theone.mvvm.ext.qmui.setTitleWithBackBtn
import com.theone.mvvm.ext.qmui.showSingleChoiceDialog

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
 * @date 2021-04-08 10:51
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SamplePagerFragment:
    BasePagerPullRefreshFragment<ArticleResponse, HomeViewModel, BasePullFreshFragmentBinding>() {

    private val mStyle = arrayOf("QMUIDefaultStyle","WWLoadingStyle","FlymeLoadingStyle")

    override fun createAdapter(): BaseQuickAdapter<ArticleResponse, *> = ArticleAdapter()

    override fun initView(root: View) {
        super.initView(root)
        getTopBar()?.run {
            setTitleWithBackBtn("更换下拉刷新的示例",this@SamplePagerFragment)
            setSubTitle(mStyle[PullRefreshLayout.Style])
            addRightImageButton(R.drawable.mz_titlebar_ic_more_dark,R.id.topbar_right_button1).setOnClickListener{
                showChangeStyleDialog()
            }
        }
    }

   private fun showChangeStyleDialog(){
        context?.showSingleChoiceDialog("选择刷新样式",mStyle,PullRefreshLayout.Style,
            DialogInterface.OnClickListener {
                    dialog, which ->
                    dialog.dismiss()
                    // TODO 把默认样式放值Application里
                    PullRefreshLayout.initStyle(which)
                    startFragmentAndDestroyCurrent(SamplePagerFragment())
            })
    }

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}