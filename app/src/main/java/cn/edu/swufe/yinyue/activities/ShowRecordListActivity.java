package cn.edu.swufe.yinyue.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.models.RecordModel;
import io.realm.Realm;
import io.realm.RealmResults;

public class ShowRecordListActivity extends BaseAcitivity {

    private ListView mListView;
    private List<HashMap<String, String>> data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record_list);
        mListView = (ListView) findViewById(R.id.listView);

        data1=new ArrayList<>();



        SimpleAdapter sim_adapter = new SimpleAdapter(this, getData(), R.layout.record_list_item, new String[]{"text", "time"}, new int[]{R.id.fashion_view, R.id.fashion_text});
        //4.把listView与sim_adapter适配器绑定
        mListView.setAdapter(sim_adapter);



    }

    public List<HashMap<String, String>> getData() {

        Realm mRealm;
        mRealm = Realm.getDefaultInstance();
        RealmResults<RecordModel> menList = mRealm.where(RecordModel.class).findAll();
        for (int i = 0; i < menList.size(); i++) {
            HashMap<String, String> data2 = new HashMap<>();

             data2.put("time", menList.get(i).getTime());
            data2.put("text",menList.get(i).getXinQingRecord());

            data1.add(data2);
        }
        return data1;
    }
}
