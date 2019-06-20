package cn.edu.swufe.yinyue.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.utils.UserUtils;

public class ChangePasswordActivity extends BaseAcitivity {

    private EditText mOldPassword,mPassword,mPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }
    private void initView() {

        initNavBar(true, "修改密码", false);

        mOldPassword=findViewById(R.id.input_old_password);
        mPassword=findViewById(R.id.input_password);
        mPasswordConfirm=findViewById(R.id.input_password_confirm);
    }
    public void onChangePasswordClick(View v){


        String oldPassword = mOldPassword.getText().toString();
        String password = mPassword.getText().toString();
        String passwordConfirm = mPasswordConfirm.getText().toString();

        boolean result = UserUtils.changePassword(this, oldPassword, password, passwordConfirm);
        if (!result) {
            return;
        }

        UserUtils.logout(this);

    }

}
