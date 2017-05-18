package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public interface ISettingRequest {
    /**
     * 获取参数设置的标题
     *
     * @param url
     * @return
     */
    @GET
    Observable<ParameterInfo[]> getParameterTitle(@Url String url);

    /**
     *
     * 获取参数设置的值
     * @param url
     * @return
     */
    @GET
    Observable<String> getParameterValue(@Url String url);


}
