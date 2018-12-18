package com.example.admin.androidweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.ProvidePlanGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultScanOtherActivity extends AppCompatActivity {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    final String TAG = getClass().getSimpleName();
    private String componentid ;
    private String product;
    private String address ;
    private ComponentGson componentGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        product = getIntent().getStringExtra("product");
        componentid = getIntent().getStringExtra("componentId");
        address = "http://localhost:8080/Mobile/hfsj/transferplan/transferplanAppInterface/selectComponents?componentId=" + componentid;


        //setContentView(R.layout.second_activty);
        QMUIStatusBarHelper.translucent(this);
        View root = LayoutInflater.from(this).inflate(R.layout.result_scan_two_layout, null);
        ButterKnife.bind(this, root);
        //初始化状态栏
        initTopBar();
        setContentView(root);

//        HttpUtil.sendOkHttpRequest(address, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(ResultScanOtherActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String responseText = response.body().string();
//                if (!TextUtils.isEmpty(responseText)) {
//                    try {
//                        JSONArray allData = new JSONArray(responseText);
//                        for (int i = 0; i < allData.length(); i++) {
//                            //关键代码
//                            JSONObject productLineObject = allData.getJSONObject(i);
//                            componentGson = new ComponentGson();
//                            componentGson.setName(productLineObject.getString("name"));
//                            componentGson.setComponentCode(productLineObject.getString("componentCode"));
//                            componentGson.setComponentTypeCode(productLineObject.getString("componentTypeCode"));
//                            componentGson.setWeight(productLineObject.getString("weight"));
//                            componentGson.setDimension(productLineObject.getString("dimension"));
//                            componentGson.setFloor(productLineObject.getString("floor"));
//                            componentGson.setBlockName(productLineObject.getString("blockName"));
//                            componentGson.setSpell(productLineObject.getString("spell"));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //注入服务端拿来的数据，
//                        QMUIStatusBarHelper.translucent(ResultScanOtherActivity.this);
//                        View root = LayoutInflater.from(ResultScanOtherActivity.this).inflate(R.layout.result_scan_two_layout, null);
//                        ButterKnife.bind(this, root);
//                        initTopBar();
//                        setContentView(root);
//                    }
//                });
//            }
//        });


    }

    private void initTopBar() {

        mTopBar.setTitle("钢筋登记");
        switch (product){
            case "rebar":
                mTopBar.setTitle("钢筋登记");
                break;
        }

    }


    @Override
    public void onBackPressed() {
        //Intent intent = new Intent();

//        intent.putExtra("data_return","Hello FirstActivity");
//        setResult(RESULT_OK,intent);
        finish();
    }

}
