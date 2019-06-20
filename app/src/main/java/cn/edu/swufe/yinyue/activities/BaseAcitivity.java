package cn.edu.swufe.yinyue.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.R;

//作为activities的父类，描述activities的共性
public class BaseAcitivity extends Activity {

    private ImageView mIvBack,mIvMe;
    private TextView mTvTitle;
    //简化findViewById的写法：接受一个资源文件的Id，返回一个T（T为View子类），T extends View返回一个继承至View的实体
    protected<T extends View>T fd (@IdRes int id){
        return findViewById(id);
    }
    // 初始化NavBar
    protected void initNavBar(boolean isshowBack,String title,boolean isShowMe){//第一个参数表示后退按钮会不会进行展示,第二个参数表示中间显示汉字，第三个参数表示是否显示“我”图标

        mIvBack=fd(R.id.iv_back);//使用fd方法
        mIvMe=findViewById(R.id.iv_me);
        mTvTitle=findViewById(R.id.tv_title);

       mIvBack.setVisibility(isshowBack ? View.VISIBLE: View.GONE);//后退按钮在isShowBack为真时显示，假时隐藏

        mIvMe.setVisibility(isShowMe ? View.VISIBLE: View.GONE);
        mTvTitle.setText(title);//显示传入的title

        mIvBack.setOnClickListener(new View.OnClickListener() {//设置点击事件
                                       @Override
                                       public void onClick(View v) {
                                           onBackPressed();//后退操作
                                       }
                                   }
        );
     //为个人中心设置点击事件
        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到个人中心页面
                Intent intent =new Intent(BaseAcitivity.this,MeActivity.class);
                startActivity(intent);

            }
        });

    }
}
