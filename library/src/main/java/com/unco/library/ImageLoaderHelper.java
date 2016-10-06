package com.unco.library;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.unco.library.config.ImageLoaderConfig;


/**
 * ===============================
 *
 * @Author: 陈振
 * @Email : 15629079270@163.com
 * @Action : 图片加载框架,插拔式设计,可自由配置具体加载框架
 * @Time : 2016/10/4 22:28
 *
 * ===============================
 */
public class ImageLoaderHelper {
    private static ImageLoaderConfig loderConfig;

    /**
     * 初始化,推荐在Application中调用(仅赋值操作,不会阻塞线程)
     *
     * @param config
     */
    public static void init(ImageLoaderConfig config) {
        if (loderConfig != null) {
            Log.e("ImageLoaderHelper", "不要重复调用init(),这是无效的");
            return;
        }
        if (config == null)
            throw new IllegalArgumentException("ImageHelper初始化出错: ImageLoderConfig不能为null");
        if (config.getLoderClient() == null)
            throw new IllegalArgumentException("ImageHelper初始化出错: ImageLoderClient不能为null");
        loderConfig = config;
    }

    /**
     * 加载图片
     * 占位图和错误图为统一配置的图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void load(Context context, ImageView imageView, String url) {
        String newUrl = loderConfig.getInterceptor().InterceptorUrl(url);//url拦截变换
        loderConfig.getLoderClient().loadImage(context, imageView, newUrl, loderConfig.getPlacePicRes(),
                loderConfig.getErrorPicRes());

    }

    /**
     * 加载图片,过程中展示缩略图
     * 占位图和错误图为统一配置的图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param thumbnail 缩略图栈原图的比例:float类型,0到1
     */
    public static void load(Context context, ImageView imageView, String url, float thumbnail) {
        String newUrl = loderConfig.getInterceptor().InterceptorUrl(url);//url拦截变换
        loderConfig.getLoderClient().loadImage(context, imageView, newUrl, loderConfig.getPlacePicRes(),
                loderConfig.getErrorPicRes(), thumbnail);

    }

}  