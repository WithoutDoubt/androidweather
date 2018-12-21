package com.example.admin.androidweather;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.androidweather.YuFragment.FragmentShiro;
import com.example.admin.androidweather.YuFragment.MyFragment;
import com.example.admin.androidweather.YuFragment.ScanFragment;

public class SecondActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private String rolename;

    private MyFragment fg1;
    private ScanFragment fg3;
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
//                if (fg3 == null){
//                    fg3 = new ScanFragment();
//                    fTransaction.add(R.id.ly_content,fg3);
//                }
//                else{
//                    fTransaction.show(fg3);
//                }
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
