package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class computeAverage extends AppCompatActivity {
    private EditText etMath, etFilipino, etScience, etMapeh, etEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compute_average);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etMath = findViewById(R.id.etMath);
        etFilipino = findViewById(R.id.etFilipino);
        etScience = findViewById(R.id.etScience);
        etMapeh = findViewById(R.id.etMapeh);
        etEnglish = findViewById(R.id.etEnglish);

        findViewById(R.id.btnSubmitAverage).setOnClickListener(v -> computeAverage());
        findViewById(R.id.btnBackAverage).setOnClickListener(v -> finish());
    }
    private void computeAverage() {
        String mathStr = etMath.getText().toString();
        String filipinoStr = etFilipino.getText().toString();
        String scienceStr = etScience.getText().toString();
        String mapehStr = etMapeh.getText().toString();
        String englishStr = etEnglish.getText().toString();

        if (mathStr.isEmpty() || filipinoStr.isEmpty() || scienceStr.isEmpty() || mapehStr.isEmpty() || englishStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double math = Double.parseDouble(mathStr);
            double filipino = Double.parseDouble(filipinoStr);
            double science = Double.parseDouble(scienceStr);
            double mapeh = Double.parseDouble(mapehStr);
            double english = Double.parseDouble(englishStr);

            double average = (math + filipino + science + mapeh + english) / 5;

            Intent intent = new Intent(computeAverage.this, averageResult.class);
            intent.putExtra("average", average);
            startActivity(intent);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}