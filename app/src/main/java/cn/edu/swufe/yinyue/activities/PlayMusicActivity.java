package cn.edu.swufe.yinyue.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.views.PlayMusicView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseAcitivity {
    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //隐藏掉statusbar部分
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {

            initView();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    private void initView() throws IOException {

        mIvBg=findViewById(R.id.iv_bg);

        //glide-transformations,进行图片高斯模糊处理(配合glide使用)
        Glide.with(this)
                .load("http://www.cnr.cn/xjfw/btfw/2011btfw/jkwh/jkwt/20160516/W020160516492035673595.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))//radius:表示模糊程度   sampling：表示为原图的宽高比，10表示为原图的10分之一
                .into(mIvBg);





        mPlayMusicView=findViewById(R.id.play_music_view);
        //add


        mPlayMusicView.setMusicIcon("http://www.cnr.cn/xjfw/btfw/2011btfw/jkwh/jkwt/20160516/W020160516492035673595.jpg");//

        //mPlayMusicView.playMusic("http://music.taihe.com/song/124810874");


        // mPlayMusicView.setMusic(mMusicModel);
        //mPlayMusicView.playMusic();
        mPlayMusicView.playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");
    }


    //后退按钮点击事件
    public void onBackClick(View view){
        onBackPressed();
    }
}
