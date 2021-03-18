package com.theone.demo.ui.fragment

import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.theone.demo.R
import com.theone.demo.app.ext.setAdapterAnimation
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.base.ext.getAppViewModel
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding


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
    BaseRecyclerPagerFragment<T, VM,DB>() {

    protected val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    fun isFirstLoadSuccessRefresh()= false

    override fun getItemSpace(): Int = 12

    override fun initAdapter() {
        super.initAdapter()
        mAdapter.setAdapterAnimation(mAppVm.appAnimation.value)
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.getFirstLoadSuccessLiveData().observeInFragment(this, Observer {
            getRecyclerView().setBackgroundColor(
                ContextCompat.getColor(
                    mActivity,
                    R.color.qmui_config_color_background
                )
            )
            if(isFirstLoadSuccessRefresh()){
                onRefresh()
            }
        })
        mAppVm.appAnimation.observeInFragment(this, Observer {
            mAdapter.setAdapterAnimation(it)
        })
    }

}