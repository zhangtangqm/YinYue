package cn.edu.swufe.yinyue.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

//播放音乐
public class MediaPlayHelp {

    private String mPath;
    private static MediaPlayHelp instance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;

    public void setOnMeidaPlayerHelperListener(OnMediaPlayerHelperListener onMeidaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMeidaPlayerHelperListener;
    }


    public static MediaPlayHelp getInstance(Context context) {

        if (instance == null) {
            ;
            synchronized (MediaPlayHelp.class) {
                if (instance == null) {
                    instance = new MediaPlayHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayHelp(Context context) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /*
    1.setPath:当前需要播放的音乐
    2.start:播放音乐
    3.pause:暂停播放
     */
//  1.setPath:当前需要播放的音乐
    public void setPath(String path) throws IOException {
        /*
        1.如果音乐正在播放，重置音乐播放状态
        2.设置播放音乐路径
        3.准备播放
         */
        mPath = path;
        //1.如果音乐正在播放，重置音乐播放状态
        if (mMediaPlayer.isPlaying()) {
            //重置
            mMediaPlayer.reset();
        }
        //  2.设置播放音乐路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.准备播放,异步加载
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                if (onMediaPlayerHelperListener != null) {
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
    }





    //返回正在播放的音乐路径
    public String getPath(){
      return mPath;
    }

    // 2.start:播放音乐
    public void start(){
        if (mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }
    //  3.pause:暂停播放
    public void pause(){
        mMediaPlayer.pause();
    }
    public interface OnMediaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);

    }

public interface OnMeidaPlayerHelperListener {
    void onPrepared(MediaPlayer mp);
    void onCompletion(MediaPlayer mp);
}

}