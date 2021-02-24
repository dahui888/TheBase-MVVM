package com.theone.mvvm.widge.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.theone.mvvm.R


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}