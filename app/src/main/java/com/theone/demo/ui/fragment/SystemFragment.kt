package com.theone.demo.ui.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.SystemResponse
import com.theone.demo.ui.adapter.SystemAdapter
import com.theone.demo.viewmodel.SystemViewModel
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
 * @date 2021/3/4 0004
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SystemFragment:BaseDemoPagerListFragment<SystemResponse,SystemViewModel>() {

    override fun createAdapter(): SystemAdapter  = SystemAdapter(this)

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}