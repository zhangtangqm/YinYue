package cn.edu.swufe.yinyue.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.edu.swufe.yinyue.fragments.BookFragment;
import cn.edu.swufe.yinyue.fragments.FashionFragment;
import cn.edu.swufe.yinyue.fragments.PhotoFragment;

public class ReadAdapter  extends FragmentPagerAdapter {
    private String[] title=new String[]{"时尚","阅读","摄影"};
    public ReadAdapter(FragmentManager manager){
        super(manager);
    }



    public Fragment getItem(int position) {
        //返回需要管理的Fragment对象
        if(position==0){
            return new FashionFragment();
        }else if(position==1){
            return new BookFragment();
        }else{
            return new PhotoFragment();
        }

    }

    public CharSequence getPageTitle(int position){
        return title[position];
    }

    public int getCount() {
        //返回当前需要管理的Fragment是多少个
        return 3;//一共有3个Fragment
    }
}

