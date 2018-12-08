package com.example.admin.androidweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlanActivity3 extends AppCompatActivity {


    private String[] data = { "内倒计划1", "内倒计划2", "内倒计划3", "内倒计划4",
            "内倒计划5", "内倒计划6", "内倒计划7", "内倒计划8", "内倒计划9", "内倒计划10",
            "内倒计划11", "内倒计划12", "内倒计划13", "内倒计划14", "内倒计划15", "内倒计划16",
            "内倒计划17", "内倒计划18", "内倒计划19", "内倒计划20" };

    private String[] data2 = { "构件1", "内倒计划2", "内倒计划3", "内倒计划4",
            "内倒计划5", "内倒计划6", "内倒计划7", "内倒计划8", "内倒计划9", "内倒计划10",
            "内倒计划11", "内倒计划12", "内倒计划13", "内倒计划14", "内倒计划15", "内倒计划16",
            "内倒计划17", "内倒计划18", "内倒计划19", "内倒计划20" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_demo_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                PlanActivity3.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_vi);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PlanActivity3.this , PlanActivity2.class);
                startActivity(intent);
            }
        });

    }



}
