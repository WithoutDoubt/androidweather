package com.example.admin.androidweather.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.SecondActivity;
import com.example.admin.androidweather.gson.AddressUse;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScanActivity extends AppCompatActivity {

    private TextView buttonScan;
    private String product;
    private int REQUEST_CODE_SCAN = 111;

    private Button buttonBack;
    private TextView titleView;
    private String address_head;
    private Boolean tag = true;

    Class<?>  context;

    ComponentGson componentGson =new ComponentGson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);
        buttonScan = (TextView) findViewById(R.id.button_scan);
        buttonBack = (Button) findViewById(R.id.main_button_back);
        titleView = (TextView)findViewById(R.id.main_title_text);
       // titleView.setBackgroundColor(R.color.app_color_blue_2);
        //决定扫描后跳转的页面,
        product = getIntent().getStringExtra("product");
        address_head = AddressUse.ADDRESS_HEAD + "Mobile/hfsj/transferplan/transferplanAppInterface/selectComponents?componentId=";


        switch(product){
            case "rebar":
                titleView.setText("钢筋登记");
                context = ResultScanSecondActivity.class;
                break;
            case "selfCheck":
                titleView.setText("构件自检");
                context = ResultSelfCheckActivity.class;
                break;
            case "randomCheck":
                titleView.setText("构件抽检");
                context = ResultSelfCheckActivity.class;
                break;
            case "templateCheck":
                tag = false;
                titleView.setText("模具检查");
                address_head = AddressUse.ADDRESS_HEAD + "Mobile/hfsj/product/appAjax/selectTemplate?templateId=";

                context = ResultSelfCheckActivity.class;
                break;
            case "transferLocation":
                titleView.setText("实际内运");
                break;
            case "deliverLogin":
                titleView.setText("发货登记");
                context = ResultScanSecondActivity.class;
                break;
            case "getGood":
                titleView.setText("收货登记");
                context = ResultScanSecondActivity.class;
                break;
            case "drop":
                titleView.setText("构件报废");
                context = ResultScanSecondActivity.class;
                break;
            default:
                break;

        }

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = null;
                AndPermission.with(ScanActivity.this)
                        .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Intent intent = new Intent(ScanActivity.this, CaptureActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_SCAN);
                            }
                        })
                        .onDenied(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Uri packageURI = Uri.parse("package:" + getPackageName());
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);

                                Toast.makeText(ScanActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                            }
                        }).start();

            }
        });



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
                if (data != null) {
                    final String content = data.getStringExtra(Constant.CODED_CONTENT);
                    //  result.setText("扫描结果是："+content);

                    String  address = address_head  + content;
//                    String  address = "http://10.0.2.2:8080/Mobile/hfsj/transferplan/transferplanAppInterface/" +
//                            "selectComponents?componentId=" + content;


                      /*构件*/
                      HttpUtil.sendOkHttpRequest(address, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ScanActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String responseText = response.body().string();
                            if (!TextUtils.isEmpty(responseText)) {
                                try {
                                    JSONArray allData = new JSONArray(responseText);
                                    for (int i = 0; i < allData.length(); i++) {
                                        //关键代码
                                        JSONObject productLineObject = allData.getJSONObject(i);
                                        componentGson = new ComponentGson();
                                        componentGson.setName(productLineObject.getString("name"));
                                        componentGson.setComponentCode(productLineObject.getString("componentCode"));
                                        componentGson.setComponentTypeCode(productLineObject.getString("componentTypeCode"));
                                        componentGson.setWeight(productLineObject.getString("weight"));
                                        componentGson.setDimension(productLineObject.getString("dimension"));
                                        componentGson.setFloor(productLineObject.getString("floor"));
                                        componentGson.setBlockName(productLineObject.getString("blockName"));
                                        componentGson.setSpell(productLineObject.getString("spell"));

                                        componentGson.setName(productLineObject.getString("name"));
                                        componentGson.setMakeDate(productLineObject.getString("makeDate"));
                                        componentGson.setProjectName(productLineObject.getString("projectName"));
                                        componentGson.setTypeName(productLineObject.getString("typeName"));
                                        componentGson.setCode(productLineObject.getString("code"));

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                   // Toast.makeText(ScanActivity.this, componentGson.getName(), Toast.LENGTH_SHORT).show();
                                    if((componentGson.getComponentId()!=null)) {
                                        Intent intent = new Intent(ScanActivity.this, context);
                                        try {
                                            intent.putExtra("componentId", content);
                                            intent.putExtra("componentCode", componentGson.getComponentCode());
                                            intent.putExtra("componentTypeCode", componentGson.getComponentTypeCode());
                                            intent.putExtra("product", product);

                                            if (tag) {
                                                intent.putExtra("name", componentGson.getName());
                                                intent.putExtra("weight", componentGson.getWeight());
                                                intent.putExtra("dimension", componentGson.getDimension());
                                                intent.putExtra("floor", componentGson.getFloor());
                                                intent.putExtra("blockName", componentGson.getBlockName());
                                                intent.putExtra("spell", componentGson.getSpell());
                                            } else {
                                                intent.putExtra("Projectname", componentGson.getProjectName());
                                                intent.putExtra("makeData", componentGson.getMakeDate());
                                                intent.putExtra("projectName", componentGson.getProjectName());
                                                intent.putExtra("typeName", componentGson.getType());
                                                intent.putExtra("code", componentGson.getCode());
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(ScanActivity.this,"请扫描正确的二维码",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    });


                }
            }

        }

}