package com.example.admin.androidweather.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.gson.SelfCheckRemarks;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResultSelfCheckActivity extends AppCompatActivity {

    private TextView textView;
    private Button buttonback;
    private Button buttonok;
  //  private Button buttonno;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private CheckBox checkBox_4;
    private CheckBox checkBox_5;


    private EditText componentCode;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;
    private EditText editText9;
    private EditText editText10;
    private EditText editText11;
    private EditText editText12;
    private EditText editText13;
    private EditText editText14;
    private EditText editText15;
    private EditText editText16;

    //获取信息
    private String componentId ;
    private String stuats;
    private String remarks;


    //GSON
    private Gson gson;
    private SelfCheckRemarks remakes;
    private String comment;

    //Okhttp
    private String address = "http://localhost:8080/Mobile/updateComponentStatus";
    //private String address = "http://10.0.2.2:8080/Mobile/updateComponentStatus";
    private  RequestBody requestBody ;

    //返回值
    private  MobileGson mobileGson;

    //
    private ProgressDialog progressDialog;

    private String content;
    private String product;
    private ComponentGson componentNews;
    private List<String>  remakerList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle saveInstanceState){
        // TODO 自动生成的方法存根
        super.onCreate(saveInstanceState);
        setContentView(R.layout.result_self_check_layout);
        componentId = getIntent().getStringExtra("componentId");
        componentNews = new ComponentGson();
        componentNews = Utility.handleScanResponse(componentId);


        product = getIntent().getStringExtra("product");

      //  initeView();
        //Log.d("AAAA", "onClick: jjjJ");
{
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        editText8 = (EditText)findViewById(R.id.editText8);
        editText9 = (EditText)findViewById(R.id.editText9);
        editText10 = (EditText)findViewById(R.id.editText10);
        editText11 = (EditText)findViewById(R.id.editText11);
        editText12 = (EditText)findViewById(R.id.editText12);
        editText13 = (EditText)findViewById(R.id.editText13);
        editText14 = (EditText)findViewById(R.id.editText14);
        editText15 = (EditText)findViewById(R.id.editText15);
        editText16 = (EditText)findViewById(R.id.editText16);

        checkBox_1 = (CheckBox)findViewById(R.id.checkbox_1);
        checkBox_2 = (CheckBox)findViewById(R.id.checkbox_2);
        checkBox_3 = (CheckBox)findViewById(R.id.checkbox_3);
        checkBox_4 = (CheckBox)findViewById(R.id.checkbox_4);
        checkBox_5 = (CheckBox)findViewById(R.id.checkbox_5);


        componentCode = (EditText)findViewById(R.id.transfer_location_component_code);
        componentCode.setText("构件ID:" + componentNews.getComponentCode() );
        componentCode.setEnabled(false);
        //  buttonno = (Button)findViewById(R.id.selfcheck_no);
        buttonok = (Button)findViewById(R.id.selfcheck_ok);
        buttonback =(Button)findViewById(R.id.button_back);
 }


        Log.d("AAAA", "onClick: jjjinitView");


        //按钮事件
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                getComment();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                requestBody = RequestBody.create(JSON,"{compomemtId:\"085213c03d0445eea26ebad68296991d\",status= 8 ,"+"remarks="+"\""
//                        + comment +"\"}");
                Log.d("SSSSA", comment);
 //               {id: "33cedef39e024ceb9e98b5046b0d78d5", selfCheckStatus: 1, selfCheckRemarks: ""}
                //
                    address = address
                            //"http://10.0.2.2:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
                            + "?componentId="
//                            "085213c03d0445eea26ebad68296991d"
                            + componentNews.getComponentId()
                            +"&status=9"
                            +"&remarks="
                            + comment
                            +"]";
                    //配筋已经完成
  //                  HttpUtil.sendPostRequest(address, requestBody,new Callback()
                    HttpUtil.sendOkHttpRequest(address,new Callback()
                    {
                        //获取失败
                        @Override
                        public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //关闭进度条
                                closeProgressDialog();
                                Toast.makeText(ResultSelfCheckActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanSelfCheckActivity.class);
                                startActivity(intent);
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
                                        //关闭进度条
                                        closeProgressDialog();
                                        Log.d("AAAA", "onClick: ok");
                                        Toast.makeText(ResultSelfCheckActivity.this,result,Toast.LENGTH_SHORT).show();
                                }
                        });
                    }
                });
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanSelfCheckActivity.class);
                startActivity(intent);
            }
        });


        }



   private void getComment(){
        if(checkBox_1.isChecked())
        {
            remakerList.add("1");
        }else
        {
            remakerList.add("0");
        }

        if(checkBox_2.isChecked())
        {
            remakerList.add("1");
        }else
        {
            remakerList.add("0");
        }

        if(checkBox_3.isChecked())
        {
            remakerList.add("1");
        }else
        {
            remakerList.add("0");
        }
        if(checkBox_4.isChecked())
        {
            remakerList.add("1");
        }else
        {
            remakerList.add("0");
        }
        if(checkBox_5.isChecked())
        {
            remakerList.add("1");
        }else
        {
            remakerList.add("0");
        }

       remakerList.add(editText1.getText().toString());
       remakerList.add(editText2.getText().toString());
       remakerList.add(editText3.getText().toString());
       remakerList.add(editText4.getText().toString());
       remakerList.add(editText5.getText().toString());
       remakerList.add(editText6.getText().toString());
       remakerList.add(editText7.getText().toString());
       remakerList.add(editText8.getText().toString());
       remakerList.add(editText9.getText().toString());
       remakerList.add(editText10.getText().toString());
       remakerList.add(editText11.getText().toString());
       remakerList.add(editText12.getText().toString());
       remakerList.add(editText13.getText().toString());
       remakerList.add(editText14.getText().toString());
       remakerList.add(editText15.getText().toString());
       remakerList.add(editText16.getText().toString());

      //  editTextSet();
       comment = "[" + "remake= " + remakerList.get(0) ;
       for (int i = 1 ; i < remakerList.size();i++){
           comment = comment + ",remake=" + remakerList.get(i) ;
       }


    }

     private void checkBoxSet( ){
        if(checkBox_1.isChecked())
        {
            remakes.setCheck_1("1");
        }else
        {
            remakes.setCheck_1("0");
        }

        if(checkBox_2.isChecked())
        {
             remakes.setCheck_2("1");
        }else
            {
             remakes.setCheck_2("0");
         }

         if(checkBox_3.isChecked())
         {
             remakes.setCheck_3("1");
         }else
         {
             remakes.setCheck_3("0");
         }
         if(checkBox_4.isChecked())
         {
             remakes.setCheck_4("1");
         }else
         {
             remakes.setCheck_4("0");
         }
         if(checkBox_5.isChecked())
         {
             remakes.setCheck_5("1");
         }else
         {
             remakes.setCheck_5("0");
         }

    }
     private void editTextSet(){
         remakes.setCheck_6(editText1.getText().toString());
         remakes.setCheck_7(editText2.getText().toString());
         remakes.setCheck_8(editText3.getText().toString());
         remakes.setCheck_9(editText4.getText().toString());
         remakes.setCheck_10(editText5.getText().toString());
         remakes.setCheck_11(editText6.getText().toString());
         remakes.setCheck_12(editText7.getText().toString());
         remakes.setCheck_13(editText8.getText().toString());
         remakes.setCheck_14(editText9.getText().toString());
         remakes.setCheck_15(editText10.getText().toString());
         remakes.setCheck_16(editText11.getText().toString());
         remakes.setCheck_17(editText12.getText().toString());
         remakes.setCheck_18(editText13.getText().toString());
         remakes.setCheck_19(editText14.getText().toString());
         remakes.setCheck_20(editText15.getText().toString());
         remakes.setCheck_21(editText16.getText().toString());

     }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(ResultSelfCheckActivity.this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}

