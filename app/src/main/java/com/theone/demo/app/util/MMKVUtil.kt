package com.theone.demo.app.util

import com.tencent.mmkv.MMKV


//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021/3/5 0005
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
object MMKVUtil {

    private fun getMMKV(): MMKV = MMKV.defaultMMKV()

    fun putBoolean(key: String, value: Boolean) {
        getMMKV().encode(key, value)
    }

    fun getBoolean(key: String, default: Boolean): Boolean =
        getMMKV().decodeBool(key, default)

    fun getBoolean(key: String): Boolean =
        getBoolean(key, false)

    fun putString(key: String, value: String?) {
        getMMKV().encode(key, value)
    }

    fun getString(key: String, default: String?): String? =
        getMMKV().decodeString(key, default)

    fun getString(key: String): String? =
        getString(key, null)

}

