package com.theone.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.theone.demo.app.util.UserUtil
import com.theone.demo.data.model.bean.UserInfo
import com.theone.mvvm.base.viewmodel.BaseViewModel

class AppViewModel:BaseViewModel() {

    //App的账户信息
    var userInfo = MutableLiveData<UserInfo>()

    init {
        userInfo.value = UserUtil.getUser()
    }

}