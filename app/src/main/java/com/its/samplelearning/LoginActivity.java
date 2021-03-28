package com.its.samplelearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private Button mBtnLogin, mBtnSignUp;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences(Utility.SHARED_PREF, MODE_PRIVATE);
        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnSignUp = findViewById(R.id.btn_signup);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = validate();
                if (isValid) {
                    String savedUserName = sharedPreferences.getString(Utility.SHARED_PREF_USERNAME, "");
                    String savedPassword = sharedPreferences.getString(Utility.SHARED_PREF_PASSWORD, "");
                    if (!savedUserName.equals("") && !savedPassword.equals("")) {
                        if (savedUserName.equals(mEdtUsername.getText().toString()) && savedPassword.equals(mEdtPassword.getText().toString())) {
                            editor = sharedPreferences.edit();
                            editor.putString(Utility.SHARED_PREF_USERNAME, mEdtUsername.getText().toString());
                            editor.putString(Utility.SHARED_PREF_PASSWORD, mEdtPassword.getText().toString());
                            editor.putBoolean(Utility.SHARED_PREF_ALREADY_LOGGED_IN, true);
                            editor.apply();
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Username!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "User do not have account. Please Sign up!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SampleRecylerViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validate() {
        boolean status = true;
        String username = mEdtUsername.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();

        if (username.isEmpty()) {
            status = false;
            return status;
        }

        if (password.isEmpty()) {
            status = false;
            return status;
        }
        return status;
    }
}