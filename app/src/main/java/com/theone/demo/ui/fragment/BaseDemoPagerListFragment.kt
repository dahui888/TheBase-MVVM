package com.theone.demo.ui.fragment

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.R
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment
import com.theone.mvvm.base.viewmodel.BaseListViewModel


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
abstract class BaseDemoPagerListFragment<T, VM : BaseListViewModel<T>>:BaseRecyclerPagerFragment<T,VM>() {

    override fun createObserver() {
        super.createObserver()
        mVm.firstLoadSuccess.observe(viewLifecycleOwner, Observer {
            getRecyclerView().setBackgroundColor(
                ContextCompat.getColor(
                    mActivity,
                    R.color.qmui_config_color_background
                )
            )
        })
    }

}