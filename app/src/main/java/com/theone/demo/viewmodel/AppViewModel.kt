package com.theone.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.app.util.UserUtil
import com.theone.demo.data.model.bean.CollectBus
import com.theone.demo.data.model.bean.UserInfo
import com.theone.mvvm.base.viewmodel.BaseViewModel

class AppViewModel:BaseViewModel() {

    //App的账户信息
    var userInfo = UnPeekLiveData.Builder<UserInfo>().setAllowNullValue(true).create()

    //全局收藏，在任意一个地方收藏或取消收藏，监听该值的界面都会收到消息
    val collectEvent = MutableLiveData<CollectBus>()

    //App 列表动画
    var appAnimation = UnPeekLiveData<Int>()

    init {
        userInfo.value = UserUtil.getUser()
        //初始化列表动画
        appAnimation.value = CacheUtil.getAnimationType()
    }

}