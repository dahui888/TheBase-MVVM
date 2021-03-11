package com.theone.demo.ui.fragment

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.adapters.CompoundButtonBindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qmuiteam.qmui.widget.QMUIFloatLayout
import com.theone.demo.R
import com.theone.demo.app.widge.TheSearchView
import com.theone.demo.data.bindadapter.CustomBindAdapter
import com.theone.demo.ui.adapter.SearchAdapter
import com.theone.demo.viewmodel.HotSearchViewModel
import com.theone.demo.viewmodel.MineRequestViewModel
import com.theone.demo.viewmodel.SearchViewModel
import com.theone.mvvm.base.ext.getView
import com.theone.mvvm.base.ext.match_wrap
import com.theone.mvvm.base.ext.util.getColor
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment


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
 * @date 2021/3/11 0011
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class SearchFragment : BaseRecyclerPagerFragment<String, SearchViewModel>(), View.OnClickListener,
    TheSearchView.OnTextChangedListener {

    private val mHotVm: HotSearchViewModel by viewModels()

    private lateinit var mFloatLayout: QMUIFloatLayout

    override fun createAdapter(): BaseQuickAdapter<String, *> = SearchAdapter()

    override fun getItemSpace(): Int = 15

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            val back = addLeftBackImageButton()
            val right = addRightTextButton("", R.id.topbar_right_text)
            back.setOnClickListener {
                finish()
            }
            val searchView = TheSearchView(mActivity, true)
            searchView.setOnTextChangedListener(this@SearchFragment)
            val params = searchView.layoutParams as RelativeLayout.LayoutParams
            params.run {
                addRule(RelativeLayout.RIGHT_OF, back.id)
                addRule(RelativeLayout.LEFT_OF, right.id)
            }
            setCenterView(searchView)
        }
    }

    override fun onLazyInit() {
        super.onLazyInit()
        mHotVm.requestServer()
    }

    override fun initAdapter() {
        super.initAdapter()
        val hotView = getView(R.layout.custom_search_hot_layout)
        mFloatLayout = hotView.findViewById(R.id.float_layout)
        hotView.findViewById<TextView>(R.id.clear).setOnClickListener(this)
        mAdapter.addHeaderView(hotView)
    }

    override fun createObserver() {
        super.createObserver()
        mHotVm.run {
            getResponseLiveData().observe(viewLifecycleOwner, Observer { it ->
                CustomBindAdapter.loadHotSearchData(mFloatLayout, it) {
                    updateKey(it)
                }
            })
        }
    }

    override fun initData() {

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onClick(p0: View?) {
    }

    override fun onChanged(content: String?, isEmpty: Boolean) {

    }

    override fun onSearch(content: String?, isEmpty: Boolean) {
        if (!content.isNullOrEmpty()) {
            updateKey(content)
        }
    }

    /**
     * 更新搜索词
     */
    fun updateKey(keyStr: String) {
        mViewModel.getResponseLiveData().value?.toMutableList()?.let {
            if (it.contains(keyStr)) {
                //当搜索历史中包含该数据时 删除
                it.remove(keyStr)
            } else if (it.size >= 10) {
                //如果集合的size 有10个以上了，删除最后一个
                it.removeAt(it.size - 1)
            }
            //添加新数据到第一条
            it.add(0, keyStr)
            mViewModel.getResponseLiveData().value = it
        }
    }

}