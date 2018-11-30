package com.example.admin.androidweather;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.androidweather.Activity.ResultActivity;
import com.example.admin.androidweather.gson.ComponentGson;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

public class ThirdActicity extends AppCompatActivity implements View.OnClickListener {

    //
   // private Button scanBtn;
    private TextView result;
    private int REQUEST_CODE_SCAN = 111;

    private DrawerLayout drawerLayout;
    private TextView scanBtn;
    private TextView tvPost;
    private TextView tvSweep;
    private RecyclerView rvUnCheck;
    private TextView tvEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        initView();
    }

    private void initView(){
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
        result = findViewById(R.id.result);

    }

    @Override
    public void onClick(View v){
        Bitmap bitmap = null;
        switch(v.getId()){
            case R.id.scanBtn:
            AndPermission.with(this)
                    .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                    .onGranted(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
                            Intent intent = new Intent(ThirdActicity.this, CaptureActivity.class);
                            /*ZxingConfig是配置类
                             *可以设置是否显示底部布局，闪光灯，相册，
                             * 是否播放提示音  震动
                             * 设置扫描框颜色等
                             * 也可以不传这个参数
                             * */
//                                ZxingConfig config = new ZxingConfig();
//                                config.setPlayBeep(true);//是否播放扫描声音 默认为true
//                                config.setShake(true);//是否震动  默认为true
//                                config.setDecodeBarCode(true);//是否扫描条形码 默认为true
//                                config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
//                                config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
//                                config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
//                                config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
//                                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
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

                            Toast.makeText(ThirdActicity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                        }
                    }).start();

            break;
            default:
        }

    }

    @Override
    protected  void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == REQUEST_CODE_SCAN && resultCode==RESULT_OK){
            if(data != null){
                String content = data.getStringExtra(Constant.CODED_CONTENT);
              //  result.setText("扫描结果是："+content);


                Intent intent = new Intent( this ,ResultActivity.class );
                startActivity(intent);
            }
        }
    }
}
