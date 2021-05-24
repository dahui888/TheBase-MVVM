package com.theone.mvvm.core.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.common.constant.BundleConstant
import com.theone.common.ext.getValueNonNull
import com.theone.common.ext.logI
import com.theone.mvvm.core.R
import com.theone.mvvm.core.base.fragment.BaseImageSnapFragment
import com.theone.mvvm.core.data.entity.ImagePreviewBean
import com.theone.mvvm.core.data.entity.ImagePreviewEvent
import com.theone.mvvm.core.databinding.BaseRecyclerPagerFragmentBinding
import com.theone.mvvm.core.viewmodel.ImagePreviewViewModel
import com.theone.mvvm.ext.qmui.addLeftCloseImageBtn
import com.theone.mvvm.ext.qmui.setTitleWithBackBtn

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
 * @date 2021-04-25 10:18
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class ImagePreviewFragment:
    BaseImageSnapFragment<ImagePreviewBean, ImagePreviewViewModel, BaseRecyclerPagerFragmentBinding>() {

    companion object{

        fun  newInstance(data:ImagePreviewEvent?): ImagePreviewFragment {
            return ImagePreviewFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BundleConstant.DATA,data)
                }
            }
        }
    }

    private val mData:ImagePreviewEvent by getValueNonNull(BundleConstant.DATA)

    override fun initView(root: View) {
        super.initView(root)
        addLeftCloseImageBtn(R.drawable.mz_comment_titlebar_ic_close_dark)
    }

    override fun onFirstLoading() {
        super.onFirstLoading()
        mData.datas.toString().logI()
        mViewModel.setData(mData.datas)
    }

    override fun onLoadMoreComplete() {
        super.onLoadMoreComplete()
        getRefreshLayout().isEnabled = false
        mAdapter.loadMoreModule.loadMoreEnd(true)
        getRecyclerView().scrollToPosition(mData.position)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

    override fun onScrollChanged(item: ImagePreviewBean, position: Int, total: Int) {
        getTopBar()?.setTitle("${position+1}/$total")
    }

}