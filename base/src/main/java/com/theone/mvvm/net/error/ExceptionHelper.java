package com.theone.mvvm.net.error;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.theone.mvvm.R;
import com.theone.mvvm.base.BaseApplication;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * 异常处理帮助类
 * User: ljx
 * Date: 2019/04/29
 * Time: 11:15
 */
public class ExceptionHelper {

    //处理异常
    public static <T> String handleException(T throwable) {
        int stringId = R.string.oops;
        if (throwable instanceof UnknownHostException) {
            if (!isNetworkConnected(BaseApplication.INSTANCE)) {
                stringId = R.string.network_error;
            } else {
                stringId = R.string.notify_no_network;
            }
        } else if (throwable instanceof SocketTimeoutException || throwable instanceof TimeoutException) {
            //前者是通过OkHttpClient设置的超时引发的异常，后者是对单个请求调用timeout方法引发的超时异常
            stringId = R.string.time_out_please_try_again_later;
        } else if (throwable instanceof ConnectException) {
            stringId = R.string.esky_service_exception;
        }
        return BaseApplication.INSTANCE.getString(stringId);
    }

    @SuppressWarnings("deprecation")
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }
}