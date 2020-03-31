package com.example.doggoquiz;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtSubtitle;
    private Button btnStart;
    private Button btnOption0;
    private Button btnOption1;
    private ImageView imgDog;
    private String[] dogs = {"golden", "rottweiler", "boxer", "bernese", "collie", "husky", "aussie", "akita", "labrador", "samoyed"};
    private int dogCounter = 0;
    private int score = 0;
    private String userAnswer;
    private String dogName;
    private String randomDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupListeners();
    }

    private void setupViews() {
        txtTitle = findViewById(R.id.txt_title);
        txtSubtitle = findViewById(R.id.txt_subtitle);
        btnStart = findViewById(R.id.btn_start);
        btnOption0 = findViewById(R.id.btn_option0);
        btnOption1 = findViewById(R.id.btn_option1);
        imgDog = findViewById(R.id.img_dog);
    }

    private void setupListeners() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnOption0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = btnOption0.getText().toString();
                processScore();
                changeDogImage();
                randomizeButtons();
            }
        });

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = btnOption1.getText().toString();
                processScore();
                changeDogImage();
                randomizeButtons();
            }
        });
    }

    private void startGame() {
        setupGameUI();
        changeDogImage();
        randomizeButtons();
    }

    private void setupGameUI() {
        btnOption0.setVisibility(View.VISIBLE);
        btnOption1.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTitle.setText(R.string.title_in_game);
        txtSubtitle.setText(R.string.subtitle_in_game);
    }

    private void changeDogImage() {
        if (dogCounter < 10) {
            dogName = dogs[dogCounter];
            Context context = imgDog.getContext();
            int id = context.getResources().getIdentifier(dogName, "drawable", context.getPackageName());
            imgDog.setImageResource(id);
            dogCounter += 1;
        } else
            endGame();
    }

    private void processScore() {
        Toast toast = Toast.makeText(this, "Toast Message", Toast.LENGTH_LONG);
        if (userAnswer == dogName) {
            score += 1;
            toast.setText("Yes, that's right!");
        } else {
            toast.setText("No, you're wrong!");
        }
        toast.show();
    }

    private void endGame() {
        btnOption0.setVisibility(View.INVISIBLE);
        btnOption1.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        imgDog.setImageResource(R.drawable.dogs);
        txtTitle.setText("Nice one! You scored " + score + " points!");
        txtSubtitle.setText(R.string.subtitle_end_game);
        btnStart.setText(R.string.button_end_game);
        score = 0;
        dogCounter = 0;
    }

    private void randomizeButtons() {
        do {
            int randomDogInt = new Random().nextInt(dogs.length);
            randomDog = dogs[randomDogInt];
        } while (randomDog == dogName);

        int randomButton = new Random().nextInt(2);

        switch (randomButton) {
            case 0:
                btnOption0.setText(dogName);
                btnOption1.setText(randomDog);
                break;
            case 1:
                btnOption1.setText(dogName);
                btnOption0.setText(randomDog);
                break;
        }
    }
}
