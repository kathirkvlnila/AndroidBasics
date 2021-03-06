package com.its.samplelearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTxtName, mTxtDesignation, mTxtLocation, mTxtDescription;
    private ImageButton mBtnFb, mBtnTw, mBtnIn, mBtnAdd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPreferences = getApplicationContext().getSharedPreferences(Utility.SHARED_PREF, MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTxtName = findViewById(R.id.txt_name);
        mTxtDesignation = findViewById(R.id.txt_designation);
        mTxtLocation = findViewById(R.id.txt_location);
        mTxtDescription = findViewById(R.id.txt_description);
        mBtnFb = findViewById(R.id.imgFb);
        mBtnTw = findViewById(R.id.imgTw);
        mBtnIn = findViewById(R.id.imgIn);
        mBtnAdd = findViewById(R.id.img_add);
        initView();
        setListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logout) {
            String msg = "Are you sure you want to Logout?";
            showDialog(msg, new ExitDialogListener() {
                @Override
                public void onCancelClicked(DialogInterface dialog) {
                    dialog.dismiss();
                }

                @Override
                public void onProceedClicked() {
                    editor = sharedPreferences.edit();
                    editor.putBoolean(Utility.SHARED_PREF_ALREADY_LOGGED_IN, false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            });
            return true;
        }

        return false;
    }


    @Override
    public void onBackPressed() {
        String msg = "Are you sure you want to EXIT?";
        showDialog(msg, new ExitDialogListener() {
            @Override
            public void onCancelClicked(DialogInterface dialog) {
                dialog.dismiss();
            }

            @Override
            public void onProceedClicked() {
                MainActivity.this.finish();
            }
        });
    }


    private void showDialog(String message, ExitDialogListener exitDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Info");
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitDialogListener.onProceedClicked();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitDialogListener.onCancelClicked(dialog);
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    /**
     * init view to call onCreate
     */
    private void initView() {
        mTxtName.setText("Sachin");
        mTxtDesignation.setText("Android Developer");
        mTxtLocation.setText("Yelagiri Hills");
        mTxtDescription.setText("Multidisciplinary designer who creates character, environments and concepts for Films, Animation");
    }

    /**
     * Set onClick listeners
     */
    private void setListeners() {
        mBtnFb.setOnClickListener(this);
        mBtnTw.setOnClickListener(this);
        mBtnIn.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgFb) {
            Toast.makeText(MainActivity.this, "Fb Clicked", Toast.LENGTH_SHORT).show();
            return;
        }
        if (v.getId() == R.id.imgTw) {
            Toast.makeText(MainActivity.this, "Tw Clicked", Toast.LENGTH_SHORT).show();
            return;
        }
        if (v.getId() == R.id.imgIn) {
            Toast.makeText(MainActivity.this, "In Clicked", Toast.LENGTH_SHORT).show();
            return;
        }
        if (v.getId() == R.id.img_add) {
            Toast.makeText(MainActivity.this, "Add Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
