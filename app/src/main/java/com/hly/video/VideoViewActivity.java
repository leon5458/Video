package com.hly.video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/8/21~~~~~~
 * ~~~~~~更改时间:2018/8/21~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class VideoViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button play, pause, replay, stop;
    private VideoView vv_video;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview_activity_layout);
        vv_video = findViewById(R.id.videoview_view);
        play = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        replay = findViewById(R.id.restart);
        stop = findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                play(0);
                break;
            case R.id.pause:
                vv_video.pause();
                break;
            case R.id.restart:
                play(0);
                break;
            case R.id.stop:
                vv_video.stopPlayback();
                break;
        }
    }

    private void play(int i) {
        /**
         * 网络视频
         */
        //初始化配置
//        Uri uri = Uri.parse("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4");
//        设置视频路径
//        vv_video.setVideoURI(uri);

        /**
         * 本地
         */
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.hehe);
        vv_video.setVideoURI(uri);


        //设置视频管理器
        MediaController controller = new MediaController(this);
        //关联管理器
        vv_video.setMediaController(controller);
        controller.setAnchorView(vv_video);
        //开始播放视频
        vv_video.start();

        vv_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText( VideoViewActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
            }
        });
        vv_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                // 发生错误重新播放
                play(0);
                return false;
            }
        });

    }
}
