package cn.edu.swufe.yinyue;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

public class MyApplication extends Application {
    public void onCreate(){
        super.onCreate();
        //初始化UtilCode

        Utils.init(this);
        Realm.init(this);//初始化Realm
    }
}
