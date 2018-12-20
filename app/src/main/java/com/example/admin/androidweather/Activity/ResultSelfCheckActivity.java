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

import com.example.admin.androidweather.MainActivity;
import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.gson.SelfCheckRemarks;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
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
    private String componentName;
    //GSON
    private Gson gson;
    private SelfCheckRemarks remakes;
    private String comment;
    //Okhttp
    private String address = "http://10.0.2.2:8080/Mobile/updateComponentStatus";
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

        super.onCreate(saveInstanceState);
        setContentView(R.layout.result_self_check_layout);

        final QMUITipDialog tipDialog;
        tipDialog = new QMUITipDialog.Builder(ResultSelfCheckActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();


        componentId = getIntent().getStringExtra("componentId");
        componentName = getIntent().getStringExtra("componentCode");


//        componentNews = new ComponentGson();
//        componentNews = Utility.handleComponentResponse(componentId);
        Log.d("PPPPP", "onCreate: " + componentNews.getComponentId());


        product = getIntent().getStringExtra("product");

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
        componentCode.setText("构件ID:" + componentCode );
        componentCode.setEnabled(false);

        buttonok = (Button)findViewById(R.id.selfcheck_ok);
        buttonback =(Button)findViewById(R.id.button_back);

        Log.d("AAAA", "onClick: jjjinitView");


        //按钮事件
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipDialog.show();

                getComment();


                MediaType JSON = MediaType.parse("application/json; charset=utf-8");


                    address = "http://210.45.212.96:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
                            + "?componentId="
                            + componentId
                            +"&status=8"
                            +"&remarks="
                            + comment
                            +"]";
                    //配筋已经完成
                    HttpUtil.sendOkHttpRequest(address,new Callback()
                    {

                        @Override
                        public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //关闭进度条
                                tipDialog.dismiss();


                                Toast.makeText(ResultSelfCheckActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanActivity.class);
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
                                        tipDialog.dismiss();


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
                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanActivity.class);
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

