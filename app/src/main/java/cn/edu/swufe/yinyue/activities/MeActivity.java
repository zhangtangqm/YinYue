package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.helps.UserHelp;
import cn.edu.swufe.yinyue.utils.UserUtils;

public class MeActivity extends BaseAcitivity {

    private TextView mTvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }
    private void initView(){

        //获取并在个人中心界面展示用户名
        mTvUser=findViewById(R.id.tv_user);
       mTvUser.setText("用户名："+UserHelp.getInstance().getPhone());
        initNavBar(true, "个人中心", false);
    }
    //修改密码
    public void onChangeClick(View v){
        Intent intent =new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);


    }
    //退出登录
    public void onLogoutClick(View v){
        //退出登录
        //跳转到登陆界面

        UserUtils.logout(this);


    }
}
