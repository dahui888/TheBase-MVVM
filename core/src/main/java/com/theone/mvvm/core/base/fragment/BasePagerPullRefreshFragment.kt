package com.theone.mvvm.core.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.theone.mvvm.core.R
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import kotlinx.android.synthetic.main.base_recycler_pager_fragment.*


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
 * @date 2021/2/23 0023
 * @describe RecyclerView分页显示基类
 * @email 625805189@qq.com
 * @remark 给定了默认的下拉刷新控件 SwipeRefreshLayout
 */
abstract class BasePagerPullRefreshFragment
<T, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BasePagerAdapterFragment<T, VM, DB>(),
    SwipeRefreshLayout.OnRefreshListener {

    override fun getLayoutId(): Int = R.layout.base_recycler_pager_fragment
    override fun getRecyclerView(): RecyclerView = recyclerView
    protected open fun getRefreshLayout(): SwipeRefreshLayout = swipeRefresh

    /**
     * 初始化下拉刷新
     */
    override fun initPullRefreshView() {
        getRefreshLayout().run {
            isEnabled = false
            setOnRefreshListener(this@BasePagerPullRefreshFragment)
        }
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        getRefreshLayout().isRefreshing = true
        super.onRefresh()
    }

    /**
     * 刷新完成时的操作
     */
    override fun onRefreshComplete() {
        getRefreshLayout().run {
            isEnabled = true
            isRefreshing = false
        }
    }

}