package com.its.samplelearning;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.its.samplelearning.databinding.ActivitySignUpBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding objUiBinding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Calendar calendar;
    private int month, year, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objUiBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        Utility.setToolbar(this, getString(R.string.lbl_sign_up));
        sharedPreferences = getApplicationContext().getSharedPreferences(Utility.SHARED_PREF, MODE_PRIVATE);
        calendar = Calendar.getInstance();
        objUiBinding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (!objUiBinding.checkBox.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Please agree to Terms & Conditions", Toast.LENGTH_SHORT).show();
                    } else {
                        setData();
                    }
                }
            }
        });

        objUiBinding.edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }


    private void showDatePicker() {
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDataCal = Calendar.getInstance();
                selectedDataCal.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat(Utility.DEFAULT_DATE_FORMAT, Locale.ENGLISH);
                String formattedDate = dateFormat.format(selectedDataCal.getTimeInMillis());
                objUiBinding.edtDob.setText(formattedDate);
                calendar = selectedDataCal;
            }
        }, year, month, day);
        Calendar minCal = Calendar.getInstance();
        minCal.add(Calendar.DAY_OF_MONTH,0);
        datePickerDialog.getDatePicker().setMinDate(minCal.getTimeInMillis());
        datePickerDialog.show();
    }

    private void setData() {
        editor = sharedPreferences.edit();
        editor.putString(Utility.SHARED_PREF_USERNAME, objUiBinding.edtUsername.getText().toString());
        editor.putString(Utility.SHARED_PREF_LASTNAME, objUiBinding.editLastname.getText().toString());
        editor.putString(Utility.SHARED_PREF_DESIGNATION, objUiBinding.edtDesignation.getText().toString());
        editor.putString(Utility.SHARED_PREF_LOCATION, objUiBinding.editLocation.getText().toString());
        editor.putString(Utility.SHARED_PREF_ABOUT_ME, objUiBinding.editAboutMe.getText().toString());
        editor.putString(Utility.SHARED_PREF_PASSWORD, objUiBinding.editPassword.getText().toString());
        editor.putBoolean(Utility.SHARED_PREF_ALREADY_LOGGED_IN, true);
        editor.apply();
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private boolean validate() {
        boolean status = true;
        String username = objUiBinding.edtUsername.getText().toString().trim();
        String lastname = objUiBinding.editLastname.getText().toString().trim();
        String designation = objUiBinding.edtDesignation.getText().toString().trim();
        String location = objUiBinding.editLocation.getText().toString().trim();
        String aboutMe = objUiBinding.editAboutMe.getText().toString().trim();
        String password = objUiBinding.editPassword.getText().toString().trim();
        String retypePassword = objUiBinding.editRetypePassword.getText().toString().trim();

        if (username.isEmpty()) {
            status = false;
            objUiBinding.edtUsername.setError("Please enter FirstName");
            objUiBinding.edtUsername.requestFocus();
            return status;
        }

        if (lastname.isEmpty()) {
            status = false;
            objUiBinding.editLastname.setError("Please enter LastName");
            objUiBinding.editLastname.requestFocus();
            return status;
        }
        if (designation.isEmpty()) {
            status = false;
            objUiBinding.edtDesignation.setError("Please enter Designation");
            objUiBinding.edtDesignation.requestFocus();
            return status;
        }

        if (location.isEmpty()) {
            status = false;
            objUiBinding.editLocation.setError("Please enter Location");
            objUiBinding.editLocation.requestFocus();
            return status;
        }

        if (aboutMe.isEmpty()) {
            status = false;
            objUiBinding.editAboutMe.setError("Please enter About Me");
            objUiBinding.editAboutMe.requestFocus();
            return status;
        }

        if (password.isEmpty()) {
            objUiBinding.editPassword.setError("Please enter Password");
            objUiBinding.editPassword.requestFocus();
            status = false;
            return status;
        } else {
            if (retypePassword.isEmpty()) {
                objUiBinding.editPassword.setError("Please Retype your Password");
                objUiBinding.editPassword.requestFocus();
                status = false;
                return status;
            } else if (!password.equals(retypePassword)) {
                objUiBinding.editRetypePassword.setError("Retype your Password do not match");
                status = false;
                objUiBinding.editRetypePassword.requestFocus();
                return status;
            }
        }
        return status;
    }
}
