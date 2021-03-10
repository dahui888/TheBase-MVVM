package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.mvvm.base.constant.LayoutManagerType
import com.theone.mvvm.callback.livedata.BooleanLiveData
import com.theone.mvvm.callback.livedata.IntLiveData
import com.theone.mvvm.base.net.IPageInfo


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

    val mPageInfo: MutableLiveData<IPageInfo> = MutableLiveData()
    val isFirst: BooleanLiveData = BooleanLiveData()
    val isFresh: BooleanLiveData = BooleanLiveData()
    val firstLoadSuccess: BooleanLiveData = BooleanLiveData()
    val mFirstPage: IntLiveData = IntLiveData()
    val mPage: IntLiveData = IntLiveData()
    var goneLoadMoreEndView: Boolean = false

    val type: MutableLiveData<LayoutManagerType> = MutableLiveData()
    val column :IntLiveData = IntLiveData()
    val space :IntLiveData = IntLiveData()

    init {
        type.value =  LayoutManagerType.LIST
        column.value = 2
        space.value = 0
        mFirstPage.value = 1
    }

    protected open fun onSuccess( response:List<T>?, pageInfo: IPageInfo? ){
        super.onSuccess(response)
        mPageInfo.value = pageInfo
    }

    fun getPage():Int = mPage.value

    fun requestNewData(){
        mPage.value = mFirstPage.value
        requestServer()
    }

}