package cn.edu.swufe.yinyue.views;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.helps.MediaPlayHelp;
import cn.edu.swufe.yinyue.R;
import cn.edu.swufe.yinyue.helps.MediaPlayHelp;


public class PlayMusicView extends FrameLayout {

    private String mPath;
    private Context mContext;
    private View mView;
    private FrameLayout mFlPlayMusic;
    private ImageView mIvIcon,mIvPlay,mIvNeedle;
    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;
    private boolean isPlaying, isBindService;
    private MediaPlayHelp mMediaPlayerHelp;



    private Intent mServiceIntent;

    public PlayMusicView(@NonNull Context context) {
        super(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private void init(Context context){

        //播放音乐：MediaPlayer
        mContext=context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);

        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mIvNeedle = mView.findViewById(R.id.iv_needle);
        mIvPlay = mView.findViewById(R.id.iv_play);
        /*定义需要执行的动画
        1、光盘转动的动画
        2、指针指向光盘的动画
        3.指针离开光盘的动画
        startAnimation执行动画
        */

        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    trigger();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);

        addView(mView);

        mMediaPlayerHelp=MediaPlayHelp.getInstance(mContext);
    }
    //切换播放状态
    private void trigger () throws IOException {
      //判断播放状态
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
        }
    }

    //播放音乐
   public void playMusic(String  path) throws IOException {
        mPath=path;
        //isplaying表示是否播放
        isPlaying = true;
        //播放时隐藏
        mIvPlay.setVisibility(View.GONE);
        //动画，指针指向圆盘，圆盘开始转动
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);

/*
1.判断当前音乐是否是已经在播放的音乐
2.如果当前音乐是已经在播放的音乐，直接执行start方法
3.如果当前音乐不是需要播放的音乐，就调用setPath方法
 */
if(mMediaPlayerHelp.getPath()!=null&& mMediaPlayerHelp.getPath().equals(path)){

    mMediaPlayerHelp.start();
}
else {
    mMediaPlayerHelp.setPath(path);
    mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayHelp.OnMediaPlayerHelperListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {

            mMediaPlayerHelp.start();

        }
    });
}

//

    }

    //
    //

    //停止音乐
    public void stopMusic () {
        isPlaying = false;
        //停止播放时显示
        mIvPlay.setVisibility(View.VISIBLE);
        //动画效果，圆盘删除动画效果，指针离开圆盘
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);

        mMediaPlayerHelp.pause();

        //mMusicBinder.stopMusic();
    }

    //设置光盘中显示的图片
   public void setMusicIcon (String icon) {
        Glide.with(mContext)
                .load(icon)//mMusicModel.getPoster()
                .into(mIvIcon);
    }




}
