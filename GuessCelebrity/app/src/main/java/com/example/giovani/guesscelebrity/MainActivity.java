package com.example.giovani.guesscelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView ivCeleb;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;

    ArrayList<String> celebURLs;
    ArrayList<String> celebNames;
    Pattern p;
    Matcher m;
    Random rand;
    int chosenCeleb, locationsOfCorrectAnswer, incorrectAnswerLocation;
    String[] answers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        inicializar();
        getData();
        createNewQuestion();

    }

    private void inicializar() {
        celebNames = new ArrayList<>();
        celebURLs = new ArrayList<>();
        answers = new String[4];
        rand = new Random();
        chosenCeleb = 0;
        locationsOfCorrectAnswer = 0;
        incorrectAnswerLocation = 0;
        ivCeleb.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void findViews() {
        ivCeleb = (ImageView) findViewById(R.id.ivImage);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
    }

    public void guessCeleb(View view){
        if (view.getTag().toString().equals(Integer.toString(locationsOfCorrectAnswer))){
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
            createNewQuestion();

        }else{
            Toast.makeText(this, "Wrong! It was " + celebNames.get(chosenCeleb), Toast.LENGTH_LONG).show();
            createNewQuestion();
        }

    }

    public void getData(){
        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult = result.split("<div class=\"sidebarInnerContainer\">");

            p = Pattern.compile("img src=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()){
                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()){
                celebNames.add(m.group(1));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void createNewQuestion(){
        chosenCeleb = rand.nextInt(celebURLs.size());
        locationsOfCorrectAnswer = rand.nextInt(4);

        ImageDownloader imageTask = new ImageDownloader();
        Bitmap celebImage;

        try {
            celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();

            for (int i = 0 ; i < 4; i++){
                if (i == locationsOfCorrectAnswer){
                    answers[i] = celebNames.get(chosenCeleb);
                }else{
                    incorrectAnswerLocation = rand.nextInt(celebURLs.size());
                    while (incorrectAnswerLocation == chosenCeleb){
                        incorrectAnswerLocation = rand.nextInt(celebURLs.size());
                    }
                    answers[i] =  celebNames.get(incorrectAnswerLocation);
                }
            }

            btn0.setText(answers[0]);
            btn1.setText(answers[1]);
            btn2.setText(answers[2]);
            btn3.setText(answers[3]);

            ivCeleb.setImageBitmap(celebImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
