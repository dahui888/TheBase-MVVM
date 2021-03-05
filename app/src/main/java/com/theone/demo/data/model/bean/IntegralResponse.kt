package com.theone.demo.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 积分
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class IntegralResponse(
        var coinCount: Int,//积分
        var level: Int,//等级
        var rank: Int,// 排名
        var userId: Int,
        var username: String) : Parcelable


