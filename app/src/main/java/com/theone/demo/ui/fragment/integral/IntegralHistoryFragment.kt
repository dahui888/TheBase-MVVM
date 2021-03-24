package com.theone.demo.ui.fragment.integral

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.IntegralHistoryResponse
import com.theone.demo.ui.adapter.IntegralHistoryAdapter
import com.theone.demo.ui.fragment.BasePagerListFragment
import com.theone.demo.viewmodel.IntegralHistoryViewModel
import com.theone.mvvm.base.ext.qmui.setTitleWithBackBtn
import com.theone.mvvm.core.databinding.BaseRecyclerPagerFragmentBinding


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
 * @date 2021/3/17 0017
 * @describe 积分记录
 * @email 625805189@qq.com
 * @remark
 */
class IntegralHistoryFragment:BasePagerListFragment<IntegralHistoryResponse,IntegralHistoryViewModel, BaseRecyclerPagerFragmentBinding>() {

    override fun createAdapter(): BaseQuickAdapter<IntegralHistoryResponse, *> = IntegralHistoryAdapter()

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            setTitleWithBackBtn("积分记录",this@IntegralHistoryFragment)
        }
    }

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }
    override fun getRecyclerView(): RecyclerView = mBinding.recyclerView

    override fun getRefreshLayout(): SwipeRefreshLayout = mBinding.swipeRefresh


}