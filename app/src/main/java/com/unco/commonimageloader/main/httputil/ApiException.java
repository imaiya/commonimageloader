package com.unco.commonimageloader.main.httputil;

/**
 * ==============中康=================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Action : 自定义异常,在code!=0时抛出,在Eroor中做统一处理
 * @Time : 2016/8/11 15:02
 * ==============中康=================
 */
public class ApiException extends RuntimeException {

    public ApiException() {
        super("API异常");
    }
}

