package com.example.shuangxiang.ysvideodemo.ui.data.analyze.p;

import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.StatisticsInfo;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.TableIdInfo;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public interface IDataAnalyzeP {
    void getTableIdSucceed(TableIdInfo[] tableIdInfo);

    void getTableId(String url);
    void getStatistics(String timeType,String defaultAddress);
    void getStatisticsSucceed(StatisticsInfo statisticsInfo);
}
