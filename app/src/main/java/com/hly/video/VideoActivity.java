package com.hly.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/8/21~~~~~~
 * ~~~~~~更改时间:2018/8/21~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class VideoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity_layout);

        //取消严格模式  FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4";
                String extension = MimeTypeMap.getFileExtensionFromUrl(url);
                String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
                mediaIntent.setDataAndType(Uri.parse(url), mimeType);
                startActivity(mediaIntent);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                String path = Environment.getExternalStorageDirectory().getPath()+ "DCIM/Camera/test.mp4";//该路径可以自定义
//                File file = new File(path);
//                Uri uri = Uri.fromFile(file);
//                intent.setDataAndType(uri, "video/*");
//                startActivity(intent);

//                String path = Environment.getExternalStorageDirectory().getPath() + "DCIM/Camera/test.mp4";//该路径可以自定义
                String path = Environment.getExternalStorageDirectory().getPath() + "/miui/gallery/cloud/owner/111/hehe.mp4";//该路径可以自定义,不同的手机路径不一样注意别写错了
                Uri uri = Uri.parse("file://" + path);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri , "video/*");
                try {
                    VideoActivity.this.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(VideoActivity.this, "沒有默认播放器", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
}
