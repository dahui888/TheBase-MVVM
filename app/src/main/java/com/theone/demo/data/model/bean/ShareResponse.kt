package com.theone.demo.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import com.theone.demo.app.net.PagerResponse
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ShareResponse(
    var coinInfo: CoinInfoResponse,
    var shareArticles: PagerResponse<List<ArticleResponse>>
) : Parcelable