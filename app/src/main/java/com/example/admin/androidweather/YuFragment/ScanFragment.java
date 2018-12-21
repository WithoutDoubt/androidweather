package com.example.admin.androidweather.YuFragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.Activity.ScanActivity;
import com.example.admin.androidweather.R;
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

import static android.app.Activity.RESULT_OK;

public class ScanFragment extends Fragment {

    private int REQUEST_CODE_SCAN = 111;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content_only, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        TextView buttonScan = (TextView) getActivity().findViewById(R.id.scanControl);



        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = null;
                AndPermission.with(getActivity())
                        .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_SCAN);
                            }
                        })
                        .onDenied(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Uri packageURI = Uri.parse("package:" + getActivity().getPackageName());
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);

                                Toast.makeText(getActivity(), "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                            }
                        }).start();

            }
        });




    }
    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                final String content = data.getStringExtra(Constant.CODED_CONTENT);
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();

                //  result.setText("扫描结果是："+content);

//                String  address = "http://210.45.212.96:8080/Mobile/hfsj/transferplan/transferplanAppInterface/" +
//                        "selectComponents?componentId=" + content;
//                    String  address = "http://10.0.2.2:8080/Mobile/hfsj/transferplan/transferplanAppInterface/" +
//                            "selectComponents?componentId=" + content;


                /*构件*/
//                HttpUtil.sendOkHttpRequest(address, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(ScanActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        final String responseText = response.body().string();
//                        if (!TextUtils.isEmpty(responseText)) {
//                            try {
//                                JSONArray allData = new JSONArray(responseText);
//                                for (int i = 0; i < allData.length(); i++) {
//                                    //关键代码
//                                    JSONObject productLineObject = allData.getJSONObject(i);
//                                    componentGson = new ComponentGson();
//                                    componentGson.setName(productLineObject.getString("name"));
//                                    componentGson.setComponentCode(productLineObject.getString("componentCode"));
//                                    componentGson.setComponentTypeCode(productLineObject.getString("componentTypeCode"));
//                                    componentGson.setWeight(productLineObject.getString("weight"));
//                                    componentGson.setDimension(productLineObject.getString("dimension"));
//                                    componentGson.setFloor(productLineObject.getString("floor"));
//                                    componentGson.setBlockName(productLineObject.getString("blockName"));
//                                    componentGson.setSpell(productLineObject.getString("spell"));
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                // Toast.makeText(ScanActivity.this, componentGson.getName(), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ScanActivity.this,context);
//
////                                    intent.putExtra("name",componentGson.getName());
////                                    intent.putExtra("componentCode",componentGson.getComponentCode());
////                                    intent.putExtra("componentTypeCode",componentGson.getComponentTypeCode());
////                                    intent.putExtra("weight",componentGson.getWeight());
////                                    intent.putExtra("dimension",componentGson.getDimension());
////                                    intent.putExtra("floor",componentGson.getFloor());
////                                    intent.putExtra("blockName",componentGson.getBlockName());
////                                    intent.putExtra("spell",componentGson.getSpell());
////                                    intent.putExtra("componentId",content);
//                                intent.putExtra("product",product);
//
//
//                                //                intent.putExtra("product","rebar");
//                                intent.putExtra("componentId","0395c752fc624ce59fd4b9b810bacb65");
//                                intent.putExtra("name","康利园");
//                                intent.putExtra("componentCode"," KLY000076");
//                                intent.putExtra("componentTypeCode","KLY-C30-YWQ4-2");
//                                intent.putExtra("weight","4.31");
//                                intent.putExtra("dimension","3.00");
//                                intent.putExtra("floor","6");
//                                intent.putExtra("blockName","1");
//                                intent.putExtra("spell","KLY");
//
//                                startActivity(intent);
//
//                            }
//                        });
//                    }
//                });


            }
        }

    }


}
