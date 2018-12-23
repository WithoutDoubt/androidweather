package com.example.admin.androidweather.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.AddressUse;
import com.example.admin.androidweather.gson.ProductLineGson;
import com.example.admin.androidweather.gson.ProvidePlanGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.hh.timeselector.timeutil.datedialog.DateListener;
import com.hh.timeselector.timeutil.datedialog.TimeConfig;
import com.hh.timeselector.timeutil.datedialog.TimeSelectorDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
* 用来显示生产线列表的
* 点击生产线
* 跳转到所在生产线中合格且未被纳入内倒计划的构件
*
* onCreate
*
* */
public class PlanDeliverFirstActivity extends Activity {

    //处理List数据
    private List<String>provideList = new ArrayList<>();

    private List<ProvidePlanGson> providePlanList = new ArrayList<ProvidePlanGson>();
    private ProgressDialog progressDialog;

    private ListView listView;
    private Button buttonback;
    private TextView title;
    //
    private String product;

    private String address ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_first_layout);

        title = (TextView) findViewById(R.id.main_title_text);
        buttonback = (Button) findViewById(R.id.main_button_back);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //拿product值
        product = getIntent().getStringExtra("product");
        switch (product){
            case "deliverPlan":
                title.setText("选择供板计划");
                //修改address
                address = AddressUse.ADDRESS_HEAD + "Mobile/hfsj/deliver/deliverAppInterface/getProvidePlanList";
                break;
            default:
                break;
        }

        showProgressDialog();
        //服务器交互拿数据
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(PlanDeliverFirstActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                //拿数据
                if (!TextUtils.isEmpty(responseText)) {
                    try {
                        JSONArray allData = new JSONArray(responseText);
                        for (int i = 0; i < allData.length(); i++) {
                            //关键代码
                            JSONObject productLineObject = allData.getJSONObject(i);
                            ProvidePlanGson providePlan = new ProvidePlanGson();
                            providePlan.setCode(productLineObject.getString("providePlanCode"));
                            providePlan.setId(productLineObject.getString("providePlanCode"));

                            provideList.add(providePlan.getCode());

                            providePlanList.add(providePlan);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //注入服务端拿来的数据，
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlanDeliverFirstActivity.this, android.R.layout.simple_list_item_1, provideList);
                        Log.d("AAAA", "注入数据");
                        listView = (ListView) findViewById(R.id.list_plan_line);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    //后台交互，生成构件list，传递list过去跳转
                                ProvidePlanGson providePlan = providePlanList.get(position);
                                    dateChoose(providePlan.getId());
                            }
                        });

                    }
                });
            }
        });

        closeProgressDialog();
        }

    /**
     * 打开进度对话框
     */
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

    /*
    * 选择计划倒运时间
    * 选择计划发货时间
    * */
    private void dateChoose(final String providePlanId){
        TimeSelectorDialog dialog = new TimeSelectorDialog(PlanDeliverFirstActivity.this);
        //设置标题
        dialog.setTimeTitle("选择计划发货日期:");
        //显示类型
        dialog.setIsShowtype(TimeConfig.YEAR_MONTH_DAY);
        //默认时间
        dialog.setCurrentDate("2018-01-11");
        //隐藏清除按钮
        dialog.setEmptyIsShow(false);
        //设置起始时间
        dialog.setStartYear(1888);
        dialog.setDateListener(new DateListener() {
            @Override
            public void onReturnDate(String time,int year, int month, int day, int hour, int minute, int isShowType) {
                Log.d("AAAA", time);
                //
                Intent intent = new Intent(PlanDeliverFirstActivity.this, PlanDeliverActivity.class);
                intent.putExtra("providePlanCode", providePlanId);
                intent.putExtra("date",time);
                startActivity(intent);
            }
            @Override
            public void onReturnDate(String empty) {
                Toast.makeText(PlanDeliverFirstActivity.this,"请选择计划发货日期",Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
}
