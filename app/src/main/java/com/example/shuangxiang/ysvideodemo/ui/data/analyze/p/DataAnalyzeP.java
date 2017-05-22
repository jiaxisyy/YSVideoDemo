package com.example.shuangxiang.ysvideodemo.ui.data.analyze.p;

import android.content.Context;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.TimeUtil;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.StatisticsInfo;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.TableIdInfo;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.m.DataAnalyzeM;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.m.IDataAnalyzeM;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.v.IDataAnalyzeV;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public class DataAnalyzeP implements IDataAnalyzeP {
    private IDataAnalyzeM mIDataAnalyzeM;
    private IDataAnalyzeV mIDataAnalyzeV;
    private String mTableId;
    private Context mContext;


    public DataAnalyzeP(IDataAnalyzeV IDataAnalyzeV, Context context) {
        mIDataAnalyzeM = new DataAnalyzeM(this);
        mIDataAnalyzeV = IDataAnalyzeV;
        mContext = context;

    }

    @Override
    public void getTableIdSucceed(TableIdInfo[] tableIdInfo) {
        int length = tableIdInfo.length;
        for (int i = 0; i < length; i++) {
            if (tableIdInfo[i].getName().equals("数据表")) {
                mTableId = tableIdInfo[i].getId();
                Log.d("TEST", "DaaAnalyzeP->id=" + mTableId);
            }
        }





    }

    @Override
    public void getTableId(String url) {
        mIDataAnalyzeM.getTableId(url);
    }

    @Override
    public void getStatistics(String timeType,String defaultAddress) {
        String deviceid = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_ID);
//        String datatemplateid = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_DATATEMPLATEID);


        Long startTime = TimeUtil.getStartTime();
        long weekStartTime = TimeUtil.getTimesWeekmorning();
        long timesMonthmorning = TimeUtil.getTimesMonthmorning();
        long currentQuarterStartTime = TimeUtil.getCurrentQuarterStartTime();
        long currentYearStartTime = TimeUtil.getCurrentYearStartTime();

        Log.d("TEST", "DaaAnalyzeP->startTime=" + startTime);
        Log.d("TEST", "DaaAnalyzeP->weekStartTime=" + weekStartTime);
        Log.d("TEST", "DaaAnalyzeP->timesMonthmorning=" + timesMonthmorning);
        Log.d("TEST", "DaaAnalyzeP->currentQuarterStartTime=" + currentQuarterStartTime);
        Log.d("TEST", "DaaAnalyzeP->currentYearStartTime=" + currentYearStartTime);


        String statisticsUrl = Constants.Define
                .BASE_URL + "devices/" + deviceid + "/elementTables/" + mTableId + "/stats?statFields=" + defaultAddress + "&fromDate=1465797600000&toDate=1465904800000&statDateInterval=" + timeType;




    }

    @Override
    public void getStatisticsSucceed(StatisticsInfo statisticsInfo) {

    }
}
