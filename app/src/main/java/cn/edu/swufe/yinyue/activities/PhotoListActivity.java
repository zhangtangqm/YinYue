package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.edu.swufe.yinyue.R;

public class PhotoListActivity extends BaseAcitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        initView();
    }
    private void initView() {

        initNavBar(true, "阅·摄影", true);//表示后退按钮不显示，title显示登录，“我”不显示
    }
    public void onPhotoAClick(View v){
        Uri uri = Uri.parse("http://www.fengniao.com/");
        Intent intent;
        intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    public void onPhotoBClick(View v){
        Uri uri = Uri.parse("http://photo.poco.cn/");
        Intent intent;
        intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
