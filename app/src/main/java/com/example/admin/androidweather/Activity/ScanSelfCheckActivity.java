package com.example.admin.androidweather.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

public class ScanSelfCheckActivity extends AppCompatActivity {

    private TextView buttonScan;
    private String product;
    private int REQUEST_CODE_SCAN = 111;

    Class<?>  context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);
        buttonScan = (TextView) findViewById(R.id.button_scan);


        //决定扫描后跳转的页面,
        product = getIntent().getStringExtra("product");
        switch(product){
            case "reabr":
                context = ResultSelfCheckActivity.class;
                break;
            case "selfCheck":
                context = ResultSelfCheckActivity.class;
                break;
            case "randomCheck":
                context = ResultSelfCheckActivity.class;
                break;
            case "templateCheck":
                context = ResultSelfCheckActivity.class;
                break;
            case "transferLocation":
                context = ResultSelfCheckActivity.class;
                break;
            case "deliverLogin":
                context = ResultSelfCheckActivity.class;
                break;
            case "getGood":
                context = ResultSelfCheckActivity.class;
                break;
            case "drop":
                context = ResultSelfCheckActivity.class;
                break;
            default:
                break;

        }

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = null;
                AndPermission.with(ScanSelfCheckActivity.this)
                        .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Intent intent = new Intent(ScanSelfCheckActivity.this, CaptureActivity.class);
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

                                Toast.makeText(ScanSelfCheckActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
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
                    String content = data.getStringExtra(Constant.CODED_CONTENT);
                    //  result.setText("扫描结果是："+content);

                    Intent intent = new Intent(ScanSelfCheckActivity.this, ResultScanActivity.class);
                    //传输content
                    intent.putExtra("componentId", content);
                    intent.putExtra("product",product);
                    startActivity(intent);
                }
            }

        }

}