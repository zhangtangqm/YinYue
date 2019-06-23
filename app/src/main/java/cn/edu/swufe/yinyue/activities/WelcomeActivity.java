package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.swufe.yinyue.MainActivity;
import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.utils.UserUtils;

public class WelcomeActivity extends BaseAcitivity {


    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }
    private void init(){


        mTimer= new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                   toLogin();
            }
        },3*1000);
    }

    //跳转到loginActivity界面
    private void toLogin(){
        Intent intent =new Intent(this,LoginActivity.class);//通过intent跳转到LoginActivity
        startActivity(intent);
        finish();
    }
}
