package cn.edu.swufe.yinyue.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class RecordModel extends RealmObject {



//添加主键声明
        @PrimaryKey
        private String time;
//表示不可或缺
        @Required
        private String xinQingRecord;

        public String getTime() {
        return time;
    }

        public void setTime(String time) {
        this.time = time;
    }

        public String getXinQingRecord() {
        return xinQingRecord;
    }

        public void setXinQingRecord(String xinQingRecord) {
        this.xinQingRecord = xinQingRecord;
    }
    }

