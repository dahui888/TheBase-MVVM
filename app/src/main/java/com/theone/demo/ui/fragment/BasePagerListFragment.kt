package com.theone.demo.ui.fragment

import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.app.ext.setAdapterAnimation
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.core.base.fragment.BasePagerSwipeRefreshFragment
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel


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
 * @date 2021/3/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BasePagerListFragment<T, VM : BaseListViewModel<T>, DB : ViewDataBinding> :
    BasePagerSwipeRefreshFragment<T, VM,DB>() {

    protected val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    /**
     * 第一次请求成功后是否自动刷新（第一次的数据从Cache里获取的)
     */
    fun isFirstLoadSuccessAutoRefresh()= false

    override fun getItemSpace(): Int = 12

    override fun initAdapter() {
        super.initAdapter()
        mAdapter.setAdapterAnimation(mAppVm.appAnimation.value)
    }

    override fun createObserver() {
        super.createObserver()
//        mViewModel.getFirstLoadSuccessLiveData().observeInFragment(this, Observer {
//            getRecyclerView().setBackgroundColor(
//                ContextCompat.getColor(
//                    mActivity,
//                    R.color.qmui_config_color_background
//                )
//            )
//            if(isFirstLoadSuccessAutoRefresh()){
//                onRefresh()
//            }
//        })
        mAppVm.appAnimation.observeInFragment(this, Observer {
            mAdapter.setAdapterAnimation(it)
        })
    }

    override fun onFirstLoadSuccess(data: List<T>) {
        super.onFirstLoadSuccess(data)
        getRecyclerView().setBackgroundColor(
            ContextCompat.getColor(
                mActivity,
                R.color.qmui_config_color_background
            )
        )
        if(isFirstLoadSuccessAutoRefresh()){
            onRefresh()
        }
    }

}