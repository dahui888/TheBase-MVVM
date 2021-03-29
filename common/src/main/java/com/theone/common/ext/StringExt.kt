package com.theone.common.ext

import android.graphics.Typeface
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.*
import java.util.regex.Pattern

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
 * @date 2021-03-26 13:38
 * @describe String相关扩展函数
 * @email 625805189@qq.com
 * @remark
 */

enum class SpanType {
    ForegroundColorSpan, //前景色
    BackgroundColorSpan, //背景色
    StrikeThroughSpan, // 删除线
    UnderlineSpan, // 下划线
    SuperscriptSpan, // 上标
    SubscriptSpan, // 下标
    RelativeSizeSpan, // 字号
    BOLD, // 粗体
    ITALIC, // 斜体
    BOLD_ITALIC // 粗体加斜体
}

/**
 * 获取SpannableString
 * @param target      目标处理的文本
 * @param color       颜色
 * @param proportion  字体比例(放大或者缩小)
 * @param types       Span类型-对同一个布标进行多种样式设置
 */
fun String.getSpannableString(
    target: String,
    color: Int = -1,
    proportion: Float = 1.3f,
    vararg types: SpanType
): SpannableString {
    return SpannableString(this).apply {
        val index = getTargetIndexArray(target)
        for (type in types) {
            val characterStyle = when (type) {
                SpanType.RelativeSizeSpan -> RelativeSizeSpan(proportion)
                SpanType.BackgroundColorSpan -> BackgroundColorSpan(color)
                SpanType.StrikeThroughSpan -> StrikethroughSpan()
                SpanType.UnderlineSpan -> UnderlineSpan()
                SpanType.SuperscriptSpan -> SuperscriptSpan()
                SpanType.SubscriptSpan -> SubscriptSpan()
                SpanType.BOLD -> StyleSpan(Typeface.BOLD)
                SpanType.ITALIC -> StyleSpan(Typeface.ITALIC)
                SpanType.BOLD_ITALIC -> StyleSpan(Typeface.BOLD_ITALIC)
                else ->
                    ForegroundColorSpan(color)
            }
            setSpan(characterStyle, index[0], index[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
}

/**
 * 获取目标文字在整段中的位置
 * @param targetString 目标文本
 */
fun String.getTargetIndexArray(
    targetString: String
): IntArray {
    val index = IntArray(2)
    if (contains(targetString)) {
        val start = indexOf(targetString)
        val end = start + targetString.length
        index[0] = start
        index[1] = end
        return index
    } else {
        throw Exception("======================== \n 目标文本 \n $targetString \n 不存在于 \n $this \n========================")
    }
}

/**
 * 获取String中的数字
 */
fun String.getNumbers(): String = matchResult(PAT_NUMBERS).trim()

/**
 * 获取String中的文字
 */
fun String.getChars(): String = matchResult(PAT_CHARS).trim()

/**
 * 匹配结果
 * @param reg 匹配规则
 */
fun String.matchResult(reg: String): String {
    if (isNullOrEmpty()) return this
    val p = Pattern.compile(reg)
    val sb = StringBuilder()
    val m = p.matcher(this)
    while (m.find()) {
        for (index in 0..m.groupCount()) {
            sb.append(m.group())
        }
    }
    return sb.toString()
}


/**
 * 验证手机号码的合法性
 */
fun String.isPhoneNumber(): Boolean {
    if (TextUtils.isEmpty(this)) {
        return false
    }
    if (11 != length) {
        return false
    }
    return match(PAT_XN) || match(PAT_LT) || match(
        PAT_DX
    ) || match(PAT_XN)
}

private fun String.match(pat: String): Boolean {
    return Pattern.compile(pat).matcher(this).matches()
}

/**
 * 隐藏手机号码中间的四位数
 */
fun String.hidePhoneNumber(): String {
    return replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
}

/**
 * 加密身份证中间的
 */
fun String.hideIdCardNumber(): String {
    return replace("(\\d{4})\\d{10}(\\d{4})".toRegex(), "$1****$2")
}

/**
 * 移动号段正则表达式
 */
private const val PAT_YD = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$"

/**
 * 联通号段正则表达式
 */
private const val PAT_LT = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$"

/**
 * 电信号段正则表达式
 */
private const val PAT_DX = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$"

/**
 * 虚拟运营商正则表达式
 */
private const val PAT_XN = "^((170))\\d{8}|(1718)|(1719)\\d{7}$"

/**
 * 文字
 */
private const val PAT_CHARS = "[\\u4e00-\\u9fa5]"

/**
 * 文字
 */
private const val PAT_NUMBERS = "\"[0-9]\""


fun String.toHtml(flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, flag)
    } else {
        Html.fromHtml(this)
    }
}
