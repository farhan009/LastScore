package com.example.lastscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView scoreTextView;
    private Button increase_Button, decrease_Button;
    private  int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.score_TextView_id);
        increase_Button = findViewById(R.id.increase_Button_id);
        decrease_Button = findViewById(R.id.decrease_Button_id);

        if (loadScore() != 0){
            scoreTextView.setText("Score : " + loadScore());
        }

        increase_Button.setOnClickListener(this);
        decrease_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.increase_Button_id){

            score = score + 10;
            scoreTextView.setText("Score : " + score);
            saveScore(score);

        }
        else if (v.getId() == R.id.decrease_Button_id){

            score = score - 10;
            scoreTextView.setText("Score : " + score);
            saveScore(score);

        }

    }

    private void saveScore(int score){

        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore", score);
        editor.commit();
    }

    private int loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore", 0);

        return score;
    }
}
