package com.example.admin.androidweather.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.AddressUse;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.gson.SelfCheckRemarks;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResultSelfCheckActivity extends AppCompatActivity {

//    private TextView titleView;
//    private Button buttonback;
//    private Button buttonok;
  //  private Button buttonno;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private CheckBox checkBox_4;
    private CheckBox checkBox_5;


    private EditText editText0;
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

    private String comment;
    private String TemplateName;
    //Okhttp
    private String address ;
    private String address_head = AddressUse.ADDRESS_HEAD + "Mobile/hfsj/product/appAjax/updateComponentStatus?componentId=" ;
    private  RequestBody requestBody ;

    //返回值
    private  MobileGson mobileGson;
    //
    private ProgressDialog progressDialog;

    private String status;
    private String product;

    private List<String>  remakerList = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.result_self_check_layout);

        QMUITopBar topBar = findViewById(R.id.result_selfcheck_topbar);
        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        topBar.setBackgroundColor(getColor(R.color.app_color_blue_2));

        final QMUITipDialog tipDialog, tipDialog_1,tipDialog_2 ;
        LinearLayout linearLayout_no = (LinearLayout)findViewById(R.id.linear_self_no);
        tipDialog = new QMUITipDialog.Builder(ResultSelfCheckActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();

        tipDialog_1 = new QMUITipDialog.Builder(ResultSelfCheckActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("发送成功")
                .create();

        tipDialog_2= new QMUITipDialog.Builder(ResultSelfCheckActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("发送失败")
                .create();


        componentId = getIntent().getStringExtra("componentId");
        componentName = getIntent().getStringExtra("componentCode");
        product = getIntent().getStringExtra("product");
        TemplateName = getIntent().getStringExtra("code");

        final QMUIRoundButton buttonok = (QMUIRoundButton)findViewById(R.id.selfcheck_ok);
        final QMUIRoundButton buttonno = (QMUIRoundButton)findViewById(R.id.selfcheck_no);

        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        editText0 = (EditText)findViewById(R.id.transfer_location_component_code);

        switch (product){
            case "selfCheck":
                topBar.setTitle("构件自检");
                editText0.setText("构件编号:" + componentName);
                status = "8";
                break;
            case "randomCheck":
                topBar.setTitle("构件抽检");
                editText0.setText("构件编号:" + componentName);
                status = "9";
                linearLayout_no.setVisibility(View.VISIBLE);
                break;
            case "templateCheck":
                topBar.setTitle("模具检查");
                editText0.setText("模具编号:" + TemplateName);
                status = "1";
                address_head = AddressUse.ADDRESS_HEAD + "Mobile/hfsj/product/appAjax/productTemplateStatus?template=";
                //address_head = "http://10.0.2.2:8080/Mobile/hfsj/product/appAjax/productTemplateStatus?template=";
                break;
        }

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

//
       // editText0 = (EditText)findViewById(R.id.transfer_location_component_code);
      //  editText0.setText("构件编号:" + componentName);
        editText0.setEnabled(false);


        Log.d("AAAA", "onClick: jjjinitView");



        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tipDialog.show();

                getComment();
//                tipDialog.dismiss();

                //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    //210版本可以，
                    address = address_head
                            + componentId
                            +"&status="+status
                            +"&remarks="
                            +comment;
//                            + comment
//                            +"}";
                            //+"]";

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
                                Toast.makeText(ResultSelfCheckActivity.this, "上传信息失败", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanActivity.class);
//                                startActivity(intent);
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
//                                        Log.d("AAAA", "onClick: ok");
//                                        Toast.makeText(ResultSelfCheckActivity.this,result,Toast.LENGTH_SHORT).show();
                                        switch (result){
                                            case "ok":
                                                tipDialog_1.show();
                                                buttonok.setClickable(false);
                                                buttonno.setClickable(false);
                                                break;
                                            case "no":
                                                tipDialog_2.show();
                                                break;
                                            default:
                                                break;
                                        }
                                        TimerTask task = new TimerTask() {
                                            @Override
                                            public void run() {
                                                /**
                                                 *要执行的操作
                                                 *
                                                 */
                                                tipDialog_2.dismiss();
                                                tipDialog_1.dismiss();
                                                finish();
                                            }
                                        };
                                        Timer timer = new Timer();
                                        timer.schedule(task, 1500);//1.5秒后执行TimeTask的run方法



                                }
                        });
                    }
                });
            }
        });

        buttonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tipDialog.show();

                getComment();
//                tipDialog.dismiss();

                //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                //210版本可以，
                address = "http://210.45.212.96:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
                        + "?componentId="
                        + componentId
                        +"&status=10"
                        +"&remarks="+comment;
//                            + comment
//                            +"}";
                //+"]";

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
                                Toast.makeText(ResultSelfCheckActivity.this, "上传信息失败", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanActivity.class);
//                                startActivity(intent);
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
//                                        Log.d("AAAA", "onClick: ok");
//                                        Toast.makeText(ResultSelfCheckActivity.this,result,Toast.LENGTH_SHORT).show();
                                switch (result){
                                    case "ok":
                                        tipDialog_1.show();
                                        buttonok.setClickable(false);
                                        buttonno.setClickable(false);
                                        break;
                                    case "no":
                                        tipDialog_2.show();
                                        break;
                                    default:
                                        break;
                                }
                                TimerTask task = new TimerTask() {
                                    @Override
                                    public void run() {
                                        /**
                                         *要执行的操作
                                         *
                                         */
                                        tipDialog_2.dismiss();
                                        tipDialog_1.dismiss();
                                        finish();
                                    }
                                };
                                Timer timer = new Timer();
                                timer.schedule(task, 1500);//1.5秒后执行TimeTask的run方法



                            }
                        });
                    }
                });
            }
        });


//        buttonback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(ResultSelfCheckActivity.this ,ScanActivity.class);
////                startActivity(intent);
//                finish();
//            }
//        });


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
       comment = "remake= " + remakerList.get(0) ;
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

