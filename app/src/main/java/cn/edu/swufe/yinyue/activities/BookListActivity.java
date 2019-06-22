package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.edu.swufe.yinyue.R;

public class BookListActivity extends BaseAcitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        initView();
    }
    private void initView() {

        initNavBar(true, "阅·书籍", true);//表示后退按钮不显示，title显示登录，“我”不显示
    }

    public void onBookAClick(View v){

        Uri uri1 = Uri.parse("http://www.bookschina.com/");
        Intent intent1;
        intent1 = new Intent(Intent.ACTION_VIEW,uri1);
        startActivity(intent1);
    }
    public void onBookBClick(View v){

        Uri uri = Uri.parse("http://e.dangdang.com/");
        Intent intent;
        intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
