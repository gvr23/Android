package com.example.dsbmobile.audioapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btnPlay)
    Button btnPlay;
    @Bind(R.id.btnPause)
    Button btnPause;
    @Bind(R.id.seekBar)
    SeekBar seekBar;
    @Bind(R.id.sbTrack)
    SeekBar sbTrack;

    MediaPlayer mPlayer;
    AudioManager aManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPlayer = MediaPlayer.create(this, R.raw.chewbacca);
        aManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //max Volume for device
        int maxVolume = aManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //set the mx volume to the max value oof the seekbar
        seekBar.setMax(maxVolume);

        //current volume
        int currVolume = aManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //set the current volume to the current of the seekbar
        seekBar.setProgress(currVolume);

        //track seekBar
        sbTrack.setMax(mPlayer.getDuration());

        //scheduling task to be done every second, known as a runnable regularly
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sbTrack.setProgress(mPlayer.getCurrentPosition());
            }
        },0,200);

        eventos();
    }

    private void eventos() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //completely has been changed
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                aManager.setStreamVolume(aManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbTrack.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.btnPlay)
    public void onBtnPlayClicked() {
        mPlayer.start();
    }

    @OnClick(R.id.btnPause)
    public void onBtnPauseClicked() {
        mPlayer.pause();
    }
}
