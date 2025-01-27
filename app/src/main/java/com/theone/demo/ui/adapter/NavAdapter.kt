package com.theone.demo.ui.adapter

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.demo.BR
import com.theone.demo.R
import com.theone.demo.data.model.bean.NavigationResponse
import com.theone.demo.databinding.ItemNavBinding
import com.theone.demo.ui.fragment.category.NavFragment
import com.theone.mvvm.core.base.adapter.TheBaseQuickAdapter


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
class NavAdapter( f: NavFragment) : TheBaseQuickAdapter<NavigationResponse, ItemNavBinding>(
    R.layout.item_nav
) {

    init {
        addBindingParams(BR.fragment,f)
    }

}