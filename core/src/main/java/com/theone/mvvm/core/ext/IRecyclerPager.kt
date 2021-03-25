package com.theone.mvvm.core.ext

import androidx.recyclerview.widget.RecyclerView
import com.theone.mvvm.core.data.enum.LayoutManagerType
import com.theone.mvvm.core.viewmodel.BaseListViewModel

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
 * @date 2021-03-25 10:03
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
interface IRecyclerPager {

    /**
     * 初始化适配器
     */
    fun initAdapter()

    /**
     * 初始化RecyclerView
     */
    fun initRecyclerView()

    /**
     * 初始化下拉刷新
     */
    fun initPullRefreshView()

    /**
     * 第一次显示时的加载情况
     */
    fun onFirstLoading()

    /**
     * 刷新时的操作
     */
    fun onRefresh()

    /**
     * 回到界面自动刷新，刷新前可能是空白页，也可能存在数据
     */
    fun onAutoRefresh()

    /**
     * 刷新完成（无论成功与失败）
     */
    fun onRefreshComplete()

    /**
     * 加载更多
     */
    fun onLoadMore()

    /**
     * 获取LayoutManager
     */
    fun getLayoutManager(): RecyclerView.LayoutManager

    /**
     * 获取LayoutManager
     */
    fun getLayoutManagerType(): LayoutManagerType

    /**
     * 分割线
     */
    fun getItemDecoration():RecyclerView.ItemDecoration

    /**
     * 获取列数
     */
    fun getSpanCount():Int

    /**
     * 获取间距大小
     */
    fun getItemSpace():Int

}