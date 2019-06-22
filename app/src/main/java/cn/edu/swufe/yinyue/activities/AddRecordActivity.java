package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.utils.RecordUtils;
import io.realm.Realm;

public class AddRecordActivity extends BaseAcitivity {
    private Realm mRealm;
    private EditText mAddTimeEditText,mAddRecordEdiText;
    public String TAG="AddRecordActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        initView();
    }
    private void initView() {

        initNavBar(true, "阅·心情小记", true);//表示后退按钮不显示，title显示登录，“我”不显示


    }
    public void onAddRecordClick(View v){
        mAddRecordEdiText=(EditText)findViewById(R.id.addRecordEditText);
        mAddTimeEditText=(EditText)findViewById(R.id.addTimeEditText);
        String addTime=mAddTimeEditText.getText().toString();
        String addRecord= mAddRecordEdiText.getText().toString();

        RecordUtils.addRecord(this,addTime,addRecord);
        // onBackPressed();
        Log.i(TAG,"保存成功");
    }
    public void onItemClick(View v){

        Intent intent=new Intent(this,ShowRecordListActivity.class);
        startActivity(intent);
    }


}
