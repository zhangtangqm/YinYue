package cn.edu.swufe.yinyue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.swufe.yinyue.activities.AddRecordActivity;
import cn.edu.swufe.yinyue.activities.BaseAcitivity;
import cn.edu.swufe.yinyue.activities.MusicActivity;
import cn.edu.swufe.yinyue.activities.PlayMusicActivity;
import cn.edu.swufe.yinyue.activities.ReadActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseAcitivity {

    private Button mBtn;
    private CircleImageView mRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   mBtn=findViewById(R.id.btn);
        initView();
    }

    //初始化View
    private void initView() {

        initNavBar(false, "音·阅", true);//表示后退按钮不显示，title显示登录，“我”不显示



    }

   // mBtn.setOnClickListener()
    public void onButtonItemClick(View v){
        Intent intent=new Intent(this,ReadActivity.class);
        startActivity(intent);
    }
    public void onImageBClick(View  v){
        Intent intent=new Intent(this,AddRecordActivity.class);
        startActivity(intent);
    }
    public void onImageCClick(View v){
        Intent intent=new Intent(this,MusicActivity.class);
        startActivity(intent);
    }
    public void onItemAClick(View v){
        Intent intent=new Intent(this,PlayMusicActivity.class);
        startActivity(intent);
    }
}
