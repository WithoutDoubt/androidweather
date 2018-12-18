package com.example.admin.androidweather.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.YuFragment.FragmentResultRebar;

public class ResultScanActivity extends AppCompatActivity{
    private  String componentId ;
    private  String product;
    private FragmentResultRebar fg_reber;
    private FragmentResultRebar rebarFragment;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("aaa", "onCreate:hello ");
        componentId = getIntent().getStringExtra("componentId");
        product = getIntent().getStringExtra("product");

        setContentView(R.layout.result_scan_layout);
        FragmentTransaction fTransaction = fManager.beginTransaction();
        switch (product){
            case "Rebar":
                fg_reber = new FragmentResultRebar();
                hideAllFragment(fTransaction);
                fTransaction.add(R.id.product_content,fg_reber);

                break;


            default:
                break;
        }

    }

    private void  hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg_reber != null)fragmentTransaction.hide(fg_reber);

    }

}
