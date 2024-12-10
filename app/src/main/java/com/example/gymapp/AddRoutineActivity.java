package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddRoutineActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextExerciseType;
    EditText editTextDuration;
    Spinner spinnerExerciseImage;
    Button buttonAccept;
    Button buttonAddExercise;
    LinearLayout layoutNewExercises;

    private int[] exerciseImages = {
            R.drawable.exercise_1,
            R.drawable.exercise_2,
            R.drawable.exercise_3,
            R.drawable.exercise_4,
            R.drawable.exercise_5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_routine);

        editTextName = findViewById(R.id.editTextName);
        editTextExerciseType = findViewById(R.id.editTextExerciseType);
        editTextDuration = findViewById(R.id.editTextDuration);
        spinnerExerciseImage = findViewById(R.id.spinnerExerciseImage);
        buttonAccept = findViewById(R.id.buttonAccept);
        buttonAddExercise = findViewById(R.id.buttonAddExercise);
        layoutNewExercises = findViewById(R.id.layoutNewExercises);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercise_images, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExerciseImage.setAdapter(adapter);

        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSpinner();
            }
        });

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String exerciseType = editTextExerciseType.getText().toString();
                String duration = editTextDuration.getText().toString();
                int selectedImage = exerciseImages[spinnerExerciseImage.getSelectedItemPosition()];

                List<String> exercises = new ArrayList<>();
                for (int i = 0; i < layoutNewExercises.getChildCount(); i++) {
                    LinearLayout exerciseLayout = (LinearLayout) layoutNewExercises.getChildAt(i);
                    Spinner exerciseSpinner = (Spinner) exerciseLayout.getChildAt(0);
                    String exercise = exerciseSpinner.getSelectedItem().toString();
                    exercises.add(exercise);
                }
                Log.d("AddRoutineActivity", "Ejercicios: " + exercises);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("exerciseType", exerciseType);
                resultIntent.putExtra("duration", duration);
                resultIntent.putExtra("exerciseImage", selectedImage);
                resultIntent.putStringArrayListExtra("exercises", (ArrayList<String>) exercises);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void addNewSpinner() {
        LinearLayout newLayout = new LinearLayout(this);
        newLayout.setOrientation(LinearLayout.HORIZONTAL);

        Spinner newSpinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercise_images, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newSpinner.setAdapter(adapter);

        EditText newEditText = new EditText(this);
        newEditText.setHint("Contador");
        newEditText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        newEditText.setTextColor(getResources().getColor(R.color.white));
        newEditText.setHintTextColor(getResources().getColor(R.color.white));
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        newLayout.addView(newSpinner, new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        newLayout.addView(newEditText);

        layoutNewExercises.addView(newLayout);
    }
}