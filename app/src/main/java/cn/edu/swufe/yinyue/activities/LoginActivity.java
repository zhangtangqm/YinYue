package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.swufe.yinyue.MainActivity;
import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.utils.UserUtils;

public class LoginActivity extends BaseAcitivity{
    //private InputView mInputPhone, mInputPassword;
    EditText mInputPhone;
    EditText mInputPassword;
    Button mcommitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    //初始化View
    private void initView() {

        initNavBar(false, "登录", false);//表示后退按钮不显示，title显示登录，“我”不显示

        mInputPhone = (EditText)findViewById(R.id.input_phone);
        mInputPassword = (EditText)findViewById (R.id.input_password);
    }

    //注册点击事件，跳转注册页面
    public void onRegisterClick(View v) {
        Intent intent =new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    //登录按钮的点击事件
    public void onCommitClick(View v) {

        String phone = mInputPhone.getText().toString();
        String password = mInputPassword.getText().toString();

//调用UserUtils方法,验证用户输入是否合法
         if (!UserUtils.validateLogin(this, phone, password)) {//传入手机号和密码
          return;
         }
        //如果正确跳转到MainAcitivity
        mcommitButton=(Button)findViewById(R.id.commitBtn);



        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
