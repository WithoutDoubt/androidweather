package com.example.admin.androidweather.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.SecondActivity;
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
public class TranferLocationActivity extends AppCompatActivity {

    /**
     * 布局控件
     * 4 个 EditView
     * 1 个 BackButton
     * 1 个 saveButton
     * */
    private  EditText editTextCode ;
    private  EditText editTextLocationOne;
    private  EditText editTextLocationTwo ;
    private  EditText editTextLocationThree;

    private Button buttonBack ;
    private Button buttonSave ;

    String componentCode;
    String yardCode;
    String areaCode;
    String shelfCode;

    String address ;

    private ProgressDialog progressDialog;

    @Override
    protected void  onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.transfer_location_layout);
        componentCode = getIntent().getStringExtra("componentCode");
        initView();
        /**传入构件值**/
        editTextCode.setText("构件编号：" + componentCode);
        editTextCode.setEnabled(false);



    }
    private void initView(){
        editTextCode = (EditText)findViewById(R.id.transfer_location_component_code);
        editTextLocationOne= (EditText)findViewById(R.id.yard_code);
        editTextLocationTwo = (EditText)findViewById(R.id.area_code);
        editTextLocationThree = (EditText)findViewById(R.id.shelf_code);
        buttonBack = (Button)findViewById(R.id.transfer_location_back_button);
        buttonSave = (Button)findViewById(R.id.transfer_location_save_button);
       //result = findViewById(R.id.result);
        final Gson gson = new Gson();



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TranferLocationActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yardCode = editTextLocationOne.getText().toString();
                areaCode = editTextLocationTwo.getText().toString();
                shelfCode = editTextLocationThree.getText().toString();
              //  showProgressDialog();
                //拼接url
                address = "http://10.0.2.2:8080/Mobile/PutTransferMap" + "?yardCode="+yardCode
                        +"&componentCode=" + componentCode
                        +"&areaCode="+ areaCode
                        +"&shelfCode="+shelfCode ;
                HttpUtil.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TranferLocationActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                            }
                    });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String responseText = response.body().string();
                        final MobileGson mobileGson = Utility.handleMobileResponse(responseText);
                        final String result = mobileGson.getResult();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Intent intent = new Intent(TranferLocationActivity.this,SecondActivity.class);
//                                    startActivity(intent);
                                //    MobileGson mobileGson = gson.fromJson(responseText,MobileGson.class);
                                    Log.d("Thaaaa", result);
                                   Toast.makeText(TranferLocationActivity.this,result,Toast.LENGTH_SHORT).show();
                                }
                            });
                    }
                });
            }
        });


    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
