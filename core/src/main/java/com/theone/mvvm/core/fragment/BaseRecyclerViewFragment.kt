package com.theone.mvvm.core.fragment

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
 * @remark 自行处理Adapter+RecyclerView的请继承此类
 */
abstract class BaseRecyclerViewFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseCoreFragment<VM, DB>(), IRecyclerPager {

    abstract fun getRecyclerView(): RecyclerView

    override fun getLayoutManagerType(): LayoutManagerType = LayoutManagerType.LIST
    override fun getSpanCount(): Int = 1
    override fun getItemSpace(): Int = 0

    override fun initView(rootView: View) {
        initAdapter()
        initRecyclerView()
        initPullRefreshView()
    }

    override fun initRecyclerView() {
        getRecyclerView().init(getLayoutManager())
    }

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

    override fun onLazyInit() {
        onFirstLoading()
    }

    /**
     * 错误、空界面点击事件
     */
    override fun onErrorPageClick() {
        onFirstLoading()
    }

}