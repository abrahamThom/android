package com.example.formdesign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText dobEditText;
    private RadioGroup genderRadioGroup;

    // Define SharedPreferences key
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
        setContentView(R.layout.activity_main);

        firstNameEditText = findViewById(R.id.fname);
        lastNameEditText = findViewById(R.id.lname);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phn);
        passwordEditText = findViewById(R.id.pass);
        dobEditText = findViewById(R.id.dob);
        genderRadioGroup = findViewById(R.id.radioGroup3);
    }

    public void next(View view) {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String dob = dobEditText.getText().toString().trim();

        // Check for empty fields
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate first name and last name
        if (!isValidName(firstName) || !isValidName(lastName)) {
            Toast.makeText(this, "Invalid first name or last name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate phone number
        if (!isValidPhoneNumber(phone)) {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate password
        if (!isValidPassword(password)) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate date of birth
        if (!isValidDOB(dob)) {
            Toast.makeText(this, "Invalid date of birth. Format should be yyyy-mm-dd", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check gender selection
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";

        if (gender.isEmpty()) {
            Toast.makeText(this, "Select a gender", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.putString(KEY_LAST_NAME, lastName);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_GENDER, gender);
        editor.apply(); // Save changes asynchronously

        // Proceed with intent
        Toast.makeText(this, "Registration Success.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    // Email validation method
    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Phone number validation method
    private boolean isValidPhoneNumber(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        String phoneString = phone.toString();
        String cleanedPhone = phoneString.replaceAll("\\D", "");


        return cleanedPhone.length() >= 10;
    }

    // Name validation method
    private boolean isValidName(CharSequence name) {
        return !TextUtils.isEmpty(name) && name.toString().matches("[a-zA-Z]+([ '-][a-zA-Z]+)*");
    }

    // Password validation method
    private boolean isValidPassword(CharSequence password) {
        return password.length() >= 6; // Example requirement for minimum length
    }

    // Date of Birth validation method
    private boolean isValidDOB(CharSequence dob) {
        // Example validation for yyyy-mm-dd format
        return dob.toString().matches("\\d{2}-\\d{2}-\\d{4}");
    }
}
