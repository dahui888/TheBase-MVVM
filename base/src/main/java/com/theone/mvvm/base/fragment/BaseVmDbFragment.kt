package com.theone.mvvm.base.fragment

import android.util.SparseArray
import android.view.View
import androidx.annotation.NonNull
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.theone.mvvm.BR
import com.theone.mvvm.base.IBaseDataBinding
import com.theone.mvvm.base.IClick
import com.theone.mvvm.base.viewmodel.BaseViewModel
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
 * @date 2021/2/22 0022
 * @describe ViewModel+DataBinding基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>(),
    IBaseDataBinding {

    lateinit var mBinding: DB

    private val mBindingParams: SparseArray<Any> = SparseArray()

    /**
     * 视图里绑定的ViewModel ID
     * @return Int
     */
    override fun getBindingVmId(): Int = BR.vm

    /**
     * 视图里绑定的点击事件 ID
     * @return Int
     */
    override fun getBindingClickId(): Int = BR.click

    /**
     * 视图里绑定的点击事件 - 需实现[IClick]
     * @return Int
     */
    override fun getBindingClick(): IClick? = null

    /**
     * 添加绑定
     * @param variableId Int 绑定的ID
     * @param any Any 绑定的内容
     * @remark kotlin方法默认都是final类型，子类不能重写此方法。
     */
    override fun addBindingParams(@NonNull variableId: Int,
                                   @NonNull any: Any){
        mBindingParams.addParams(variableId,any)
    }

    /**
     * 创建内容层 - 这一层使用DataBinding绑定
     * @return View
     */
    override fun createContentView(): View {
        mBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
        mBinding.run {
            lifecycleOwner = this@BaseVmDbFragment
            this.setVariable(getBindingVmId(), mViewModel)
            getBindingClick()?.let {
                this.setVariable(getBindingClickId(),it)
            }
            mBindingParams.forEach { key, any ->
                this.setVariable(key, any)
            }
        }
        return mBinding.root
    }

}