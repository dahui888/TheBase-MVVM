package com.theone.demo.ui.adapter

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.demo.BR
import com.theone.demo.R
import com.theone.demo.data.model.bean.SystemResponse
import com.theone.demo.databinding.ItemSystemBinding
import com.theone.demo.ui.fragment.category.SystemFragment
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
class SystemAdapter(val fragment: SystemFragment) :
    TheBaseQuickAdapter<SystemResponse, ItemSystemBinding>(
        R.layout.item_system
    ) {

    init {
        addBindingParams(BR.fragment,fragment)
    }

}