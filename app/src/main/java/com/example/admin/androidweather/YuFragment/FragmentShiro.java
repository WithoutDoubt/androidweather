package com.example.admin.androidweather.YuFragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.Activity.SnoopyActivity;
import com.example.admin.androidweather.Activity.TransferLocationActivity;
import com.example.admin.androidweather.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentShiro extends Fragment {
    private int REQUEST_CODE_SCAN = 111;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content_only, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        TextView textView = (TextView) getActivity().findViewById(R.id.scanControl);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
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
                                        Uri packageURI = Uri.parse("package:" + getActivity().getPackageName());//小天才
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
    public   void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == REQUEST_CODE_SCAN && resultCode==RESULT_OK){
            if(data != null){
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                //  result.setText("扫描结果是："+content);

                //实际内倒登记
                Intent intent = new Intent( getActivity() ,SnoopyActivity.class );


                //传输content
                intent.putExtra("componentId",content);
                startActivity(intent);
            }
        }
    }
}
