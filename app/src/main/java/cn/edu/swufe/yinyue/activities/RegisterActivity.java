package cn.edu.swufe.yinyue.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.utils.UserUtils;

public class RegisterActivity extends BaseAcitivity {

    private EditText mInputPhone, mInputPassword, mInputPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    //初始化View

    private void initView() {

        initNavBar(false, "注册", false);//表示后退按钮不显示，title显示登录，“我”不显示


        mInputPhone=(EditText)findViewById(R.id.input_phone);
        mInputPassword=(EditText)findViewById(R.id.input_password);
        mInputPasswordConfirm=(EditText)findViewById(R.id.input_password_confirm);


    }
    /*注册按钮点击事件
    1.用户输入合法性验证：
           手机号是否合法，用户是否输入密码，
            两次输入密码是否相同，
            输入手机号是否已经被注册
    2.将输入的手机号和密码（MD5加密）保存到数据库中

     */
    public void onRegisterClick (View v) {
        String phone = mInputPhone.getText().toString();
        String password = mInputPassword.getText().toString();
        String passwordConfirm = mInputPasswordConfirm.getText().toString();

        boolean result = UserUtils.registerUser(this, phone, password, passwordConfirm);

        if (!result) return;
        //成功之后后退页面
        onBackPressed();


    }
}
