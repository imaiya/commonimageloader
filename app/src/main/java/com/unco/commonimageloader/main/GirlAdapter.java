package com.unco.commonimageloader.main;

import android.widget.ImageView;

import com.unco.commonimageloader.R;
import com.unco.commonimageloader.baseadapter.RecyclerAdapter;
import com.unco.commonimageloader.baseadapter.viewholders.RecyclerViewHolder;
import com.unco.library.ImageLoaderHelper;

import java.util.List;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/9/20 15:03
 * @Action :
 *
 * =================中康================
 */
public class GirlAdapter extends RecyclerAdapter<GirlBean> {

    public GirlAdapter(int layoutId, List<GirlBean> datas) {
        super(layoutId, datas);
    }

    @Override
    protected void onBindData(RecyclerViewHolder viewHolder, int position, GirlBean item) {
        ImageView imageView = viewHolder.findViewById(R.id.my_image_view);
        ImageLoaderHelper.load(viewHolder.getContext(), imageView, item.getUrl());
        viewHolder.setText(R.id.tv, item.getWho());
    }
}  