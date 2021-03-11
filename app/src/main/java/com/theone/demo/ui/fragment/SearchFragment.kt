package com.theone.demo.ui.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.adapters.CompoundButtonBindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton
import com.qmuiteam.qmui.widget.QMUIFloatLayout
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.theone.demo.R
import com.theone.demo.app.ext.toJson
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.app.widge.TheSearchView
import com.theone.demo.data.bindadapter.CustomBindAdapter
import com.theone.demo.ui.adapter.SearchAdapter
import com.theone.demo.viewmodel.HotSearchViewModel
import com.theone.demo.viewmodel.MineRequestViewModel
import com.theone.demo.viewmodel.SearchViewModel
import com.theone.mvvm.base.ext.*
import com.theone.mvvm.base.ext.net.loadListData
import com.theone.mvvm.base.ext.net.loadListError
import com.theone.mvvm.base.ext.qmui.showFailDialog
import com.theone.mvvm.base.ext.util.getColor
import com.theone.mvvm.base.ext.util.getDrawable
import com.theone.mvvm.base.ext.util.logE
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
    TheSearchView.OnTextChangedListener, QMUIDialogAction.ActionListener, OnItemChildClickListener {

    private val mHotVm: HotSearchViewModel by viewModels()

    private lateinit var mSearchView: TheSearchView
    private lateinit var mSearchBtn: Button
    private lateinit var mFloatLayout: QMUIFloatLayout
    private lateinit var mHistory: View

    override fun createAdapter(): BaseQuickAdapter<String, *> = SearchAdapter()

    override fun getItemSpace(): Int = 15

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            addLeftBackImageButton().apply { setOnClickListener(this@SearchFragment) }
            mSearchBtn = addRightTextButton(R.string.search, R.id.topbar_search).apply { setOnClickListener(this@SearchFragment) }
            mSearchView = TheSearchView(mActivity, true)
            mSearchView.setOnTextChangedListener(this@SearchFragment)
            mSearchView.searchEditText.setHint(R.string.search_hint)
            val params = mSearchView.layoutParams as RelativeLayout.LayoutParams
            params.run {
                addRule(RelativeLayout.RIGHT_OF, R.id.qmui_topbar_item_left_back)
                addRule(RelativeLayout.LEFT_OF, R.id.topbar_search)
            }
            setCenterView(mSearchView)
        }
        mHotVm.requestServer()
    }

    override fun onLazyInit() {
        mViewModel.requestNewData()
        mHotVm.requestServer()
    }

    override fun initAdapter() {
        super.initAdapter()
        mAdapter.addChildClickViewIds(R.id.history_delete)
        mAdapter.setOnItemChildClickListener(this)
        val hotView = getView(R.layout.custom_search_hot_layout)
        mFloatLayout = hotView.findViewById(R.id.float_layout)
        mHistory = hotView.findViewById(R.id.history_layout)
        hotView.findViewById<TextView>(R.id.clear).apply {
            setOnClickListener(this@SearchFragment)
        }
        mAdapter.addHeaderView(hotView)
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observe(viewLifecycleOwner, Observer {
                mAdapter.setNewInstance(it.toMutableList())
                mAdapter.loadMoreModule.loadMoreEnd(true)
                if (it.isEmpty()) goneViews(mHistory) else showViews(mHistory)
                CacheUtil.setSearchHistoryData(it.toJson())
                showContentPage()
            })
        }
        mHotVm.run {
            getResponseLiveData().observe(viewLifecycleOwner, Observer { it ->
                CustomBindAdapter.loadHotSearchData(mFloatLayout, it) {
                    updateKey(it)
                }
            })
            getFinallyLiveData().observe(viewLifecycleOwner, Observer {
                mHotVm.isFirst.set(false)
            })
        }
    }

    override fun initData() {

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val key = adapter.getItem(position) as String
        updateKey(key)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        mAdapter.data.removeAt(position)
        mViewModel.getResponseLiveData().value = mAdapter.data
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.clear -> showClearHistoryDialog()
            R.id.qmui_topbar_item_left_back -> finish()
            R.id.topbar_search -> onSearch(mSearchView.searchEditText.text.toString(),true)
        }
    }

    override fun onChanged(content: String?, isEmpty: Boolean) {
        mSearchBtn.isEnabled = !isEmpty
    }

    override fun onSearch(content: String?, isEmpty: Boolean) {
        if (!content.isNullOrEmpty()) {
            updateKey(content)
        }
    }

    /**
     * 更新搜索词
     */
    private fun updateKey(keyStr: String) {
        startFragment(SearchResultFragment.newInstance(keyStr))
        mAdapter.data.let {
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

    private fun showClearHistoryDialog() {
        QMUIDialog.MessageDialogBuilder(context)
            .setTitle("提示")
            .setMessage("是否清除所有搜索记录？")
            .addAction("取消", this)
            .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEGATIVE, this)
            .show()
    }

    override fun onClick(dialog: QMUIDialog?, index: Int) {
        dialog?.dismiss()
        if (index > 0) {
            mViewModel.getResponseLiveData().value = mutableListOf()
        }
    }


}