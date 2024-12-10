package com.example.gymapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        String name = getIntent().getStringExtra("name");
        if (name != null) {
            TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(name);
        }

        ArrayList<String> exercises = getIntent().getStringArrayListExtra("exercises");
        if (exercises != null) {
            LinearLayout layoutExercises = findViewById(R.id.layoutExercises);
            for (String exercise : exercises) {
                LinearLayout exerciseLayout = new LinearLayout(this);
                exerciseLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        100));
                exerciseLayout.setGravity(android.view.Gravity.CENTER);
                exerciseLayout.setOrientation(LinearLayout.VERTICAL);

                LinearLayout innerLayout = new LinearLayout(this);
                innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                innerLayout.setOrientation(LinearLayout.HORIZONTAL);
                innerLayout.setBackgroundColor(android.graphics.Color.parseColor("#EEEEEE"));
                innerLayout.setOnClickListener(v -> Imagebuttonclicked());

                LinearLayout textLayout = new LinearLayout(this);
                textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                textLayout.setOrientation(LinearLayout.VERTICAL);

                TextView textView1 = new TextView(this);
                textView1.setText(exercise);
                textView1.setTextSize(15);
                textView1.setTextColor(android.graphics.Color.parseColor("#151515"));
                textView1.setTypeface(android.graphics.Typeface.MONOSPACE, android.graphics.Typeface.BOLD);

                TextView textView2 = new TextView(this);
                textView2.setText("Repetir cada mañana");
                textView2.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);

                TextView textView3 = new TextView(this);
                textView3.setText("1 MIN");
                textView3.setTextColor(android.graphics.Color.parseColor("#C73659"));
                textView3.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);

                textLayout.addView(textView1);
                textLayout.addView(textView2);
                textLayout.addView(textView3);

                LinearLayout gifLayout = new LinearLayout(this);
                gifLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));

                GifImageView gifImageView = new GifImageView(this);
                gifImageView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                gifImageView.setImageResource(R.drawable.exercise_1);

                gifLayout.addView(gifImageView);

                LinearLayout arrowLayout = new LinearLayout(this);
                arrowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                arrowLayout.setGravity(android.view.Gravity.CENTER);

                ImageView arrowImageView = new ImageView(this);
                arrowImageView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                arrowImageView.setImageResource(R.drawable.baseline_keyboard_arrow_right_24);

                arrowLayout.addView(arrowImageView);

                innerLayout.addView(textLayout);
                innerLayout.addView(gifLayout);
                innerLayout.addView(arrowLayout);

                exerciseLayout.addView(innerLayout);

                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        1));
                tableRow.setBackgroundColor(android.graphics.Color.parseColor("#C73659"));

                layoutExercises.addView(exerciseLayout);
                layoutExercises.addView(tableRow);
            }
        } else {
            Log.d("NewActivity", "No llegaron");
        }
    }
    public void Imagebuttonclicked() {
    }
}
