package com.example.dsbmobile.connect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    boolean gameIsActive = true;
    //yellow = 0, red = 1
    int activePlayer = 0;
    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2},
                                {3, 4, 5},
                                {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Bind(R.id.btnReset)
    Button btnReset;
    @Bind(R.id.winnerMessage)
    TextView winnerMessage;
    @Bind(R.id.llReset)
    LinearLayout llReset;
    @Bind(R.id.glboard)
    GridLayout glboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void fadeIn(View view) {
        ImageView ivView = (ImageView) view;
        int tappedView = Integer.parseInt(ivView.getTag().toString());

        if (gameState[tappedView] == 2 && gameIsActive) {

            gameState[tappedView] = activePlayer;
            ivView.setTranslationY(-2000f);

            if (activePlayer == 0) {
                ivView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                ivView.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            ivView.animate().translationYBy(2000f).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    //Someone has won!

                    gameIsActive = false;
                    if (gameState[winningPosition[0]] == 0) {
                        winnerMessage.setText("Winner is the yellow player");

                    } else {
                        winnerMessage.setText("Winner is the red player");
                    }



                    llReset.setVisibility(View.VISIBLE);
                }
            }
        } else if(drawCheck() && gameIsActive) {
            winnerMessage.setText("No one won, play Again!");
            llReset.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btnReset)
    public void onViewClicked() {
        llReset.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        gameIsActive = true;

        //reseting
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        //resetting the images
        for (int i = 0; i < glboard.getChildCount(); i++){
            //0 to set to empty image
            ((ImageView) glboard.getChildAt(i)).setImageResource(0);
        }

    }

    public boolean drawCheck(){
        for (int state : gameState) {
            if (gameState[state] == 2){return false;}
        }
        return true;
    }
}
