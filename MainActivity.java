package com.example.sql;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText Name, Mark, Surname;
    TextView DataV;
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        Name = findViewById(R.id.ed1);
        Surname = findViewById(R.id.ed2);
        Mark = findViewById(R.id.ed3);
        DataV = findViewById(R.id.textView);

        // Initialize the database
        database = new MyDatabase(this);
    }

    public void SaveDat(View view) {
        String userVal = Name.getText().toString().trim();
        String surnameVal = Surname.getText().toString().trim();
        String markVal = Mark.getText().toString().trim();

        // Check if fields are empty
        if (userVal.isEmpty() || surnameVal.isEmpty() || markVal.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Integer passVal = Integer.parseInt(markVal);
            Boolean result = database.insertdata(userVal, passVal, surnameVal);
            if (result) {
                Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data insertion failed", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid marks input", Toast.LENGTH_SHORT).show();
        }
    }

    public void read(View view) {
        Cursor res = database.getAllData();
        StringBuilder stringBuffer = new StringBuilder();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("Id: ").append(res.getString(0)).append("\n");
                stringBuffer.append("Name: ").append(res.getString(1)).append("\n");
                stringBuffer.append("Surname: ").append(res.getString(2)).append("\n");
                stringBuffer.append("Marks: ").append(res.getString(3)).append("\n\n");
            }
            DataV.setText(stringBuffer.toString());
        } else {
            DataV.setText("");
            Toast.makeText(getApplicationContext(), "No data retrieved", Toast.LENGTH_SHORT).show();
        }
        res.close(); // Close the cursor when done
    }

    public void update(View view) {
        String userVal = Name.getText().toString().trim();
        String surnameVal = Surname.getText().toString().trim();
        String markVal = Mark.getText().toString().trim();

        // Check if fields are empty
        if (userVal.isEmpty() || surnameVal.isEmpty() || markVal.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Integer passVal = Integer.parseInt(markVal);
            Boolean result = database.updateData(userVal, passVal, surnameVal);
            if (result) {
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No rows affected", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid marks input", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        String userVal = Name.getText().toString().trim();
        if (userVal.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a name to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        int result = database.deletedata(userVal);
        if (result > 0) {
            Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No data found for deletion", Toast.LENGTH_SHORT).show();
        }
    }
}