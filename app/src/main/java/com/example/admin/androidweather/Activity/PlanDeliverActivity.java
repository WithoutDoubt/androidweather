package com.example.admin.androidweather.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.YuAdapter.PlanCartAdapter;
import com.example.admin.androidweather.gson.AddressUse;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PlanDeliverActivity extends Activity implements View.OnClickListener ,  PlanCartAdapter.CheckInterface{

    private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    private static final String TAG = "PlanActivity3";
    private Button btnBack;
    private CheckBox ckAll;
    private TextView tvShowCount;
    private TextView tvShowSettlement;
    private ListView list_plan_cart;
    private TextView title;

    private PlanCartAdapter planCartAdapter;
    private List<ComponentGson> componentList = new ArrayList<>();
    private int totalCount ;
    //Intent 传递
    private String providePlanCode;//供板计划编号
    private String estimateDate;

    private RequestBody requestBody;
    private String request;

    private ProgressDialog progressDialog;
    private String TimeDate = new String();

    //未制定发货计划的构件
    private String address = AddressUse.ADDRESS_HEAD + "Mobile/hfsj/deliver/deliverAppInterface/findUnPlanComponentList";
    //发货计划单的登记


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_cart_layout);

        providePlanCode = getIntent().getStringExtra("providePlanCode");
        estimateDate = getIntent().getStringExtra("date")+" 01:00:00";
        Log.d("HHHH", estimateDate);

        address = address + "?providePlanCode=" + providePlanCode;
        //服务端交互获取构件
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PlanDeliverActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            //获取信息
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                if (!TextUtils.isEmpty(responseText)){
                    try {
                        JSONArray allData = new JSONArray(responseText);
                        for (int i = 0; i < allData.length(); i++) {
                            JSONObject componentObject = allData.getJSONObject(i);
                            ComponentGson component = new ComponentGson();
                            component.setComponentCode(componentObject.getString("componentCode"));
                            component.setComponentTypeId(componentObject.getString("componentTypeCode"));
                            component.setChoosed(false);
//                            Log.d("AAAA", component.getComponentCode());
                            componentList.add(component);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        initView();
                        if(componentList.isEmpty())
                        {
                            tvShowSettlement.setEnabled(false);
                            Toast.makeText(PlanDeliverActivity.this,"目前没有未制定发货计划的构件",Toast.LENGTH_LONG).show();
                        }
//                        Toast.makeText(PlanFirstActivity.this,result,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        //     initView();

    }

    private void initView(){
        btnBack = (Button) findViewById(R.id.main_button_back);
        ckAll = (CheckBox) findViewById(R.id.ck_all);
        tvShowCount = (TextView)findViewById(R.id.tv_show_price);
        tvShowSettlement = (TextView)findViewById(R.id.tv_settlement);
        list_plan_cart = (ListView) findViewById(R.id.list_plan_cart);

        ckAll.setOnClickListener(this);
        tvShowSettlement.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        title = (TextView)findViewById(R.id.main_title_text);
        initData();
    }
    //初始化数据 将服务器返回的数据填进来。
    protected  void initData(){

        //Adapter
        planCartAdapter = new PlanCartAdapter(this);
        planCartAdapter.setCheckInterface(this);
        list_plan_cart.setAdapter(planCartAdapter);
        planCartAdapter.setComponentList(componentList);
        title.setText("发货计划制定");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            //全选按钮的逻辑
            case R.id.ck_all:
                if (componentList.size()!=0){
                    if (ckAll.isChecked()){
                        for (int i = 0; i < componentList.size();i++){
                            componentList.get(i).setChoosed(true);
                        }
                        planCartAdapter.notifyDataSetChanged();
                    }else{
                        for (int i = 0 ; i < componentList.size();i++){
                            componentList.get(i).setChoosed(false);
                        }
                        planCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;

            //提交表单
            case R.id.tv_settlement:

                //开始缓存
                showProgressDialog();
//                if (isAllCheckNo()){
//                    Toast.makeText(PlanActivity.this,"请选择构件",Toast.LENGTH_SHORT).show();
//                    break;
//                }
                request = null;
                for (ComponentGson e : componentList){

                    if (e.isChoosed()) {
                        if (request!=null) {
                            request = request + "," + "{componentCode:" + "\"" + e.getComponentCode() + "\""
                                    + ", estimateDate:" + "\"" + estimateDate + "\"}";
                        } else{
                            request = "{componentCode:" + "\"" + e.getComponentCode() + "\""
                                    + ", estimateDate:" + "\"" + estimateDate + "\"}";
                        }
                    }

                }
                Log.d("CCCCC", "CCCC-----"+ request);
                if (request==null || estimateDate ==null){
                    closeProgressDialog();
                    Toast.makeText(PlanDeliverActivity.this,"请选择构件",Toast.LENGTH_SHORT).show();
                }else {
                    closeProgressDialog();
                    Intent intent = new Intent(PlanDeliverActivity.this, PlanDeliver.class);
                    intent.putExtra("requestData", request);
                    intent.putExtra("estimateDate", estimateDate);
                    startActivity(intent);
                }
                break;
            case R.id.main_button_back:
                finish();
                break;
        }
    }


    @Override
    public void  checkGroup(int position ,boolean isChecked){
        componentList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        planCartAdapter.notifyDataSetChanged();
        statistics();
    }

    //都没选
    private boolean isAllCheckNo(){
        for (ComponentGson e : componentList){
            if (e.isChoosed())
                return false;
        }
        return true;
    }


    //看是否全选
    private boolean isAllCheck(){
        for (ComponentGson e : componentList){
            if (!e.isChoosed())
                return false;
        }
        return true;
    }


    //统计数量

    public void statistics(){
        totalCount = 0;
        for (ComponentGson e: componentList){
            if (e.isChoosed()){
                totalCount++;
            }
        }
        tvShowCount.setText("数量合计：" + totalCount);
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
