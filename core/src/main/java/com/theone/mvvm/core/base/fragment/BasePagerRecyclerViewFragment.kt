package com.theone.mvvm.core.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.data.enum.LayoutManagerType
import com.theone.mvvm.core.ext.createLayoutManager
import com.theone.mvvm.core.ext.init
import com.theone.mvvm.core.widge.loadsir.callback.ErrorCallback

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
 * @date 2021-03-25 10:08
 * @describe RecyclerView基类
 * @email 625805189@qq.com
 * @remark 自行处理Adapter+下拉刷新的请继承此类
 */
abstract class BasePagerRecyclerViewFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseCoreFragment<VM, DB>(), IRecyclerPager {

    /**
     * 获取 RecyclerView.LayoutManager 类型
     * @return LayoutManagerType
     */
    override fun getLayoutManagerType(): LayoutManagerType = LayoutManagerType.LIST

    /**
     * 网格中的列数
     * @return Int
     */
    override fun getSpanCount(): Int = 2

    /**
     * 间距
     * @return Int
     */
    override fun getItemSpace(): Int = 0

    override fun initView(rootView: View) {
        initAdapter()
        initRecyclerView()
        initPullRefreshView()
    }

    /**
     * 对RRecyclerView进行默认初始化
     * 如需自定义，重写
     */
    override fun initRecyclerView() {
        getRecyclerView().init(getLayoutManager())
    }

    /**
     * 这里默认根据[getLayoutManagerType]提供常见的三种
     * 如有需要，重写此方法返回
     * @return RecyclerView.LayoutManager
     */
    override  fun getLayoutManager(): RecyclerView.LayoutManager{
        return mActivity.createLayoutManager(getLayoutManagerType(),getSpanCount())
    }

    /**
     * 当再次回到此界面时，自动刷新数据
     * 此时要分两种情况：
     * 1.界面显示空页面或者错误页面，此时需要调用 onFirstLoading() -> 这里面必须加入此方法 showLoadingPage()
     * 2.界面已经存在数据，此时需要调用头部刷新 onRefresh()
     */
    override fun onAutoRefresh() {
        if (mLoadSir?.currentCallback is ErrorCallback) {
            onFirstLoading()
        } else {
            onRefresh()
        }
    }

    /**
     * 懒加载 - 开始第一次请求数据
     */
    override fun onLazyInit() {
        onFirstLoading()
    }

    /**
     * 错误、空界面点击事件
     */
    override fun onPageReLoad() {
        onFirstLoading()
    }

}