package com.theone.demo.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//  "id": "1",
//  "name": "奥迪A6L新能源",
//  "brandid": "1",
//  "levelid": "5",
//  "levelname": "中大型车",
//  "sname": "一汽-大众奥迪"


@Parcelize
data class Series(var id: String = "",
                  var name: String="",
                  var levelid: Int= 0,
                  var levelname: String = "",
                  var sname: String="") : Parcelable {
}
