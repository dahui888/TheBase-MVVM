package the.one.brand.net

import com.theone.demo.net.Response
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.convert
import java.io.IOException
import java.lang.reflect.Type
import kotlin.jvm.Throws


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
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

@Parser(name = "Response")
open class ResponseParse<T> : AbstractParser<T?> {

    protected constructor() : super()

    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T? {
        val type: Type = ParameterizedTypeImpl[Response::class.java, mType]
        val data: Response<T> = response.convert(type)
        var t = data.getData()
        if (t == null && mType === String::class.java) {
            @Suppress("UNCHECKED_CAST")
            t = data.getMsg() as T
        }
        if (data.isSuccess()) {
            throw ParseException(data.error_code.toString(), data.reason, response)
        }
        return t
    }

}