package com.example.shuangxiang.ysvideodemo.api;

import com.example.shuangxiang.ysvideodemo.login.bean.LoginInfo;
import com.example.shuangxiang.ysvideodemo.login.retrofit.ILoginRequest;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by shuang.xiang on 2017/4/10.
 */

public class ApiManager {

    private static final String BASEURL = "http://10.199.198.55:58010/userconsle/";
    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    private static final ILoginRequest loginRequest = retrofit.create(ILoginRequest.class);

    /**
     * 获取登录信息
     *
     * @param username
     * @param password
     * @return
     */
    public static Observable<String> getLoginRequest(String username, String password) {
        return loginRequest.loginRequest(username, password);

    }


}
