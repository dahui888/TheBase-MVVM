package com.theone.demo.viewmodel

import com.theone.demo.app.net.Url
import com.theone.demo.data.model.bean.UserInfo
import com.theone.mvvm.core.ext.request
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.callback.databind.BooleanObservableField
import com.theone.mvvm.callback.databind.StringObservableField
import com.theone.mvvm.callback.livedata.StringLiveData
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

class LoginRegisterViewModel : BaseRequestViewModel<UserInfo>() {

    var account = StringLiveData()
    var password = StringObservableField()
    var repassword = StringObservableField()

    var isRegister = BooleanObservableField()

    override fun requestServer() {
        request({
            val res = RxHttp.postForm(if (isRegister.get()) Url.REGISTER else Url.LOGIN)
                .add("username", account.value)
                .add("password", password.get())
                .add("repassword", repassword.get(), isRegister.get())
                .toResponse<UserInfo>()
                .await()
            onSuccess(res)
        }, if (isRegister.get()) "注册中" else "登录中")
    }

}