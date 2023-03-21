package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegistration extends AppCompatActivity {

    Button Already_Registered, register;
    TextInputLayout fullName_var, username_var, email_var, phoneNo_var, password_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        Already_Registered = (Button) findViewById(R.id.back_to_login);
        register = findViewById(R.id.register);
        fullName_var = findViewById(R.id.full_name);
        username_var = findViewById(R.id.enter_username);
        email_var = findViewById(R.id.email_address);
        phoneNo_var = findViewById(R.id.phn_no);
        password_var = findViewById(R.id.password_field);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName_ = fullName_var.getEditText().getText().toString();
                String username_ = username_var.getEditText().getText().toString();
                String email_ = email_var.getEditText().getText().toString();
                String phnNo_ = phoneNo_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();

                if (!fullName_.isEmpty()) {
                    fullName_var.setError(null);
                    fullName_var.setErrorEnabled(false);
                    if (!username_.isEmpty()) {
                        username_var.setError(null);
                        username_var.setErrorEnabled(false);
                        if (!email_.isEmpty()) {
                            email_var.setError(null);
                            email_var.setErrorEnabled(false);
                            if (!phnNo_.isEmpty()) {
                                phoneNo_var.setError(null);
                                phoneNo_var.setErrorEnabled(false);
                                if (!password_.isEmpty()) {
                                    password_var.setError(null);
                                    password_var.setErrorEnabled(false);
                                    if (email_.matches("[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        reference = firebaseDatabase.getReference("studentdata");

                                        String fullName_s = fullName_var.getEditText().getText().toString();
                                        String username_s = username_var.getEditText().getText().toString();
                                        String email_s = email_var.getEditText().getText().toString();
                                        String phnNo_s = phoneNo_var.getEditText().getText().toString();
                                        String password_s = password_var.getEditText().getText().toString();

                                        storingUserData storingUserDatass = new storingUserData(fullName_s, username_s, email_s, phnNo_s, password_s);

                                        reference.child(username_s).setValue(storingUserDatass);

                                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                                        Intent intend = new Intent(getApplicationContext(), DashboardActivity.class);
                                        startActivity(intend);
                                        finish();


                                    } else {
                                        email_var.setError("Invalid email");
                                    }

                                } else {
                                    password_var.setError("Please enter the password");
                                }

                            } else {
                                phoneNo_var.setError("Please enter the phone no.");
                            }

                        } else {
                            email_var.setError("Please enter the email");
                        }

                    } else {
                        username_var.setError("Please enter the username");
                    }

                } else {
                    fullName_var.setError("Please enter your full name");
                }
            }
        });
        Already_Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserLogin();
            }
        });
    }

    private void openUserLogin() {
        Intent intent = new Intent(this,studentLogin.class);
        startActivity(intent);
        finish();
    }
}
