package com.example.shuangxiang.ysvideodemo.api;

import android.content.Context;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.download.FileResponseBody;
import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FilePath;
import com.example.shuangxiang.ysvideodemo.manager.CookieManger;
import com.example.shuangxiang.ysvideodemo.retrofit.IDownloadRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IHomePictureRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.ILoginRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IMyDeviceListRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IUploadFileRequest;
import com.example.shuangxiang.ysvideodemo.retrofit.IWarningListRequest;
import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by shuang.xiang on 2017/4/10.
 */

public class ApiManager {


    private Context mContext;
    private IDownloadRequest sDownRequest;
    private static Retrofit sRetrofit;
    private static IHomePictureRequest sHomePictureRequest;
    private IUploadFileRequest sUploadFileRequest;
    private IMyDeviceListRequest sMyDeviceListRequest;
    private IWarningListRequest sWarningListRequest;
    private OkHttpClient mSOkHttpClient;

    public ApiManager(Context context) {
        mContext = context;
    }

    public ApiManager() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile ApiManager instance;

    //定义一个共有的静态方法，返回该类型实例
    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
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
    public Observable<String> getLoginRequest(String username, String password) {
        mSOkHttpClient = new OkHttpClient.Builder()

                .cookieJar(new CookieManger(mContext))
//                .addInterceptor(new SaveCookiesInterceptor(mContext))
//                .addInterceptor(new ReadCookiesInterceptor(mContext))
                .build();
        //增加返回值为String的支持
        sRetrofit = new Retrofit.Builder().baseUrl(BASEURL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mSOkHttpClient)
                .build();

        ILoginRequest loginRequest = sRetrofit.create(ILoginRequest.class);

        return loginRequest.loginRequest(username, password);
    }

    /**
     * 获取app信息
     *
     * @return
     */
    public Observable<AppMessage> getAppMessage() {
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
    public Observable<ProductInfo> getProducts() {
        return sHomePictureRequest.getProducts();
    }

    /**
     * 图片文件上传
     *
     * @param file
     * @return
     */
    public Observable<FilePath> uploadFile(File file) {
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
    public Observable<String> submit(FeedbackInfo info) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), new Gson().toJson(info));
        return sUploadFileRequest.submit(body);
    }

    /**
     * 查询所有的设备
     *
     * @return
     */
    public Observable<MyDeviceInfo> getAllDevices(String orgId, String name, int pageNum, int
            pageSize) {
        sMyDeviceListRequest = sRetrofit.create(IMyDeviceListRequest.class);
        return sMyDeviceListRequest.getAllDevices(orgId, name, pageNum, pageSize);
    }

    /**
     * 查询所有的设备
     *
     * @return
     */
    public Observable<WarningInfo> getRecord(int pageNum, int pageSize, String fromDate,
                                             String toDate) {
        sWarningListRequest = sRetrofit.create(IWarningListRequest.class);
        return sWarningListRequest.getRecord(pageNum, pageSize, fromDate, toDate);
    }

    public Observable<ResponseBody> down(String url) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.Define.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookieManger(mContext))
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());//对结果重新处理
                        return originalResponse
                                .newBuilder()
                                .body(new FileResponseBody(originalResponse))//将自定义的ResposeBody设置给它
                                .build();
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        IDownloadRequest iDownloadRequest = retrofit.create(IDownloadRequest.class);
        Log.d("TEST", "ApiManager->down");
        return iDownloadRequest.down();
    }
}
