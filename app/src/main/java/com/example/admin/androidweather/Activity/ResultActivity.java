package com.example.admin.androidweather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.admin.androidweather.R;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfcheck_result_layout);

        Button button1 = (Button)findViewById(R.id.selfcheck_ok);
        Button button2 = (Button)findViewById(R.id.selfcheck_no);



    }
}
