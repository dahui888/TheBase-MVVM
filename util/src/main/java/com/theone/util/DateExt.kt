package com.theone.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
 * @date 2021-03-26 15:35
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

/**         格式
 *
 *  yyyy    四位年
 *  yy      两位年
 *  MM      月份  始终两位
 *  M       月份
 *  dd      日期  始终两位
 *  d       日期
 *  HH      24小时制  始终两位
 *  H       24小时制
 *  hh      12小时制  始终两位
 *  h       12小时制
 *  mm      分钟  始终两位
 *  ss      秒  始终两位
 *
 */

/**
 * 年月日 时分秒
 */
const val YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

/**
 * 年月日
 */
const val YYYY_MM_DD = "yyyy-MM-dd";

/**
 * 月日
 */
const val MM_DD = "MM-dd";

/**
 * Date格式化时间
 * @param format 格式
 */
@SuppressLint("SimpleDateFormat")
fun Date.formatString(format: String = YYYY_MM_DD): String {
    return SimpleDateFormat(format).format(this)
}

/**
 * Date转Calendar
 */
fun Date.toCalendar(): Calendar {
    return Calendar.getInstance().apply {
        time = this@toCalendar
    }
}

/**
 * String格式的时间转Long
 * @param format 格式
 */
fun String.formatString(format: String = YYYY_MM_DD): Long? {
   return formatDate(format)?.time
}

/**
 * String格式的时间转Long
 * @param format 格式
 */
@SuppressLint("SimpleDateFormat")
fun String.formatDate(format: String = YYYY_MM_DD): Date? {
    return SimpleDateFormat(format).parse(this)
}