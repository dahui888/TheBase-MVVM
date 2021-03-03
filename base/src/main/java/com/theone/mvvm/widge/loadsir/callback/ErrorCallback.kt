package com.theone.mvvm.widge.loadsir.callback

import com.theone.mvvm.widge.loadsir.callback.Callback
import com.theone.mvvm.R


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}