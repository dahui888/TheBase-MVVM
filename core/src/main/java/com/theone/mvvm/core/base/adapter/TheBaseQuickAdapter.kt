package com.theone.mvvm.core.base.adapter

import android.util.SparseArray
import androidx.annotation.NonNull
import androidx.core.util.forEach
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.theone.mvvm.core.BR
import com.theone.mvvm.ext.addParams


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
 * @date 2021/3/25 09:25
 * @describe 简单封装下DataBinding带有加载更多模块Adapter
 * @email 625805189@qq.com
 * @remark
 */
abstract class TheBaseQuickAdapter<T, BD : ViewDataBinding>(layout: Int) :
    BaseQuickAdapter<T, BaseDataBindingHolder<BD>>(
        layout
    ), LoadMoreModule {

    private val bindingParams: SparseArray<Any> = SparseArray()

    protected fun addBindingParams(
        @NonNull variableId: Int,
        @NonNull any: Any
    ) {
        bindingParams.addParams(variableId,any)
    }

    override fun convert(holder: BaseDataBindingHolder<BD>, item: T) {
        holder.dataBinding?.run {
            this.setVariable(BR.item, item)
            bindingParams.forEach { key, any ->
                setVariable(key, any)
            }
        }
    }

}