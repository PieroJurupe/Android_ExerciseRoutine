package com.example.gymapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_NAME = 1;
    Button button1, button2, addNameButton;
    LinearLayout mainLayout;
    ArrayList<String> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        exercises = new ArrayList<>();
        Log.d("MainActivity", "xd");
        button1 = findViewById(R.id.start1);
        addNameButton = findViewById(R.id.addNameButton);
        mainLayout = findViewById(R.id.main);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRoutineActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NAME);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NAME && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String exerciseType = data.getStringExtra("exerciseType");
            String duration = data.getStringExtra("duration");
            int exerciseImage = data.getIntExtra("exerciseImage", R.drawable.exercise_1);
            exercises = data.getStringArrayListExtra("exercises");
            addNewLinearLayout(name, exerciseType, duration, exerciseImage,exercises);
        }
    }

    private void addNewLinearLayout(String name, String exerciseType, String duration, int exerciseImage, ArrayList<String> exercises) {
        Log.d("MainActivity", "Ejercicios: " + exercises);
        LinearLayout outerLayout = new LinearLayout(this);
        outerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        outerLayout.setOrientation(LinearLayout.VERTICAL);
        outerLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        outerLayout.setPadding(20, 0, 20, 0);
        LinearLayout.LayoutParams outerLayoutParams = (LinearLayout.LayoutParams) outerLayout.getLayoutParams();
        outerLayoutParams.setMargins(20, 15, 20, 0);
        outerLayout.setLayoutParams(outerLayoutParams);

        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                400));
        innerLayout.setOrientation(LinearLayout.HORIZONTAL);
        innerLayout.setGravity(Gravity.CENTER_VERTICAL);
        innerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        LinearLayout textLayout = new LinearLayout(this);
        textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        textLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        textView1.setText(name);
        textView1.setTextSize(20);
        textView1.setPadding(5, 5, 5, 5);
        textView1.setTextColor(Color.parseColor("#151515"));
        textView1.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);

        TextView textView2 = new TextView(this);
        textView2.setText(exerciseType);
        textView2.setTextSize(15);
        textView2.setPadding(5, 5, 5, 5);
        textView2.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView textView3 = new TextView(this);
        textView3.setText("Realizar " + duration + " veces a la semana");
        textView3.setTextSize(15);
        textView3.setPadding(5, 5, 5, 5);
        textView3.setTextColor(Color.parseColor("#A91D3A"));
        textView3.setTypeface(Typeface.DEFAULT_BOLD);

        textLayout.addView(textView1);
        textLayout.addView(textView2);
        textLayout.addView(textView3);

        LinearLayout gifAndArrowLayout = new LinearLayout(this);
        gifAndArrowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        gifAndArrowLayout.setOrientation(LinearLayout.HORIZONTAL);
        gifAndArrowLayout.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

        GifImageView gifImageView = new GifImageView(this);
        gifImageView.setLayoutParams(new LinearLayout.LayoutParams(
                270,
                270));
        gifImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        gifImageView.setImageResource(exerciseImage);

        ImageView arrowImageView = new ImageView(this);
        arrowImageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        arrowImageView.setPadding(10, 10, 15, 10);
        arrowImageView.setImageResource(R.drawable.baseline_keyboard_arrow_right_24);

        gifAndArrowLayout.addView(gifImageView);
        gifAndArrowLayout.addView(arrowImageView);

        innerLayout.addView(textLayout);
        innerLayout.addView(gifAndArrowLayout);

        outerLayout.addView(innerLayout);

        Button startButton = new Button(this);
        startButton.setId(View.generateViewId());
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, 0, 0, 20);
        startButton.setLayoutParams(buttonParams);
        startButton.setText(R.string.comenzar);
        startButton.setBackgroundResource(R.drawable.btn);
        startButton.setTextColor(Color.parseColor("#EEEEEE"));
        startButton.setAllCaps(true);
        startButton.setPadding(10, 10, 10, 10);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Ejercicios: " + exercises);
                Log.d("MainActivity2", "Ejercicios: " + name);

                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("name", name);
                intent.putStringArrayListExtra("exercises", exercises);
                startActivity(intent);
            }
        });

        outerLayout.addView(startButton);

        LinearLayout scrollViewLayout = findViewById(R.id.scrollViewLayout);
        scrollViewLayout.addView(outerLayout);

       for (int i = 0; i < exercises.size(); i++) {
            LinearLayout exerciseLayout = new LinearLayout(this);
            exerciseLayout.setId(i);
        }
    }

    public void paraManana(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

    }

}