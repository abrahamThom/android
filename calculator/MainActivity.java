package com.example.mcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText n1,n2;
    double number1,number2;

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txt3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        n1 = findViewById(R.id.txt3);
        n2 = findViewById(R.id.txt4);
        t= findViewById(R.id.tv);
    }
    private boolean validateInput(){
        String num1=n1.getText().toString();
        String num2=n2.getText().toString();
        if(num1.isEmpty() || num2.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        try{
            Double.parseDouble(num1);
            Double.parseDouble(num2);
        }
        catch(NumberFormatException e){
            Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    public void add(View view) {
        if (validateInput()) {
            number1 = Integer.parseInt(n1.getText().toString());
            number2 = Integer.parseInt(n2.getText().toString());
            double sum = number1 + number2;
            t.setText(Double.toString(sum));

        }
    }

    public void sub(View view) {
        if (validateInput()) {
            number1 = Integer.parseInt(n1.getText().toString());
            number2 = Integer.parseInt(n2.getText().toString());
            double subtract = number1 - number2;
            t.setText(Double.toString(subtract));

        }
    }
    public void mul(View view) {
        if (validateInput()) {
            number1 = Integer.parseInt(n1.getText().toString());
            number2 =Integer.parseInt(n2.getText().toString());
            double multiplication = number1 * number2;
            t.setText(Double.toString(multiplication));
        }
    }

    public void div(View view) {
        if(validateInput()){
            number1= Integer.parseInt(n1.getText().toString());
            number2 = Integer.parseInt(n2.getText().toString());
            if (number2!= 0) {
                double result = number1/ number2;
                t.setText(String.valueOf(result));
            } else {
                // Handle division by zero error
                t.setText("Error: Division by zero");
            }
        }
    }
}
