package cn.edu.swufe.yinyue.helps;

public class UserHelp {
    private static UserHelp instance;
    private UserHelp(){};
    public static UserHelp getInstance(){
        if(instance==null){
            synchronized (UserHelp.class){
                if(instance==null){
                    instance=new UserHelp();
                }
            }
        }
        return instance;
    }
    private String phone;
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
}
