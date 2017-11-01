package com.example.dsbmobile.brainapp;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnGo;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView tvOp;
    TextView tvResult;
    TextView tvScore;
    TextView tvTimer;
    CountDownTimer timer;

    ArrayList<Integer> answers = new ArrayList<>();
    int locationCorrectAnswer;
    int incorrectAnswer;
    int numberOfQuestions;
    int score;
    boolean active;

    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        inicializar();
    }

    private void findViews() {
        btnGo = (Button) findViewById(R.id.btnGo);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        tvOp = (TextView) findViewById(R.id.tvOperation);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
    }

    private void inicializar() {
        active = true;
        start();
        generateQuestion();

    }

    public void start(){
        timer =  new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                tvTimer.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                    active = false;
                    tvTimer.setText("0s");
                    tvResult.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                    btnGo.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void restart(View view){
        numberOfQuestions = 0;
        score = 0;
        active = true;
        tvScore.setText("0/0");
        tvResult.setText("");
        btnGo.setVisibility(View.INVISIBLE);
        inicializar();
    }

    public void chooseAnswer(View view){
        if (active){
            if (view.getTag().toString().equalsIgnoreCase(Integer.toString(locationCorrectAnswer))){
                score++;
                tvResult.setText("Correct!");
            }else{
                tvResult.setText("Wrong!");
            }
            numberOfQuestions++;
            tvScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generateQuestion();
        }
    }

    public void generateQuestion(){
        if (active){
            rand = new Random();
            int a = rand.nextInt(0 + 20 + 1);
            int b = rand.nextInt(0 + 20 + 1);
            locationCorrectAnswer = rand.nextInt(0 + 3 + 1);
            answers.clear();

            for (int i = 0; i < 4; i++){
                if (i == locationCorrectAnswer){
                    answers.add(a + b);
                }else{
                    incorrectAnswer = rand.nextInt(0 + 40 + 1);

                    while(incorrectAnswer == (a + b)){
                        incorrectAnswer = rand.nextInt(0 + 40 + 1);
                    }
                    answers.add(incorrectAnswer);
                }
            }

            tvOp.setText(Integer.toString(a) + " + " + Integer.toString(b));
            btn1.setText(Integer.toString(answers.get(0)));
            btn2.setText(Integer.toString(answers.get(1)));
            btn3.setText(Integer.toString(answers.get(2)));
            btn4.setText(Integer.toString(answers.get(3)));
        }else {}
    }
}
