package com.hly.video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hly.video.media.IRenderView;
import com.hly.video.media.IjkVideoView;
import com.hly.video.media.PlayerManager;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/8/22~~~~~~
 * ~~~~~~更改时间:2018/8/22~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class IjkActivity extends AppCompatActivity{
    private IjkVideoView mVideoView;
    private PlayerManager player;

    private String url5 = "http://stream1.grtn.cn/tvs2/sd/live.m3u8?_ts&time=1518428696629";
    private String url6 = "http://218.207.213.137//PLTV/88888888/224/3221225879/index.m3u8";
    private String url7 = "http://183.251.61.207/PLTV/88888888/224/3221225829/index.m3u8";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ijk_activity_layout);
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);


        /** 普通播放 start **/
//        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
//        mVideoView.setVideoURI(Uri.parse(url5));
//        mVideoView.start();
        /** 普通播放 end **/

        initVideo();
    }

    //使用滑动控制的话解开注释
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (player.gestureDetector.onTouchEvent(event))
//            return true;
//        return super.onTouchEvent(event);
//    }

    /**
     * 可左半屏滑动控制亮度  右半屏控制音量  双击切换比例  （无提示）
     */
    private void initVideo() {
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.live(true);
        player.setScaleType(PlayerManager.SCALETYPE_WRAPCONTENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(new PlayerManager.PlayerStateListener() {
            @Override
            public void onComplete() {
                Log.e("   player  status    :", "complete");
            }

            @Override
            public void onError() {
                Log.e("   player  status    :", "error");
            }

            @Override
            public void onLoading() {
                Log.e("   player  status    :", "loading");
            }

            @Override
            public void onPlay() {
                Log.e("   player  status    :", "play");
            }
        });
        player.play(url5);
        IjkVideoView videoView = player.getVideoView();
        videoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                switch (i) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        break;
                }
                return false;

            }
        });
    }
}
