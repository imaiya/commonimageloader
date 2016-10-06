package com.unco.commonimageloader;

import android.app.Application;

import com.unco.library.ImageLoaderHelper;
import com.unco.library.config.ImageLoaderConfig;
import com.unco.library.impl.DefaInterceptor;

/**
 * =========================================
 *
 * @User: Doraemon
 * @Email:15629079270@163.com
 * @CreateTime: 2016-10-06 15:23
 * @PackageName: com.unco.commonimageloader
 * @ProjectName: CommonImageLoader
 * @Do What:
 * @ClassName: IApplication
 *
 * ==========================================
 */
public class IApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfig loaderConfig = new ImageLoaderConfig.Builder()
                .client(new GlideClient())//设置加载器
                .placePicRes(R.mipmap.placeholder)//占位图
                .errorPicRes(R.mipmap.placeholder)//错误图
                .interceptor(new DefaInterceptor())//设置拦截器
                .build();
        ImageLoaderHelper.init(loaderConfig);//初始化CommonImageLoader
    }
}  