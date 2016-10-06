package com.unco.commonimageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.unco.library.config.ImageLoaderClient;


/**
 * ===============================
 *
 * @Author: 陈振
 * @Email : 15629079270@163.com
 * @Action : 使用glide进行图片加载的实例
 * @Time : 2016/10/4 22:29
 *
 * ===============================
 */
public class GlideClient implements ImageLoaderClient {

    @Override
    public void loadImage(Context context, ImageView view, String url, int placePicRes, int errorPicRes) {
        Glide.with(context).load(url).placeholder(placePicRes).error(errorPicRes).into(view);
    }

    @Override
    public void loadImage(Context context, ImageView view, String url, int placePicRes, int errorPicRes, float
            thumbnail) {
        Glide.with(context).load(url).placeholder(placePicRes).error(errorPicRes).thumbnail(thumbnail).into(view);
    }
}  