package cn.edu.swufe.yinyue.activities;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.swufe.yinyue.MainActivity;
import cn.edu.swufe.yinyue.R;

public class MusicActivity extends ListActivity implements   Runnable {
    private ListView mListview;
    Handler handler;


    private final String TAG = "MusicActivity";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // mListview=findViewById(R.id.listview);
        List<String> list1=new ArrayList<String>();//列表没有长度限制
       /* for(int i=1;i<100;i++){
            list1.add("item"+i);
        }*/

        // List<String> list1=new ArrayList<String>();//列表没有长度限制
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);//android.R.layout.simple_list_item_1表示布局，适配器将布局和数据联系起来，ArrayAdapter为数组适配器
        setListAdapter(adapter);//把当前界面用adapter进行管理*/
        Thread t=new Thread(this);
        t.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    List<String> list2= (List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(MusicActivity.this,android.R.layout.simple_list_item_1,list2);//android.R.layout.simple_list_item_1表示布局
                    setListAdapter(adapter);//把当前界面用adapter进行管理
                }



                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void run() {
        List<String> retList = new ArrayList<>();


        //从网络中获取数据
        Log.i("run", "日期不相等，从网络中获取数据");
        Document doc = null;
        try {
            Thread.sleep(3000);
            doc = Jsoup.connect("http://music.taihe.com/").get();
            Log.i(TAG, "run:" + doc.title());
            Elements tables = doc.getElementsByTag("div");//使用标签获得元素


            Element table = tables.get(8);
            Log.i(TAG, "run:table=" + table);
            //获取td中的元素
            Elements tds = doc.getElementsByTag("a");//使用标签获得元素

            // List<RateItem> rateList = new ArrayList<RateItem>();//为了写数据库

            for (int i = 10; i < 17; i++) {//根据网页源代码发现，每8个td一个循环
                Element td1 = tds.get(i);

                String str1=td1.text();
                retList.add(str1);
                this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        switch (i) {  //用swith的方法来实现item的每一项的点击
                            case 0://0代表第一行的点击！也可以点击Item实现跳转！只要用Intent来实现就好！
                                Uri uri = Uri.parse("http://music.taihe.com/");//url为你要链接的地址
                                Intent intent;
                                intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                break;

                            case 1://0代表第一行的点击

                                Uri uri2 = Uri.parse("http://music.taihe.com/songlist");
                                Intent intent2;
                                intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                                startActivity(intent2);
                                // Toast.makeText(FashionListActivity.this, "我终于找到你了......", Toast.LENGTH_SHORT).show();
                                break;
                            case 2://0代表第一行的点击

                                Uri uri3 = Uri.parse("http://music.taihe.com/artist");
                                Intent intent3;
                                intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                                startActivity(intent3);

                                break;
                            case 3://0代表第一行的点击

                                Uri uri4 = Uri.parse("http://music.taihe.com/tag");
                                Intent intent4;
                                intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                                startActivity(intent4);
                                break;
                            case 4://0代表第一行的点击

                                Uri uri5 = Uri.parse("http://music.taihe.com/top");
                                Intent intent5;
                                intent5 = new Intent(Intent.ACTION_VIEW, uri5);
                                startActivity(intent5);
                                break;
                            case 5://0代表第一行的点击

                                Uri uri6 = Uri.parse("https://www.showstart.com/event/list");
                                Intent intent6;
                                intent6 = new Intent(Intent.ACTION_VIEW, uri6);
                                startActivity(intent6);
                                break;
                            case 6://0代表第一行的点击

                                Uri uri7 = Uri.parse("http://music.taihe.com/redrank");
                                Intent intent7;
                                intent7 = new Intent(Intent.ACTION_VIEW, uri7);
                                startActivity(intent7);
                                break;
                        }
                    }
                });
            }


             /*   //记录更新日期
                SharedPreferences sp=getSharedPreferences("myrate",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=sp.edit();
                edit.putString(DATE_SP_KEY,curDateStr);
                edit.commit();
                Log.i(TAG,"run,更新日期结束"+curDateStr);*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






        //bundle中保存获取的数据
        //获取msg对象，用于返回主线程
        Message msg=handler.obtainMessage(5);
        //msg.what=5;//what用于标记当前Message的属性
        msg.obj=retList;
        handler.sendMessage(msg);

    }
}


