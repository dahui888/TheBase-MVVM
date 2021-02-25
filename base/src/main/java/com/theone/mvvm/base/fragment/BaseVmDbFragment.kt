package com.theone.mvvm.base.fragment

import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout2
import com.theone.mvvm.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.ext.setMargin


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
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {

    lateinit var mDB: DB

    override fun createContentView(): View {
        mDB = DataBindingUtil.inflate(layoutInflater,getLayoutId(),null,false)
        mDB.lifecycleOwner = this
        return mDB.root
    }

}