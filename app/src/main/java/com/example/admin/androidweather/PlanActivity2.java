package com.example.admin.androidweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlanActivity2 extends AppCompatActivity {
    private String[] data2 = { "构件1", "构件2", "构件3", "构件4",
            "构件5", "构件6", "构件7", "构件8", "构件9", "构件10",
            "构件11", "构件12", "构件13", "构件14", "构件15", "构件16",
            "构件17", "构件18", "构件19", "构件20" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_demo_layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                PlanActivity2.this, android.R.layout.simple_list_item_1, data2);
        ListView listView = (ListView) findViewById(R.id.list_vi);
        listView.setAdapter(adapter);
    }
}
