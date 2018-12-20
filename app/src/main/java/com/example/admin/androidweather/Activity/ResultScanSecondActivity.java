package com.example.admin.androidweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultScanSecondActivity extends AppCompatActivity {
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

    //
   // EditText editText_0 = (EditText)findViewById(R.id.edittext_name);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_scan_two_layout);

        LinearLayout linearLayout_no = (LinearLayout)findViewById(R.id.scan_linear_no);

        QMUIStatusBarHelper.translucent(this);
        final QMUITipDialog tipDialog,tipDialog_1,tipDialog_2;
        tipDialog = new QMUITipDialog.Builder(ResultScanSecondActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();

        tipDialog_1 = new QMUITipDialog.Builder(ResultScanSecondActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("发送成功")
                .create();

        tipDialog_2= new QMUITipDialog.Builder(ResultScanSecondActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("发送失败")
                .create();

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

        QMUITopBar topBar = findViewById(R.id.result_scan_topbar);
        final QMUIRoundButton buttonok = (QMUIRoundButton)findViewById(R.id.button_result_ok);
        final QMUIRoundButton buttonno = (QMUIRoundButton)findViewById(R.id.button_result_no);

        //thinkgemtopBar.setBackgroundColor(ContextCompat.getColor(this,R.color.app_color_blue_2));
        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        topBar.setBackgroundColor(getColor(R.color.app_color_blue_2));

        switch(product){
            case "rebar":
                topBar.setTitle("钢筋登记");
                buttonok.setText("确认登记");
                status_ok = "4";
                break;
            case "transferLocation":
                buttonok.setText("实际内运");
                status_ok = "12";
                break;
            case "deliverLogin":
                topBar.setTitle("发货登记");
                buttonok.setText("确认发货");
                status_ok = "13";
                break;
            case "getGood":
                topBar.setTitle("收货登记");
                buttonok.setText("确认收货");
              //  buttonok.setBackgroundColor(ContextCompat.getColor(this, R.color.app_color_blue_2));

                linearLayout_no.setVisibility(View.VISIBLE);
                buttonno.setText("构件退货");
                status_ok = "15";
                status_no = "16";
                break;
            case "drop":
                topBar.setTitle("构件报废");
                buttonok.setText("确认报废");
                status_ok = "21";
                break;
            default:
                break;

        }

        initeView();



        /**
         * 确认按钮
         * */
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipDialog.show();

//                address = "http://10.0.2.2:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
//                        + "?componentId="
//                        + componentId
//                        +"&status="
//                        +status_ok;
                address = "http://210.45.212.96:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
                        + "?componentId="
                        + componentId
                        + "&status="
                        + status_ok;
//                        +"&remarks="
//                        +"["
//  //                      + comment
//                        +"]";
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


                                Toast.makeText(ResultScanSecondActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResultScanSecondActivity.this ,ScanActivity.class);
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
                                    }
                                };
                                Timer timer = new Timer();
                                timer.schedule(task, 1500);//1.5秒后执行TimeTask的run方法



//                                Log.d("AAAA", "onClick: ok");
//                                Toast.makeText(ResultScanSecondActivity.this,result,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });


        /*
        * 否定按钮
        * */
        buttonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipDialog.show();

//                address = "http://10.0.2.2:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
//                        + "?componentId="
//                        + componentId
//                        +"&status="
//                        +status_no;
                address = "http://210.45.212.96:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
                        + "?componentId="
                        +  componentId
                        + "&status="
                        + status_no;
//                        +"&remarks="
//                        +"["
//                        //                      + comment
//                        +"]";
                //退货
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


                                Toast.makeText(ResultScanSecondActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResultScanSecondActivity.this ,ScanActivity.class);
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
//                                        finish();
                                    }
                                };
                                Timer timer = new Timer();
                                timer.schedule(task, 1500);//1.5秒后执行TimeTask的run方法



//                                Log.d("AAAA", "onClick: ok");
//                                Toast.makeText(ResultScanSecondActivity.this,result,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });


    }
    private void initeView(){


    EditText editText_0 = (EditText)findViewById(R.id.edittext_name);
    editText_0.setText(spell);
    editText_0.setEnabled(false);
    //topBar.showTitleView(true);

    EditText editText_1 = (EditText)findViewById(R.id.edittext_phonenumber);
    editText_1.setText(name);
    editText_1.setEnabled(false);

    EditText editText_2 = (EditText)findViewById(R.id.edittext_verifycode);
    editText_2.setText(blockName);
    editText_2.setEnabled(false);

    EditText editText_3 = (EditText)findViewById(R.id.edittext_password);
    editText_3.setText(floor);
    editText_3.setEnabled(false);

    EditText editText_4 = (EditText)findViewById(R.id.edittext_code);
    editText_4.setText(componentCode);
    editText_4.setEnabled(false);

    EditText editText_5 = (EditText)findViewById(R.id.edittext_type);
    editText_5.setText(componentTypeCode);
    editText_5.setEnabled(false);

    EditText editText_6 = (EditText)findViewById(R.id.edittext_weight);
    editText_6.setText(weight);
    editText_6.setEnabled(false);

    EditText editText_7 = (EditText)findViewById(R.id.edittext_fang);
    editText_7.setText(dimension);
    editText_7.setEnabled(false);
}

}
