package com.theone.demo.viewmodel

import com.theone.demo.app.net.Url
import com.theone.mvvm.core.ext.request
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

class SettingViewModel:BaseRequestViewModel<String>() {

    override fun requestServer() {

    }

    fun loginOut(){
        request({
           val res =  RxHttp.get(Url.LOGIN_OUT)
                .toResponse<String>()
                .await()
            onSuccess(res)
        },"退出中")
    }

}