package com.example.shuangxiang.ysvideodemo.common;

/**
 * Created by shuang.xiang on 2017/4/6.
 * 常量表
 */

public class Constants {

    public interface Define {
        boolean ISLOGTEST = true;
        String COOKIE = "cookie";
        String UPDATE_FAILED = "更新失败";
        String MYSERVICE_TITLE = "我的客服";
        String FIRST_START = "first_start";
        String USERNAME = "username";
        String PASSWORD = "password";
        String DEVICELIST = "设备列表";
        String DEVICEMAP = "设备地图";
        long START_TIME = 3000;//启动页跳转时间为3秒
        String BASE_URL = "http://58.250.204.112:58010/userconsle/";//外网
        //设备列表的默认设置
        int DEFAULTPAGENUM = 1;
        int DEFAULTPAGESIZE = 100;
        int MAXPAGESIZE = 100;
        String WARNINGMAP = "预警地图";
        String WARNINGRECORD = "预警记录";
        int CONNECT_TIME_OUT = 5 * 1000 * 60;
        int RXBUS_COOKIE_CODE = 1;
        int RXBUS_MYDEVICELISTP_CODE = 2;
        int RXBUS_MYDEVICEMAP_TO_DATASHOW_CODE = 3;
        String MYDEVICE_TO_SECONDHOME_ID = "mydevice_to_secondhome_id";//设备列表返回的ID
        String MYDEVICE_TO_SECONDHOME_DATATEMPLATEID="mydevice_to_secondhome_datatemplateid";
        String MYDEVICE_TO_SECONDHOME_TBTITLE="mydevice_to_secondhome_tbtitle";
        int DATASHOW_CENTER_SPACINGINPIXELS = 80;
    }

}
