package com.theone.demo.widge.banner;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.theone.demo.R;
import com.theone.demo.data.model.bean.BannerResponse;
import com.theone.mvvm.util.glide.GlideUtil;
import com.zhpan.bannerview.holder.ViewHolder;


public class BannerViewHolder implements ViewHolder<BannerResponse> {

    @Override
    public int getLayoutId() {
        return R.layout.item_banner;
    }

    @Override
    public void onBind(View itemView, BannerResponse data, int position, int size) {
        AppCompatImageView imageView = itemView.findViewById(R.id.banner_image);
        GlideUtil.load(itemView.getContext(), data.getImagePath(),imageView);
        AppCompatTextView title = itemView.findViewById(R.id.banner_title);
        title.setText(data.getTitle());
    }
}