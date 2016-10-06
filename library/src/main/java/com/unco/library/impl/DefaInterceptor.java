package com.unco.library.impl;


import com.unco.library.config.ImageInterceptor;

/**
 * ===============================
 *
 * @Author: 陈振
 * @Email : 15629079270@163.com
 * @Action : 默认拦截器,不做任何拦截
 * @Time : 2016/10/4 22:29
 *
 * ===============================
 */
public class DefaInterceptor implements ImageInterceptor {

    @Override
    public String InterceptorUrl(String oldUrl) {
        return oldUrl;
    }
}  