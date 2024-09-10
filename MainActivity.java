package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Login(View view) {
        EditText username1 = findViewById(R.id.et1);
        EditText password1 = findViewById(R.id.pass);

        String username=username1.getText().toString();
        String password=password1.getText().toString();

        String u="admin";
        String p="admin";

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please fill all the fields.", Toast.LENGTH_SHORT).show();
        } else if (username.equals(u) && password.equals(p)) {
            Toast.makeText(this, "Login succesfull.", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
        }
    }
}