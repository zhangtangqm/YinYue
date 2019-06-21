package cn.edu.swufe.yinyue.utils;

import android.content.Context;

import cn.edu.swufe.yinyue.helps.RealmHelp;
import cn.edu.swufe.yinyue.models.RecordModel;

public class RecordUtils {
    public static boolean addRecord (Context context, String time, String record) {

        RecordModel recordModel=new RecordModel();
        recordModel.setTime(time);
        recordModel.setXinQingRecord(record);
        RecordUtils.saveRecord(recordModel);

        return true;
    }
    public static void saveRecord (RecordModel recordModel) {
        RealmHelp realmHelper = new RealmHelp();
        realmHelper.saveRecord(recordModel);
        realmHelper.close();
    }
}
