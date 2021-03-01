package com.theone.demo.ui.fragment

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.tab.QMUITab
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.theone.demo.R
import com.theone.mvvm.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_index.*


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
 * @date 2021/3/1 0001
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class IndexFragment : BaseFragment() {

    lateinit var mFragments:MutableList<Fragment>
    lateinit var mPagerAdapter: PagerAdapter

    override fun showTitleBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.fragment_index

    override fun initView(rootView: View) {
        initViewPager()
        initSegment()
    }

    private fun initViewPager(){
        mFragments = mutableListOf()
        mFragments.add(SampleFragment())
        mPagerAdapter = createAdapter()
        mQMUIViewPager.run {
            adapter = mPagerAdapter
        }
    }

    private fun initSegment(){
        val builder = mTabSegment.tabBuilder()
        builder.run {
            setSelectedIconScale(1.2f)
            setTextSize(dp2px(13),dp2px(15))
            setDynamicChangeIconColor(false)
        }
        val home = builder.createTab("首页",R.drawable.ic_home_normal,R.drawable.ic_home_selected)
        mTabSegment.addTab(home)
        mTabSegment.setupWithViewPager(mQMUIViewPager,false)
    }


    private fun dp2px(dp:Int):Int = QMUIDisplayHelper.dp2px(mActivity,dp)

    private fun QMUITabBuilder.createTab(text:CharSequence, normal:Int, select:Int):QMUITab{
        setText(text)
        setNormalDrawable(getDrawable(normal))
        setSelectedDrawable(getDrawable(select))
        return build(context)
    }

    private fun getDrawable(res:Int):Drawable?{
        return ContextCompat.getDrawable(mActivity,res)
    }


    private fun createAdapter(): PagerAdapter {
       return object : PagerAdapter() {

           var mChildCount:Int = 0

           override fun instantiateItem(container: ViewGroup, position: Int): Any {
               return mFragments[position]
           }

           override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
               container.removeView(`object`as View)
           }

           override fun isViewFromObject(view: View, `object`: Any): Boolean {
               return view == `object`
           }

           override fun getCount(): Int  = mFragments.size

           override fun getItemPosition(`object`: Any): Int {
               return if (mChildCount == 0) {
                   POSITION_NONE
               } else super.getItemPosition(`object`)
           }

           override fun notifyDataSetChanged() {
               mChildCount = count
               super.notifyDataSetChanged()
           }
        }
    }

    override fun onLazyInit() {

    }

}