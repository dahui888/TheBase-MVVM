package com.theone.mvvm.base.activity

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
 * @date 2021-03-31 15:18
 * @describe ViewModel+DataBinding基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>(),
    IBaseDataBinding {

    lateinit var mBinding: DB

    private val mBindingParams: SparseArray<Any> = SparseArray()

    /**
     * @return Int  视图里绑定的ViewModel ID
     * @remark 如果使用默认值，则都要命名为 vm ,如果不一致,重写此方法返回
     */
    override fun getBindingVmId(): Int = BR.vm

    /**
     *
     * @return Int 视图里绑定的点击事件 ID
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
    override fun addBindingParams(
        @NonNull variableId: Int,
        @NonNull any: Any
    ) {
        mBindingParams.addParams(variableId, any)
    }

    override fun createContentView(): View {
        mBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
        mBinding.run {
            lifecycleOwner = this@BaseVmDbActivity
            setVariable(getBindingVmId(), mViewModel)
            getBindingClick()?.let {
                setVariable(getBindingClickId(), it)
            }
            mBindingParams.forEach { key, any ->
                setVariable(key, any)
            }
        }
        return mBinding.root
    }

}