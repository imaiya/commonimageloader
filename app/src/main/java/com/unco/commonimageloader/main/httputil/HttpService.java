package com.unco.commonimageloader.main.httputil;


import com.unco.commonimageloader.main.GirlBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/8/10 15:48
 * @Action :请求接口
 *
 * =================中康================
 */
public interface HttpService {

    @GET("api/data/{type}/{count}/{page}")
    Observable<HttpResult<List<GirlBean>>> getGirls(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
}  