package com.theone.mvvm.base.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.theone.mvvm.callback.livedata.BooleanLiveData
import com.theone.mvvm.callback.livedata.IntLiveData
import com.theone.mvvm.net.IResponse


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
open abstract class BaseListViewModel<T> : BaseViewModel() {

    private val mListData: MutableLiveData<IResponse<List<T>>> = MutableLiveData()
    var isHeadFresh :Boolean = false
    var mPage: Int = 1
    var goneLoadMoreEndView: Boolean = false

    override fun createObserve(lifecycleOwner: LifecycleOwner) {
    }

    abstract fun requestServer()

    fun getResponse(): MutableLiveData<IResponse<List<T>>> = mListData

}