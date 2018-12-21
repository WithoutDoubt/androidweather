package com.example.admin.androidweather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 实际内倒 没有大问题，数据可以传到数据库，只是服务端接口有点问题，返回数据也没有什么问题
 *
 * View ：界面
 *
 *
 * */
public class TransferLocationActivity extends AppCompatActivity {

    /**
     * 布局控件
     * 4 个 EditView
     * 1 个 BackButton
     * 1 个 saveButton
     * */


    private String name;
    private String componentCode;
    private String componentTypeCode;
    private String weight;
    private String dimension;
    private String floor;
    private String blockName;
    private String spell;
    private String componentId;
    private String product ;
    private String address ;
    private ComponentGson componentGson;
    private String status_ok;
    private String status_no;



    @Override
    protected void  onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.transfer_location_layout);


        componentCode = getIntent().getStringExtra("componentCode");


        initView();



        /**传入构件值**/




    }
    private void initView(){

       //result = findViewById(R.id.result);
       // final Gson gson = new Gson();

        product = getIntent().getStringExtra("product");
        componentId = getIntent().getStringExtra("componentId");
        name = getIntent().getStringExtra("name");
        componentCode = getIntent().getStringExtra("componentCode");
        componentTypeCode = getIntent().getStringExtra("componentTypeCode");
        weight = getIntent().getStringExtra("weight");
        dimension = getIntent().getStringExtra("dimension");
        floor = getIntent().getStringExtra("floor");
        blockName = getIntent().getStringExtra("blockName");
        spell = getIntent().getStringExtra("spell");




//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                yardCode = editTextLocationOne.getText().toString();
//                areaCode = editTextLocationTwo.getText().toString();
//                shelfCode = editTextLocationThree.getText().toString();
//              //  showProgressDialog();
//                //拼接url
//                address = "http://10.0.2.2:8080/Mobile/PutTransferMap" + "?yardCode="+yardCode
//                        + "&componentCode=" + componentCode
//                        + "&areaCode=" + areaCode
//                        + "&shelfCode=" + shelfCode ;
//                HttpUtil.sendOkHttpRequest(address, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//
//                                Toast.makeText(TransferLocationActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
//                            }
//                    });
//                    }
//
//                    @Override
//                    public void onResponse(Call call, final Response response) throws IOException {
//                        final String responseText = response.body().string();
//                        final MobileGson mobileGson = Utility.handleMobileResponse(responseText);
//                        final String result = mobileGson.getResult();
//                            runOnUiThread(new Runnable() {
//
//                                @Override
//                                public void run() {
//
////                                    Intent intent = new Intent(TransferLocationActivity.this,SecondActivity.class);
////                                    startActivity(intent);
//                                    //    MobileGson mobileGson = gson.fromJson(responseText,MobileGson.class);
//                                    Log.d("Thaaaa", result);
//                                    Toast.makeText(TransferLocationActivity.this,result,Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                    }
//                });
//            }
//        });


    }



}
