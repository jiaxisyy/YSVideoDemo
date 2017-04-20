package com.example.shuangxiang.ysvideodemo.api;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.Interceptor.ReadCookiesInterceptor;
import com.example.shuangxiang.ysvideodemo.Interceptor.SaveCookiesInterceptor;
import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;
import com.example.shuangxiang.ysvideodemo.retrofit.IDownloadRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.ILoginRequest;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by shuang.xiang on 2017/4/10.
 */

public class ApiManager {


    private static Context mContext;
    private static IDownloadRequest sDownRequest;
    private static Retrofit sRetrofit;

    public ApiManager(Context context) {
        mContext = context;
    }
    //内网
//    private static final String BASEURL = "http://10.199.198.55:58010/userconsle/";
    //外网
    private static final String BASEURL = "http://58.250.204.112:58010/userconsle/";

    /**
     * 获取登录信息
     *
     * @param username
     * @param password
     * @return
     */
    public static Observable<String> getLoginRequest(String username, String password) {
        OkHttpClient sOkHttpClient = new OkHttpClient.Builder().addInterceptor(new
                SaveCookiesInterceptor(mContext)).addInterceptor(new ReadCookiesInterceptor(mContext)
        ).build();

        //增加返回值为String的支持
        sRetrofit = new Retrofit.Builder().baseUrl(BASEURL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(sOkHttpClient)
                .build();

        ILoginRequest loginRequest = sRetrofit.create(ILoginRequest.class);

        return loginRequest.loginRequest(username, password);
    }

    /**
     * 获取app信息
     *
     * @return
     */
    public static Observable<AppMessage> getAppMessage() {
        sDownRequest = sRetrofit.create(IDownloadRequest.class);
        return sDownRequest.getAppMessage();
    }
}
