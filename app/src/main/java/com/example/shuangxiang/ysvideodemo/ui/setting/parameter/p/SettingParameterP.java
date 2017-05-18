package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p;

import android.content.Context;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m.ISettingParameterM;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m.SettingParameterM;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v.ISettingParameterV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class SettingParameterP implements ISettingParameterP {
    private ISettingParameterM mISettingParameterM;
    private ISettingParameterV mISettingParameterV;

    private Context mContext;
    private String mMTitleId;
    private String mDatatemplateid;
    private List<String> mFieldNames;
    private List<String> mNames;


    public SettingParameterP(ISettingParameterV ISettingParameterV, Context context) {
        mISettingParameterM = new SettingParameterM(this);
        mISettingParameterV = ISettingParameterV;
        mContext = context;
    }

    @Override
    public void getTitleSucceed(ParameterInfo[] parameterInfo) {
        List<ParameterInfo.ElementsBean> elements = parameterInfo[0].getElements();
        mFieldNames = new ArrayList<>();
        int size = elements.size();
        mNames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String name = elements.get(i).getName();
            String fieldName = elements.get(i).getFieldName();
            mFieldNames.add(fieldName);
            mNames.add(name);
            Log.d("TEST", "name=" + name);
        }
        String valueUrl = Constants.Define.BASE_URL + "dataTemplates/" + mDatatemplateid + "/datas?pageSize=1&showTable=false&deviceId=" + mMTitleId;
        mISettingParameterM.getParameterValue(valueUrl);
    }

    @Override
    public void getTitle(String type) {
        mMTitleId = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_ID);
        mDatatemplateid = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_DATATEMPLATEID);
        String url = Constants.Define.BASE_URL + "devices/" + mMTitleId +
                "/elementCategorys?type="+type;
        Log.d("TEST", "url=" + url);
        mISettingParameterM.getParameterTitle(url);
    }

    @Override
    public void getValueSucceed(String s) {
        Log.d("TEST", "json=" + s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray=jsonObject.getJSONArray("list");
            List<String> values = new ArrayList<>();
            int size = mFieldNames.size();
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                for (int j = 0; j < size; j++) {
                    String value = object.getString(mFieldNames.get(j));
                    values.add(value);
                }

            }
            mISettingParameterV.setRvData(mNames, values);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR",e.getMessage().toString());
        }


    }


}
