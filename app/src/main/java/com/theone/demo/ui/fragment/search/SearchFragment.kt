package com.theone.demo.ui.fragment.search

import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.qmuiteam.qmui.widget.QMUIFloatLayout
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.theone.common.ext.getView
import com.theone.demo.R
import com.theone.demo.app.ext.toJson
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.data.bindadapter.CustomBindAdapter
import com.theone.demo.ui.adapter.SearchAdapter
import com.theone.demo.viewmodel.HotSearchViewModel
import com.theone.demo.viewmodel.SearchViewModel
import com.theone.common.ext.goneViews
import com.theone.common.ext.showViews
import com.theone.common.ext.textStringTrim
import com.theone.common.widget.TheSearchView
import com.theone.mvvm.core.base.fragment.BasePagerSwipeRefreshFragment
import com.theone.mvvm.core.databinding.BaseRecyclerPagerFragmentBinding
import com.theone.mvvm.core.ext.showSuccessPage
import com.theone.mvvm.ext.qmui.showMsgDialog


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
class SearchFragment :
    BasePagerSwipeRefreshFragment<String, SearchViewModel, BaseRecyclerPagerFragmentBinding>(),
    View.OnClickListener,
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
            mSearchBtn = addRightTextButton(R.string.search, R.id.topbar_search).apply {
                setOnClickListener(this@SearchFragment)
            }
            mSearchView = TheSearchView(mActivity, true)
            mSearchView.mSearchListener = this@SearchFragment
            mSearchView.mEditText.setHint(R.string.search_hint)
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
            getResponseLiveData().observeInFragment(this@SearchFragment, Observer {
                mAdapter.setNewInstance(it.toMutableList())
                mAdapter.loadMoreModule.loadMoreEnd(true)
                setHistoryData(it)
                showSuccessPage()
            })
        }
        mHotVm.run {
            getResponseLiveData().observeInFragment(this@SearchFragment, Observer { it ->
                CustomBindAdapter.loadHotSearchData(mFloatLayout, it) {
                    updateKey(it)
                }
            })
            getFinallyLiveData().observeInFragment(this@SearchFragment, Observer {
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
        mAdapter.notifyItemRangeChanged(position + mAdapter.headerLayoutCount,mAdapter.data.size + mAdapter.headerLayoutCount)
        setHistoryData()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.clear -> showClearHistoryDialog()
            R.id.qmui_topbar_item_left_back -> finish()
            R.id.topbar_search -> updateKey(mSearchView.mEditText.textStringTrim())
        }
    }

    /**
     * 更新搜索词
     */
    private fun updateKey(keyStr: String) {
        startFragment(
            SearchResultFragment.newInstance(
                keyStr
            )
        )
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
        }
        mAdapter.notifyDataSetChanged()
        setHistoryData()
    }

    private fun showClearHistoryDialog() {
        context?.showMsgDialog("提示","是否清除所有搜索记录?",listener = this,prop = QMUIDialogAction.ACTION_PROP_NEGATIVE)
    }

    override fun onClick(dialog: QMUIDialog?, index: Int) {
        dialog?.dismiss()
        if (index > 0) {
            setHistoryData(arrayListOf())
            mAdapter.setNewInstance(arrayListOf())
        }
    }

    private fun setHistoryData(data: List<String> = mAdapter.data) {
        if (data.isEmpty())
            goneViews(mHistory)
        else
            showViews(mHistory)
        CacheUtil.setSearchHistoryData(data.toJson())
    }

    override fun onSearchViewTextChanged(content: String, empty: Boolean) {
        mSearchBtn.isEnabled = !empty
    }

    override fun onSearchViewClick(content: String, empty: Boolean) {
        if (!empty) {
            updateKey(content)
        }
    }

}