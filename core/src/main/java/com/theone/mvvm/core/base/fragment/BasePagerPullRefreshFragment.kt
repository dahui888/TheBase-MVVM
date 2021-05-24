package com.theone.mvvm.core.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUICenterGravityRefreshOffsetCalculator
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout.OnPullListener
import com.theone.mvvm.core.R
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import com.theone.mvvm.core.widge.loadsir.callback.ErrorCallback
import com.theone.mvvm.core.widge.pullrefresh.PullRefreshLayout
import kotlinx.android.synthetic.main.base_pull_fresh_fragment.*

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
 * @date 2021-04-08 09:04
 * @describe RecyclerView分页显示基类
 * @email 625805189@qq.com
 * @remark 给定了默认的下拉刷新控件 PullRefreshLayout
 */
abstract class BasePagerPullRefreshFragment<T, VM : BaseListViewModel<T>, DB : ViewDataBinding> :
    BasePagerAdapterFragment<T, VM, DB>(),
    OnPullListener {

    override fun getLayoutId(): Int = R.layout.base_pull_fresh_fragment

    override fun getRecyclerView(): RecyclerView = recyclerView

    protected open fun getRefreshLayout(): PullRefreshLayout = refresh_layout

    override fun initPullRefreshView() {
        getRefreshLayout().run {
            setDragRate(0.5f)
            setRefreshOffsetCalculator(QMUICenterGravityRefreshOffsetCalculator())
            setOnPullListener(this@BasePagerPullRefreshFragment)
            isEnabled = false
        }
    }

    override fun onMoveTarget(offset: Int) {

    }

    override fun onMoveRefreshView(offset: Int) {

    }

    override fun onFirstLoadSuccess(data: List<T>) {
        super.onFirstLoadSuccess(data)
        setPullLayoutEnabled(true)
    }

    override fun onRefreshSuccess(data: List<T>) {
        super.onRefreshSuccess(data)
        setPullLayoutEnabled(true)
    }

    override fun onRefreshError(errorMsg: String?) {
        super.onRefreshError(errorMsg)
        setPullLayoutEnabled(true)
    }

    override fun onAutoRefresh() {
        if (mLoadSir?.currentCallback is ErrorCallback) {
            onFirstLoading()
        } else {
            // 这里要调用PullRefreshLayout的主动刷新方法，后会自动回到到onRefresh 方法请求数据
            getRefreshLayout().setToRefreshDirectly()
        }
    }

    protected open fun setPullLayoutEnabled(enabled: Boolean) {
        getRefreshLayout().run {
            isEnabled = enabled
            finishRefresh()
        }
    }

}