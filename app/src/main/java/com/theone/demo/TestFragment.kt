package com.theone.demo

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout
import com.theone.demo.databinding.FragmentTestBinding
import com.theone.mvvm.base.fragment.BaseVmDbFragment
import com.theone.mvvm.base.fragment.BaseVmFragment
import com.theone.mvvm.ext.setTitleWithBackBtn
import kotlinx.android.synthetic.main.fragment_test.*


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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TestFragment : BaseVmDbFragment<TestViewModel, FragmentTestBinding>(),
    QMUIPullRefreshLayout.OnPullListener {

    override fun getLayoutId(): Int = R.layout.fragment_test
    private val mAdapter: TestAdapter by lazy { TestAdapter() }
    private var mPage: Int = 1

    override fun initView(savedInstanceState: Bundle?) {
        mTopBar.setTitleWithBackBtn(R.string.app_name, this)
        recyclerView.run {
            layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
        pullRefreshLayout.run {
            setOnPullListener(this@TestFragment)
        }
    }

    override fun onLazyInit() {
        if (mPage == 1)
            mAdapter.setNewInstance(getData())
        else
            mAdapter.addData(getData())
    }

    private fun getData(): MutableList<TestBean> {
        val data = mutableListOf<TestBean>()
        for (index in 1..50) {
            data.add(TestBean("$mPage - $index"))
        }
        return data
    }

    override fun createObserver() {
        mViewModel.test.set(getString(R.string.app_test))
    }

    override fun initData() {
        mDataBind.vm = mViewModel
    }

    override fun onMoveRefreshView(offset: Int) {
    }

    override fun onRefresh() {
        mPage = 1
        onLazyInit()
    }

    override fun onMoveTarget(offset: Int) {
    }

}