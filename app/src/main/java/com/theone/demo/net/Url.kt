package com.theone.demo.net

import rxhttp.wrapper.annotation.DefaultDomain


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
 * @date 2021/2/18 0018
 * @describe 接口
 * @email 625805189@qq.com
 * @remark
 */
object Url {

    @JvmField
    @DefaultDomain //设置为默认域名
    var baseUrl = "http://apis.juhe.cn/cxdq/"

    /**
     * 请求示例：http://apis.juhe.cn/cxdq/brand?key=xxxx&first_letter=A
     * 接口备注：返回车辆品牌所有列表，或更具中文拼音首字母查询品牌列表
     */
    const val BRAND = "brand"

    /**
     *  请求示例：http://apis.juhe.cn/cxdq/series?key=xxxx&brandid=1&levelid=5
     *  接口备注：根据车辆品牌ID查询旗下车系列表
     */
    const val SERIES = "series"

    /**
     * 请求示例：http://apis.juhe.cn/cxdq/models?key=xxxxx&series_id=2
     * 接口备注：根据车系id查询旗下车型列表
     */
    const val MODELS = "models"

}