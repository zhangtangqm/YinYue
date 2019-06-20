package cn.edu.swufe.yinyue.helps;

import java.util.List;

import cn.edu.swufe.yinyue.models.RecordModel;
import cn.edu.swufe.yinyue.models.UserModel;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    public Realm mRealm;


    public RealmHelp( ) {


        mRealm  = Realm.getDefaultInstance();
    }
    public void close(){

        if(mRealm!=null&& !mRealm.isClosed()){
            mRealm.close();
        }
    }
    public void saveRecord(RecordModel recordModel){
        mRealm.beginTransaction();

        mRealm.insert(recordModel);//数据放到缓存之中
        //提交事务，将缓存中的数据存到数据库中
        mRealm.commitTransaction();
        //add
        mRealm.close();
    }
    //保存用户信息
    public void saveUser( UserModel userModel){
        mRealm.beginTransaction();
        // mRealm.insertOrUpdate(userModel);可以用于更新主键下的其他内容
        mRealm.insert(userModel);//数据放到缓存之中
        //提交事务，将缓存中的数据存到数据库中
        mRealm.commitTransaction();
    }
    //返回所有用户
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query= mRealm.where(UserModel.class);
        RealmResults<UserModel> results= query.findAll();
        return results;
    }
    /*
验证用户信息
 */
    public boolean validateUser(String phone,String password){
        boolean result=false;
        RealmQuery<UserModel> query= mRealm.where(UserModel.class);
        query=query.equalTo("phone",phone).equalTo("password",password);
        UserModel userModel=query.findFirst();
        if(userModel!=null){
            result=true;
        }
        return result;
    }
    //获取当前用户
    public UserModel getUser(){
        RealmQuery<UserModel> query=mRealm.where(UserModel.class);
        UserModel userModel= query.equalTo("phone",UserHelp.getInstance().getPhone()).findFirst();
        return userModel;
    }
    //修改密码
    public void changePassword(String password){
        UserModel userModel=getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }
}
