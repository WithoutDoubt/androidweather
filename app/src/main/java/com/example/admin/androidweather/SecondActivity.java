package com.example.admin.androidweather;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.admin.androidweather.YuFragment.FragmentShiro;
import com.example.admin.androidweather.db.City;
import com.example.admin.androidweather.db.Province;
import com.example.admin.androidweather.gson.UserGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

public class SecondActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private String rolename;

    private MyFragment fg1,fg3;
    private FragmentShiro fg2;
    private FragmentManager fManager;
    //


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.second_layout);

        //String role = getIntent().getStringExtra("role");

       // roleName = getIntent().getStringExtra("role");

        fManager = getFragmentManager();
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        rb_channel = (RadioButton)findViewById(R.id.rb_channel);
        rb_channel.setChecked(true);


    }

    @Override
    public void  onCheckedChanged(RadioGroup group,int checkedId){
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_channel:
                if (fg1 == null){
                    fg1 = new MyFragment();
                    fTransaction.add(R.id.ly_content,fg1);
                }
                else{
                    fTransaction.show(fg1);
                    }
                break;
            case R.id.rb_message:
                if (fg2 == null){
                    fg2 = new FragmentShiro();
                    fTransaction.add(R.id.ly_content,fg2);
                }
                else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.rb_setting:
                break;
         }
         fTransaction.commit();
    }
    private void  hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }


}
