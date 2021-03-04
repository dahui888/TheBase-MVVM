package com.theone.demo.viewmodel

import com.theone.demo.data.model.bean.UserInfo
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.callback.livedata.UnPeekLiveData

class AppViewModel:BaseViewModel() {

    //App的账户信息
    var userinfo = UnPeekLiveData<UserInfo>()

}