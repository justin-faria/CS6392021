package com.example.literstogallons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView, textView2, Gallons;
    Button enter_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        Gallons = findViewById(R.id.Gallons);
        editText = findViewById(R.id.editTextNumber);
        enter_btn = findViewById(R.id.enter_btn);
        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                literTogallon();
            }
        });
   
    }
    private void literTogallon() {
        try {
            String valueEntered = editText.getText().toString();

            double valueConverted = Double.parseDouble(valueEntered);

            double gal = valueConverted / 3.785;

            Gallons.setText(" " + gal);
        }catch(NumberFormatException e){
           e.printStackTrace();
        }

    }
}