package cn.edu.swufe.yinyue.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.swufe.yinyue.R;

public class FashionListActivity extends AppCompatActivity {
    private ListView mFashionListView;
    private List<Map<String,Object>> data;//集合
    private String[] app={"Vogue：寻找美丽  ","YOKA:态度创造时尚","ELLE：世界时尚","cosmopolitan:属于你的时尚","L'OFFICIEL:时尚高八度","TrendsGroup:敢于不完美"};//ListView中Item的每一项
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_list);

       mFashionListView= (ListView)findViewById(R.id.fashionListView);
        data=new ArrayList<>();


        SimpleAdapter sim_adapter=new SimpleAdapter(this,getData(),R.layout.fashion_list_item,new String[]{"img","text"},new int[]{R.id.fashion_view,R.id.fashion_text});
        //把listView与sim_adapter适配器绑定
        mFashionListView.setAdapter(sim_adapter);
        //实现listview列表项的点击事件
        mFashionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){  //用swith的方法实现item的每一项的点击
                    case 0:
                        Uri uri = Uri.parse("http://www.vogue.com.cn/");//url为链接的地址
                        Intent intent;
                        intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;

                    case 1://0代表第一行的点击

                        Uri uri2 = Uri.parse("http://www.yoka.com/");
                        Intent intent2;
                        intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                        startActivity(intent2);

                        break;
                    case 2:

                        Uri uri3 = Uri.parse("http://mini-s3.ellechina.com/maintenance/");
                        Intent intent3;
                        intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                        startActivity(intent3);

                        break;
                    case 3:

                        Uri uri4 = Uri.parse("http://www.cosmopolitan.com.cn/");
                        Intent intent4;
                        intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                        startActivity(intent4);
                        break;
                    case 4:

                        Uri uri5 = Uri.parse("http://www.lofficiel.cn/");
                        Intent intent5;
                        intent5 = new Intent(Intent.ACTION_VIEW,uri5);
                        startActivity(intent5);
                        break;
                    case 5:

                        Uri uri6 = Uri.parse("http://www.trendsgroup.com.cn/");
                        Intent intent6;
                        intent6 = new Intent(Intent.ACTION_VIEW,uri6);
                        startActivity(intent6);
                        break;
                }
            }
        });
    }
    public List<Map<String,Object>> getData() {//第二个参数
        for (int i=0;i<app.length;i++) {
            Map<String, Object> map = new HashMap<>();
            if (i == 0) {
                map.put("img", R.mipmap.fashion_a);
                map.put("text", app[i]);
                data.add(map);
            }
            else if(i == 1) {
                map.put("img", R.mipmap.fashion_c);
                map.put("text", app[i]);
                data.add(map);
            }
            else if(i == 2) {
                map.put("img", R.mipmap.fashion_f);
                map.put("text", app[i]);
                data.add(map);
            }
            else if(i == 3) {
                map.put("img", R.mipmap.fashion_d);
                map.put("text", app[i]);
                data.add(map);
            }
            else if(i == 4) {
                map.put("img", R.mipmap.fashion_e);
                map.put("text", app[i]);
                data.add(map);
            }
            else {
                map.put("img", R.mipmap.fashion_f);
                map.put("text", app[i]);
                data.add(map);
            }
        }
        return data;
    }
}
