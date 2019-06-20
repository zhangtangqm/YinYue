package cn.edu.swufe.yinyue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import cn.edu.swufe.yinyue.activities.BaseAcitivity;

public class MainActivity extends BaseAcitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化View
    private void initView() {

        initNavBar(false, "音·阅", true);//表示后退按钮不显示，title显示登录，“我”不显示


    }
}
