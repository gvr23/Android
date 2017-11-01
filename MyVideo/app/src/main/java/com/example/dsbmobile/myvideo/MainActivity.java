package com.example.dsbmobile.myvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.vvVid)
    VideoView vvVid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set the path
        vvVid.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.demovideo);

        //To get the video controls
        MediaController mediaController = new MediaController(this);

        //sync up the view to media contoller and vice versa
        mediaController.setAnchorView(vvVid);
        vvVid.setMediaController(mediaController);

        vvVid.start();
    }
}
