package com.unco.library.config;

import android.content.Context;
import android.widget.ImageView;

/**
 * ===============================
 *
 * @Author: 陈振
 * @Email : 15629079270@163.com
 * @Action : 图片加载接口,定义图片加载的方法,如果需要更换图片加载的框架,实现该接口即可
 * @Time : 2016/10/4 22:29
 *
 * ===============================
 */
public interface ImageLoaderClient {
    /**
     * 正常加载图片
     *
     * @param context
     * @param view
     * @param url
     */
    void loadImage(Context context, ImageView view, String url, int placePicRes, int errorPicRes);

    /**
     * 先展示缩略图,加载完成展示原图
     *
     * @param context
     * @param view
     * @param url
     * @param placePicRes
     * @param errorPicRes
     * @param thumbnail 
     */
    void loadImage(Context context, ImageView view, String url, int placePicRes, int errorPicRes, float thumbnail);
}  