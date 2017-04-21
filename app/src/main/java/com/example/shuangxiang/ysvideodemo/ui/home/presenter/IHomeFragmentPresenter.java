package com.example.shuangxiang.ysvideodemo.ui.home.presenter;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/21.
 */

public interface IHomeFragmentPresenter {
    void loadBanner();

    void getBannerResource();

    void requestSucceed(List<String> list);


}
