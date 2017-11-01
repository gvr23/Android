package com.example.giovani.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    GridLayout grid;

/*
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        counter.setTranslationY(-2000f);
        counter.setImageResource(R.drawable.yellow);
        counter.animate().translationYBy(2000f).setDuration(300);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        eventos();
    }

    private void findViews() {
        grid = (GridLayout) findViewById(R.id.glboard);

    }

    private void eventos() {
        int childCount = grid.getChildCount();
        for (int i = 0; i < childCount; i++){
            RelativeLayout container = (RelativeLayout) grid.getChildAt(i);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setTranslationY(-2000f);
                    v.animate().translationYBy(2000f).setDuration(300);
                }
            });
        }
    }
}
