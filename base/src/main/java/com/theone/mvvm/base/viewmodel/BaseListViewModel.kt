package com.theone.mvvm.base.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.theone.mvvm.base.constant.LayoutManagerType
import com.theone.mvvm.callback.livedata.BooleanLiveData
import com.theone.mvvm.callback.livedata.IntLiveData
import com.theone.mvvm.callback.livedata.UnPeekLiveData
import com.theone.mvvm.ext.util.logE
import com.theone.mvvm.net.IResponse
import okhttp3.Response


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
 * @date 2021/2/23 0023
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseListViewModel<T> : BaseRequestViewModel<List<T>>() {

    var isFirstLoad: BooleanLiveData = BooleanLiveData()
    var isHeadFresh: BooleanLiveData = BooleanLiveData()
    var mPage: IntLiveData = IntLiveData()
    var goneLoadMoreEndView: Boolean = false

    var type: UnPeekLiveData<LayoutManagerType> = UnPeekLiveData()
    var column :IntLiveData = IntLiveData()
    var space :IntLiveData = IntLiveData()

    init {
        type.value =  LayoutManagerType.LIST
        column.value = 2
        space.value = 0
    }

    abstract fun requestServer()


}