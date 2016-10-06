package com.unco.commonimageloader.main.httputil;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/8/10 15:59
 * @Action :统一格式的json实体
 *
 * =================中康================
 */
public class HttpResult<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

}  