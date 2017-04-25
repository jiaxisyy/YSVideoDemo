package com.example.shuangxiang.ysvideodemo.api;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.Interceptor.ReadCookiesInterceptor;
import com.example.shuangxiang.ysvideodemo.Interceptor.SaveCookiesInterceptor;
import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FilePath;
import com.example.shuangxiang.ysvideodemo.retrofit.IDownloadRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IHomePictureRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.ILoginRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IMyDeviceListRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IUploadFileRequest;
import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.google.gson.Gson;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
    private static IHomePictureRequest sHomePictureRequest;
    private static IUploadFileRequest sUploadFileRequest;
    private static IMyDeviceListRequest sMyDeviceListRequest;

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

    /**
     * 首页轮播图
     *
     * @return
     */
    public static Observable<String[]> getBannersUrl() {
        sHomePictureRequest = sRetrofit.create(IHomePictureRequest.class);
        return sHomePictureRequest.getBannersUrl();
    }

    /**
     * 首页产品
     *
     * @return
     */
    public static Observable<ProductInfo> getProducts() {
        return sHomePictureRequest.getProducts();
    }

    /**
     * 图片文件上传
     *
     * @param file
     * @return
     */
    public static Observable<FilePath> uploadFile(File file) {
        sUploadFileRequest = sRetrofit.create(IUploadFileRequest.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        return sUploadFileRequest.uploadFile(requestBody);
    }

    /**
     * 反馈问题
     *
     * @param
     * @return
     */
    public static Observable<String> submit(FeedbackInfo info) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), new Gson().toJson(info));
        return sUploadFileRequest.submit(body);
    }

    /**
     * 查询所有的设备
     *
     * @param orgId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static Observable<MyDeviceInfo> getAllDevices(String orgId, int pageNum, int pageSize) {
        sMyDeviceListRequest = sRetrofit.create(IMyDeviceListRequest.class);
        return sMyDeviceListRequest.getAllDevices(orgId, pageNum, pageSize);
    }


}
