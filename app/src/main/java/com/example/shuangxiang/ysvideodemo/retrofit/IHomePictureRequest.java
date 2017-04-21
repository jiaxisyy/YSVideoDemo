package com.example.shuangxiang.ysvideodemo.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by shuang.xiang on 2017/4/21.
 */

public interface IHomePictureRequest {
    @GET("kawaapp/banners")
    Observable<String[]> getBannersUrl();

}
