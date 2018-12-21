package com.example.admin.androidweather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.Timer;
import java.util.TimerTask;

public class SnoopyActivity extends AppCompatActivity {

    /**
     * 布局控件
     * 4 个 EditView
     * 1 个 BackButton
     * 1 个 saveButton
     * */
    private TextView title;
    private Button button_back;

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



    @Override
    protected void  onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.snoopy_layout);

        button_back = (Button) findViewById(R.id.main_button_back);
        title = (TextView)findViewById(R.id.main_title_text);
        title.setText("构件全栈溯源");
        title.setBackgroundColor(getColor(R.color.app_color_blue_2));

        final QMUITipDialog tipDialog,tipDialog_1,tipDialog_2;
        tipDialog = new QMUITipDialog.Builder(SnoopyActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();

        tipDialog_1 = new QMUITipDialog.Builder(SnoopyActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("发送成功")
                .create();
        final QMUIRoundButton buttonok = (QMUIRoundButton)findViewById(R.id.button_snoopy_ok);

        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipDialog_1.show();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        /**
                         *要执行的操作
                         *
                         */

                        tipDialog_1.dismiss();
                        finish();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 1500);//1.5秒后执行TimeTask的run方法

            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





        //item
        //QMUICommonListItemView item_1 = (QMUICommonListItemView)findViewById(R.id.item_1);
        //item_1.setImageDrawable(R.mipmap.ic_point_gray);




        componentCode = getIntent().getStringExtra("componentCode");


        initView();



        /**传入构件值**/




    }
    private void initView(){

        //result = findViewById(R.id.result);
        // final Gson gson = new Gson();

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




//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                yardCode = editTextLocationOne.getText().toString();
//                areaCode = editTextLocationTwo.getText().toString();
//                shelfCode = editTextLocationThree.getText().toString();
//              //  showProgressDialog();
//                //拼接url
//                address = "http://10.0.2.2:8080/Mobile/PutTransferMap" + "?yardCode="+yardCode
//                        + "&componentCode=" + componentCode
//                        + "&areaCode=" + areaCode
//                        + "&shelfCode=" + shelfCode ;
//                HttpUtil.sendOkHttpRequest(address, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//
//                                Toast.makeText(TransferLocationActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
//                            }
//                    });
//                    }
//
//                    @Override
//                    public void onResponse(Call call, final Response response) throws IOException {
//                        final String responseText = response.body().string();
//                        final MobileGson mobileGson = Utility.handleMobileResponse(responseText);
//                        final String result = mobileGson.getResult();
//                            runOnUiThread(new Runnable() {
//
//                                @Override
//                                public void run() {
//
////                                    Intent intent = new Intent(TransferLocationActivity.this,SecondActivity.class);
////                                    startActivity(intent);
//                                    //    MobileGson mobileGson = gson.fromJson(responseText,MobileGson.class);
//                                    Log.d("Thaaaa", result);
//                                    Toast.makeText(TransferLocationActivity.this,result,Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                    }
//                });
//            }
//        });


    }



}
