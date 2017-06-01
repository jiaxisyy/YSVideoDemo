package com.example.shuangxiang.ysvideodemo.manager;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/22.
 */
public class ActivityManager {
    private List<WeakReference<Activity>> activityList = new ArrayList<>();
    private static volatile ActivityManager instance;


    private ActivityManager() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(WeakReference<Activity> activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    // 遍历所有Activity并finish
    public void exit() {

        for (WeakReference<Activity> activity : activityList) {
            Activity ac = activity.get();
            if (ac != null) {
                ac.finish();
            }
        }
//        友盟关闭前调用
//        MobclickAgent.onKillProcess();
        activityList.clear();
        System.exit(0);
    }

}
