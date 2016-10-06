package com.unco.commonimageloader.main.httputil;


import com.unco.commonimageloader.main.GirlBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/8/10 15:46
 * @Action :网络层
 *
 * =================中康================
 */
public class HttpManager {
    public static final String BASE_URL = "http://gank.io/";
    private static final int DEFAULT_TIMEOUT = 5;//请求超时时间/秒

    private Retrofit retrofit;
    private HttpService mService;

    //构造方法私有
    private HttpManager() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置日志
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mService = retrofit.create(HttpService.class);
    }

    private static class SingletonHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    //获取单例
    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
/*<=====================================================================================================================>*/

//    /**
//     * 网络层方法
//     *
//     * @param subscriber
//     * @param pageNum
//     * @param token
//     * @param pageSize
//     * @param hospitalId
//     */
//    public void getDoc(int pageNum, String token, int pageSize, int hospitalId, Subscriber<ItemBean> subscriber) {
//        mService.getDoc(pageNum, token, pageSize, hospitalId)
//                .map(new HttpResultFunc<ItemBean>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    /**
     * 获取girl列表
     *
     * @param type
     * @param count
     * @param page
     * @param subscriber
     */
    public void getGirls(String type, int count, int page, Subscriber<List<GirlBean>> subscriber) {
        mService.getGirls(type, count, page)
                .map(new HttpResultFunc<List<GirlBean>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    

/*<=====================================================================================================================>*/

    /**
     * 统一的错误判断,如果code不是成功,则抛出自定义异常,可以在subscribe的onError()处理
     * RxJava中的map操作
     *
     * @param <T>
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.isError()) {
                throw new ApiException();
            }
            return httpResult.getResults();
        }
    }
}  
