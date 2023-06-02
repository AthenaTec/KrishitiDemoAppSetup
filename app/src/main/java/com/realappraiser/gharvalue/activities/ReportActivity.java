package com.realappraiser.gharvalue.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.realappraiser.gharvalue.R;

@SuppressWarnings("ALL")
public class ReportActivity extends AppCompatActivity {

    TextView error;
    private String versionCode;
    private String result = "NA";
    private String deviceinfo = "NA";
    String userID;
    boolean is_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent i = getIntent();
        result = i.getStringExtra("error");
        deviceinfo = i.getStringExtra("device_info");
        versionCode = i.getStringExtra("versionCode");

//        alert_report();

        Toast.makeText(this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);



    }

}

