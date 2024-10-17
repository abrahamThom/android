package com.example.relative;

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
    EditText n, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        n = findViewById(R.id.name);
        p = findViewById(R.id.pass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void submit(View view) {
        String name = n.getText().toString();
        String pass = p.getText().toString();
        if (name.equals("admin") && pass.equals("admin")) 
        {
            Toast.makeText(this, "login successfull", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();

        }
    }
}
