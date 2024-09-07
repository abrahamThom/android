package com.example.formdesign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String firstName = sharedPreferences.getString(KEY_FIRST_NAME, "N/A");
        String lastName = sharedPreferences.getString(KEY_LAST_NAME, "N/A");
        String email = sharedPreferences.getString(KEY_EMAIL, "N/A");
        String phone = sharedPreferences.getString(KEY_PHONE, "N/A");
        String password = sharedPreferences.getString(KEY_PASSWORD, "N/A");
        String dob = sharedPreferences.getString(KEY_DOB, "N/A");
        String gender = sharedPreferences.getString(KEY_GENDER, "N/A");

        // Find TextView to display the data
        TextView txtDetails = findViewById(R.id.d1);

        // Display data
        String details = "First Name: " + firstName + "\n\n" +
                "Last Name: " + lastName + "\n\n" +
                "Gender: " + gender + "\n\n" +
                "Email: " + email + "\n\n" +
                "Phone: " + phone + "\n\n" +
                "Password: " + "********" + "\n\n" + // Mask password
                "Date of Birth: " + dob;

        txtDetails.setText(details);
    }

    public void back(View view) {
        Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();
        Intent bintent = new Intent(MainActivity2.this, MainActivity.class);
        finish();
    }
}
