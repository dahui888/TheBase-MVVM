package com.theone.demo.ui.fragment.sample

import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.theone.common.ext.dp2px
import com.theone.demo.R
import com.theone.demo.databinding.FragmentSampleGroupListViewBinding
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.fragment.BaseCoreFragment
import com.theone.mvvm.ext.qmui.*


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
 * @date 2021/2/25 0025
 * @describe QMUIGroupListView 的一些使用示例，具体请看QMUI文档（文档似乎也没多少内容）
 * @email 625805189@qq.com
 * @remark
 */
class GroupListViewFragment : BaseCoreFragment<BaseViewModel,FragmentSampleGroupListViewBinding>(), CompoundButton.OnCheckedChangeListener,View.OnClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_sample_group_list_view

    override fun initView(rootView: View) {
        getTopBar()?.setTitleWithBackBtn(TAG,this)
        mBinding.groupListView.run {
            val normal = createItem("普通的Item")
            val detail = createItem("带有图标和详情的Item", "这是详情",R.drawable.svg_mine_project_address)
            val switch = createSwitchItem("带有Switch的Item", "这是详情", listener = this@GroupListViewFragment)

            val logo = ImageView(mActivity)
            val size = dp2px(35)
            logo.layoutParams = ViewGroup.LayoutParams(size,size)
            logo.setImageResource(R.drawable.svg_heart)
            val custom = createCustomViewItem("带有自定义View的Item","设置自定义Item的详情",view = logo)

            val item = createItem("这个Item将被标记New")
            val item2 = createItem("这个Item将被标记红点")

            showTips(item, isDot = false)
            showTips(item2,showLeft = true)

            addToGroup(normal,detail,title = "这是标题",description = "",listener = this@GroupListViewFragment)
            addToGroup(switch,custom,listener = this@GroupListViewFragment)
            addToGroup(item,item2,title = "",description = "这是描述",listener = this@GroupListViewFragment)

        }
    }

    override fun onLazyInit() {

    }

    override fun createObserver() {

    }

    override fun initData() {

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        showMsgTipsDialog("$isChecked")
    }

    override fun onClick(v: View?) {
        v as QMUICommonListItemView
        showInfoTipsDialog("点击了 ${v.text.toString()}")
    }

}