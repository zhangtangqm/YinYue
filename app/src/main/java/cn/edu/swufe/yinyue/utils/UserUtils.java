package cn.edu.swufe.yinyue.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.activities.LoginActivity;
import cn.edu.swufe.yinyue.helps.RealmHelp;
import cn.edu.swufe.yinyue.helps.UserHelp;
import cn.edu.swufe.yinyue.models.UserModel;

public class UserUtils {
    public static boolean validateLogin(Context context, String phone, String password) {

//        精确验证
        //当输入不是正确手机号时
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            //toast会给出一个提醒
            return false;
        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;

        }
        /*
        1.当前手机号是否已经被注册了
        2.用户输入的手机号和密码是否匹配
         */
        if(!UserUtils.userExistFromPhone(phone)){
            Toast.makeText(context, "当前手机号未注册", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelp realmHelper=new RealmHelp();
        boolean result=realmHelper.validateUser(phone,EncryptUtils.encryptMD5ToString(password));
        realmHelper.close();
        if(!result){
            Toast.makeText(context, "手机号或者密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }

        //保存用户登录标记

      /*  boolean isSave=SPUtils.saveUser(context,phone);
        if(!isSave){
            Toast.makeText(context, "系统错误，请稍后重试", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        //保存用户标记（手机号）
        UserHelp.getInstance().setPhone(phone);

        return true;
    }


    //退出登录
    public static void logout(Context context){
        //删除保存在SharedPreferences中的数据

      /*  boolean isRemove= SPUtils.removeUser(context);
        if(!isRemove){
            Toast.makeText(context, "系统错误，请稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }*/
        //跳转到登录页面
        Intent intent=new Intent(context,LoginActivity.class);
        //解决在返回到登陆界面之后，点击手机返回按钮应当退出程序的问题
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//清除返回栈中的Activity，并且新生成一个Task栈
        context.startActivity(intent);
        //overridePendingTransition在startAcitivity之后调用，解决页面切换动画问题，定义Acitivity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    //注册用户
    public static boolean registerUser (Context context, String phone, String password, String passwordConfirm) {
        //输入内容合法性验证
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(password) || !password.equals(passwordConfirm)) {
            Toast.makeText(context,"请确认密码 ", Toast.LENGTH_SHORT).show();
            return false;
        }

        //用户当前输入的手机号是否已经被注册
        /**
         *1.通过Realm获取已经注册的所有用户
         *2.根据用户输入的手机号匹配查询的所有用户，如果匹配，则该手机号已经被注册
         */
        if (UserUtils.userExistFromPhone(phone)) {
            Toast.makeText(context, "该手机号已存在", Toast.LENGTH_SHORT).show();
            return false;
        }


        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        //EncryptUtils.encryptMD5ToString(password)进行MD5加密
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        UserUtils.saveUser(userModel);

        return true;
    }

    //保存数据到数据库
    public static void saveUser (UserModel userModel) {
        RealmHelp realmHelper = new RealmHelp();
        realmHelper.saveUser(userModel);
        realmHelper.close();
    }
    /*
    根据手机号判断用户是否存在
     */
    public static boolean userExistFromPhone(String phone){
        boolean result=false;

        RealmHelp realmHelp=new RealmHelp();
        //RealmHelp realmHelp=new RealmHelp();
        List<UserModel> allUser= realmHelp.getAllUser();

        for(UserModel userModel:allUser){
            if(userModel.getPhone().equals(phone)){
                //当前手机号已经存在于数据库中了
                result=true;
                break;
            }
        }
        realmHelp.close();
        return result;
    }

    //验证是否存在已登录用户
  /*  public static boolean validateUserLogin(Context context){

        //return  SPUtils.isLoginUser(context);
    }*/

    /*修改密码
    1.验证是否输入原密码
    2.验证新密码是否输入
    3.确认密码是否与新密码相同
    4.原密码是否正确
          在Realm数据库中获得当前登录的用户数据
          验证密码是否相同
    利用Realm自动更新的功能进行修改密码操作
     */

    public static boolean changePassword (Context context, String oldPassword, String password, String passwordConfirm) {

        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(context, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password) || !password.equals(passwordConfirm)) {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }

//验证原密码是否正确
        RealmHelp realmHelper = new RealmHelp();
        UserModel userModel = realmHelper.getUser();
        if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())) {
            Toast.makeText(context, "原密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelper.changePassword(EncryptUtils.encryptMD5ToString(password));

        realmHelper.close();

        return true;
    }
}



