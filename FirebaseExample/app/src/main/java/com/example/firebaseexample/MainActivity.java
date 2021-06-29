package com.example.firebaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView employees;
    private EditText firstname;
    private EditText lastname;
    private ScrollView scrollView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("employees");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employees = findViewById(R.id.employee);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        scrollView = findViewById(R.id.scrollView);


        myRef.addValueEventListener(new ValueEventListener() {
            Employee emp;
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                StringBuilder employeeList = new StringBuilder();
                int counter = 1;
                for (DataSnapshot s : snapshot.getChildren())
                {
                    emp = (Employee)s.getValue(Employee.class);
                    employeeList.append("Employee #" + counter + ":\n"+"First name: " + emp.getFirstName()
                            + "\nLast name: " + emp.getLastName() + "\n\n");
                    counter += 1;
                }
                employees.setText(employeeList);
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("MAINACTIVITY", "Database read failed!", error.toException());
            }
        });
    }


    public void addEmployee(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String firstName = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();

        if(firstName.trim().isEmpty() || lastName.trim().isEmpty()){

            Toast toast = Toast.makeText(this, "Please Enter Employees Full Name",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{

            Employee emp = new Employee(lastName, firstName);
            myRef.push().setValue(emp);
            Toast toast = Toast.makeText(this, "Employee added!",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
