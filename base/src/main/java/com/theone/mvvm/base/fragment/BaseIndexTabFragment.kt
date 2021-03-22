package com.theone.mvvm.base.fragment

import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.mvvm.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.databinding.BaseFragmentIndexBinding
import kotlinx.android.synthetic.main.base_fragment_index.*
import net.lucode.hackware.magicindicator.MagicIndicator


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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseIndexTabFragment<VM : BaseViewModel> : BaseTabFragment<VM,BaseFragmentIndexBinding>() {

    override fun showTitleBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.base_fragment_index

    override fun getViewPager(): QMUIViewPager = mQMUIViewPager

    override fun getTabSegment(): QMUITabSegment? = mTabSegment

    override fun getMagicIndicator(): MagicIndicator? = null

    override fun initTabSegment() {
        super.initTabSegment()
        mTabSegment.mode = QMUITabSegment.MODE_FIXED
    }

}