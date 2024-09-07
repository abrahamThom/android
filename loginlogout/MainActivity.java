package com.example.loginlogout;

import android.os.Bundle;
import android.view.View;
import  android.content.Intent;
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

    public void click(View view) {
        EditText uname=findViewById(R.id.t1);
        EditText pwd=findViewById(R.id.t2);
        String username=uname.getText().toString();
        String password=pwd.getText().toString();
        String u="admin";
        String p="admin";
        if(username.isEmpty()||password.isEmpty()){
            Toast.makeText(MainActivity.this,"please fill all the fields",Toast.LENGTH_SHORT).show();

        } else if (username.equals(u)&&password.equals(p)) {
            Toast.makeText(MainActivity.this,"login successfull",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("user_name", u);
            startActivity(intent);

        }
        else {
            Toast.makeText(MainActivity.this,"invalid credentials",Toast.LENGTH_SHORT).show();

        }
    }
}
