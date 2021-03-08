package com.its.samplelearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = validate();
                if (isValid) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
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