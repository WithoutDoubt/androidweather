package com.example.admin.androidweather.YuFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.androidweather.Activity.ResultSelfCheckActivity;
import com.example.admin.androidweather.Activity.ScanSelfCheckActivity;
import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FragmentResultRebar extends Fragment {

    private  String componentId;
    private  String address ="http://10.0.2.2:8080/Mobile/updateComponentStatus";
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.result_rebar_fragment , container ,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //加载布局
        EditText editText = (EditText) getActivity().findViewById(R.id.component_rebar);
        editText.setText(getArguments().getString("componentId"));
        editText.setEnabled(false);


        Button button_ok = (Button)getActivity().findViewById (R.id.button_ok_rebar);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address ="http://10.0.2.2:8080/Mobile/hfsj/product/appAjax/updateComponentStatus"
                        + "?componentId=085213c03d0445eea26ebad68296991d"

                        +"&status=4"
                        +"&remakes=1";

                HttpUtil.sendOkHttpRequest(address, new Callback() {
                    //获取失败
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //关闭进度条
                                closeProgressDialog();
                                Toast.makeText(getActivity(), "获取信息失败", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity() ,ScanSelfCheckActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String responseText = response.body().string();
                        final MobileGson mobileGson = Utility.handleMobileResponse(responseText);
                        final String result = mobileGson.getResult();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //关闭进度条
                                closeProgressDialog();
                                Log.d("AAAA", "onClick: ok");
                                if (result == "ok")
                                     Toast.makeText(getActivity(),"配筋已完成",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getActivity(),"登记出错",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
                    }
                });
                //
            }
        });

        Button button_back = (Button)getActivity().findViewById(R.id.button_back_rebar);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }


    //加载框
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
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
