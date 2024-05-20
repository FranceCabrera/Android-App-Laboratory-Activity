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

public class addContact extends AppCompatActivity {
    private EditText etName, etContactNumber, etEmail, etAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etName = findViewById(R.id.etName);
        etContactNumber = findViewById(R.id.etContactNumber);
        etEmail = findViewById(R.id.etEmail);
        etAge = findViewById(R.id.etAge);

        findViewById(R.id.btnSubmitContact).setOnClickListener(v -> addContact());
        findViewById(R.id.btnBackContact).setOnClickListener(v -> finish());
    }
    private void addContact() {
        String name = etName.getText().toString();
        String contactNumber = etContactNumber.getText().toString();
        String email = etEmail.getText().toString();
        String ageStr = etAge.getText().toString();

        if (name.isEmpty() || contactNumber.isEmpty() || email.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            if (age < 0 || age > 120) {
                Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                return;
            }

            String contact = "Name: " + name + "\nContact Number: " + contactNumber + "\nEmail: " + email + "\nAge: " + age;
            Intent intent = new Intent();
            intent.putExtra("contact", contact);
            setResult(RESULT_OK, intent);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
        }
    }
}