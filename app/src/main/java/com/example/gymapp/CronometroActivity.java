package com.example.gymapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import pl.droidsonroids.gif.GifImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CronometroActivity extends AppCompatActivity {
    private GifImageView exerciseGifView;
    private TextView exerciseTextView;
    private TextView exerciseTimeView;
    private Handler handler = new Handler();
    private int currentExerciseIndex = 0;
    private ArrayList<String> exercises;
    private static final long EXERCISE_DURATION = 5000; // 5 seconds
    private HashMap<String, String> exerciseNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        Toolbar toolbar = findViewById(R.id.toolbarcron);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        exerciseGifView = findViewById(R.id.exercise_gif_view);
        exerciseTextView = findViewById(R.id.exercise_text_view);
        exerciseTimeView = findViewById(R.id.exercise_time_view);

        exercises = getIntent().getStringArrayListExtra("exercises");
        initializeExerciseNames();
        if (exercises != null && !exercises.isEmpty()) {
            displayNextExercise();
        }
    }

    private void initializeExerciseNames() {
        exerciseNames = new HashMap<>();
        exerciseNames.put("exercise_1", "MONTAÑEROS");
        exerciseNames.put("exercise_2", "ABDOMINALES");
        exerciseNames.put("exercise_3", "FONDOS");
        exerciseNames.put("exercise_4", "ABS. BICICLETA");
        exerciseNames.put("exercise_5", "ELEVACIONES");
    }

    private void displayNextExercise() {
        if (exercises != null && !exercises.isEmpty()) {
            String exerciseKey = "exercise_" + (currentExerciseIndex + 1);
            int gifResourceId = getResources().getIdentifier(exerciseKey, "drawable", getPackageName());
            exerciseGifView.setImageResource(gifResourceId);
            exerciseTextView.setText(exerciseNames.get(exerciseKey));

            new CountDownTimer(EXERCISE_DURATION, 1000 + 1) {
                public void onTick(long millisUntilFinished) {
                    exerciseTimeView.setText(String.format("%d seconds", millisUntilFinished / 1000 + 1));
                }

                public void onFinish() {
                    if (currentExerciseIndex < exercises.size() - 1) {
                        currentExerciseIndex++;
                        displayNextExercise();
                    } else {
                        exerciseTimeView.setText("¡LO LOGRASTE!");
                    }
                }
            }.start();
        }
    }
}
