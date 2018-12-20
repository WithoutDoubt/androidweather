package com.example.admin.androidweather.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.gson.ProvidePlanGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PlanDeliver extends AppCompatActivity {

    private String request;
    private RequestBody requestBody;

    private EditText projectName;
    private EditText company;
    private EditText contanctName;
    private EditText contanctPhone;
    private EditText carrier;
    private EditText carryPhone;
    private EditText license;
    private EditText workTeam;
    private EditText receiveAddress;

    private Button btnSave;

    private ProgressDialog progressDialog;
    private String address2 =
            "http://210.45.212.96:8080/Mobile/hfsj/deliver/deliverAppInterface/savePlanDeliver";

    private String planDeliverInfoJSON=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_deliver);
        request=getIntent().getStringExtra("requestData");
        Log.d("11111", request);

        final ProvidePlanGson providePlanGson=new ProvidePlanGson();
        projectName=(EditText)findViewById(R.id.project_name);
        company=findViewById(R.id.company);
        contanctName=findViewById(R.id.contanctName);
        contanctPhone=findViewById(R.id.contanctPhone);
        carrier=findViewById(R.id.carrier);
        carryPhone=findViewById(R.id.contanctPhone);
        license=findViewById(R.id.license);
        workTeam=findViewById(R.id.workTeam);
        receiveAddress=findViewById(R.id.receiveAddress);

        providePlanGson.setProjectName(projectName.getText().toString());
        providePlanGson.setCompany(company.getText().toString());
        providePlanGson.setContanctName(contanctName.getText().toString());
        providePlanGson.setContanctPhone(contanctPhone.getText().toString());
        providePlanGson.setCarrier(carrier.getText().toString());
        providePlanGson.setCarryPhone(carryPhone.getText().toString());
        providePlanGson.setLicense(license.getText().toString());
        providePlanGson.setWorkTeam(workTeam.getText().toString());
        providePlanGson.setReceiveAddress(receiveAddress.getText().toString());
        providePlanGson.setPlanDeliverDate(getIntent().getStringExtra("estimateDate"));


        btnSave=(Button)findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                planDeliverInfoJSON="[{projectName:"+ "\"" + projectName.getText().toString() +"\""
                        +",company:"+ "\"" + company.getText().toString() +"\""
                        +",contanctName:"+ "\"" + contanctName.getText().toString() +"\""
                        +",contanctPhone:"+ "\"" +contanctPhone.getText().toString() +"\""
                        +",carrier:"+ "\"" + carrier.getText().toString() +"\""
                        +",carryPhone:"+ "\"" + carryPhone.getText().toString() +"\""
                        +",license:"+ "\"" + license.getText().toString() +"\""
                        +",workTeam:"+ "\"" + workTeam.getText().toString() +"\""
                        +",receiveAddress:"+ "\"" + receiveAddress.getText().toString() +"\""
                        +",planDeliverDate:"+ "\"" + providePlanGson.getPlanDeliverDate() +"\"}]";

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                //requestBody = RequestBody.create(JSON,"[" + request + "]");
                request="["+request+"]";
                Log.d("FFFFFF", "DDDD-----"+ request);
                Log.d("DDDDDDD", "DDDD-----"+ planDeliverInfoJSON);
                String json="planDeliverInfoJSON:"+"\""+planDeliverInfoJSON+"\""+"componentListJSON:"+"\""+request+"\"";
                Log.d("EEEEE", "EEEEEEEE-----"+ json);
                address2=address2+"?planDeliverInfoJSON="+json;
                HttpUtil.sendOkHttpRequest(address2, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                closeProgressDialog();
                                Toast.makeText(PlanDeliver.this, "制定表单失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                        final MobileGson mobileGson = Utility.handleMobileResponse(responseText);
                        final String result = mobileGson.getResult();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                closeProgressDialog();
                                Toast.makeText(PlanDeliver.this, result,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
//                HttpUtil.sendPostRequest(address2, requestBody,new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                closeProgressDialog();
//                                Toast.makeText(PlanDeliver.this, "制定表单失败", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        final String responseText = response.body().string();
//                        final MobileGson mobileGson = Utility.handleMobileResponse(responseText);
//                        final String result = mobileGson.getResult();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                closeProgressDialog();
//                                Toast.makeText(PlanDeliver.this, result,Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
            }
        });
    }



    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(PlanDeliver.this);
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
