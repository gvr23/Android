package com.example.dsbmobile.timeapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekTimer;
    Button btnGo;
    Button btnShow;
    Button btnHide;
    TextView tvTime;
    ImageView ivCarl;
    MediaPlayer mPlayer;

    boolean activeTimer = false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        inicializar();
        eventos();
    }

    private void findViews() {
        seekTimer = (SeekBar) findViewById(R.id.seekBarTimer);
        btnGo = (Button) findViewById(R.id.btnStart);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnHide = (Button) findViewById(R.id.btnHide);
        ivCarl = (ImageView) findViewById(R.id.ivNegroCarl);
        tvTime = (TextView) findViewById(R.id.tvTime);
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.moan);
    }

    private void inicializar() {
        //configuring the timer
        seekTimer.setMax(600);
        seekTimer.setProgress(30);
        seekTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void eventos() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    counter();

                }catch(Exception e){
                    String message = getStackTrace(e);
                    Log.i("error", message);
                }
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivCarl.setVisibility(View.INVISIBLE);
                btnHide.setVisibility(View.INVISIBLE);
                btnShow.setVisibility(View.VISIBLE);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivCarl.setVisibility(View.VISIBLE);
                btnShow.setVisibility(View.INVISIBLE);
                btnHide.setVisibility(View.VISIBLE);
            }
        });
    }

    public void updateTimer(int secondsLeft){
        //by casting it to an int it will round down
        // i is the progress of the seekbar that ranges between the min and max
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secString = Integer.toString(seconds);
        if(seconds <= 9){
            secString = "0" + secString;
        }
        Log.i("tst", secString);
        tvTime.setText(Integer.toString(minutes) + ":" + secString);
    }

    public void counter(){
        if (!activeTimer){
            activeTimer = true;
            seekTimer.setEnabled(false);
            btnGo.setText("Stop");

           countDownTimer = new CountDownTimer(seekTimer.getProgress() * 1000 + 100,1000){

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    tvTime.setText("0:00");
                    mPlayer.start();
                    reset();
                }
            }.start();
        }else{
            reset();
        }
    }


//MIDDLEWARE
    public void reset(){
        activeTimer = false;
        tvTime.setText("0:30");
        seekTimer.setProgress(30);
        seekTimer.setEnabled(true);
        countDownTimer.cancel();
        btnGo.setText("Go!");
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
