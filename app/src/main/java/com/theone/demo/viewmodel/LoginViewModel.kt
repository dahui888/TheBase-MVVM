package com.theone.demo.viewmodel

import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.UserInfo
import com.theone.mvvm.base.ext.request
import com.theone.mvvm.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.callback.databind.StringObservableField
import com.theone.mvvm.callback.livedata.StringLiveData
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

class LoginViewModel : BaseRequestViewModel<UserInfo>() {

    var account = StringLiveData()
    var password = StringObservableField()

    override fun requestServer() {
        request({
            val res = RxHttp.postForm(Url.LOGIN)
                .add("username", account.value)
                .add("password", password.get())
                .toResponse<UserInfo>()
                .await()
            onSuccess(res)
        },"登录中")
    }

}