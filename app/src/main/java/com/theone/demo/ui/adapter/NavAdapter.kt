package com.theone.demo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.demo.R
import com.theone.demo.data.model.bean.NavigationResponse
import com.theone.demo.databinding.ItemNavBinding
import com.theone.demo.ui.fragment.NavFragment
import com.theone.mvvm.base.fragment.BaseFragment


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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class NavAdapter(val f:NavFragment) : BaseQuickAdapter<NavigationResponse,BaseDataBindingHolder<ItemNavBinding>>(
    R.layout.item_nav
),LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ItemNavBinding>, item: NavigationResponse) {
       holder.dataBinding?.run {
           vm = item
           fragment = f
       }
    }

}