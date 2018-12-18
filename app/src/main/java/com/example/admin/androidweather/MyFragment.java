package com.example.admin.androidweather;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.androidweather.Activity.PlanDeliverFirstActivity;
import com.example.admin.androidweather.Activity.PlanFirstActivity;
import com.example.admin.androidweather.Activity.ResultScanActivity;
import com.example.admin.androidweather.Activity.ResultScanOtherActivity;
import com.example.admin.androidweather.Activity.ResultSelfCheckActivity;
import com.example.admin.androidweather.Activity.ScanSelfCheckActivity;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment {

    private String role;
    private SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content3, container, false);
        return view;
    }
//    /*
//    * 按钮点击事件
//    * */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //钢筋登记




        //role = getActivity().getIntent().getStringExtra("role");
        preferences = getActivity().getSharedPreferences("user",MODE_PRIVATE);
        role = preferences.getString("roleName","");

        TextView btn_submit0 = (TextView) getActivity().findViewById(R.id.click_rebar);
        btn_submit0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(),  ResultScanOtherActivity.class );
                intent.putExtra("product","rebar");
                intent.putExtra("componentId","00b5eaf3c4424f25bccc227779dd0a6f");
                startActivity(intent);
            }
        });

        //自检
        TextView btn_submit = (TextView) getActivity().findViewById(R.id.click_selfcheck);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(),  ScanSelfCheckActivity.class );
                intent.putExtra("product","selfCheck");
                startActivity(intent);
            }
        });

        //抽检
        TextView btn_submit2 = (TextView) getActivity().findViewById(R.id.click_randomcheck);
//        btn_submit2.setEnabled(false);
        btn_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ScanSelfCheckActivity.class );
                intent.putExtra("product","randomCheck");
                intent.putExtra("componentId","KLY000701");
                startActivity(intent);

            }
        });


        //模具检查
        TextView btn_submit3 = (TextView) getActivity().findViewById(R.id.click_templatecheck);
        btn_submit3.setEnabled(false);
        btn_submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanSelfCheckActivity.class );
                intent.putExtra("product","templateCheck");
                startActivity(intent);

            }
        });
        //内倒计划
        TextView btn_submit4 = (TextView) getActivity().findViewById(R.id.click_tranferplan);

        btn_submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , PlanFirstActivity.class );
                intent.putExtra("product","transferPlan");
                startActivity(intent);

            }
        });

        //内倒审核
        TextView btn_submit5 = (TextView) getActivity().findViewById(R.id.click_transfercheck);

//        btn_submit5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent( getActivity() , PlanFirstActivity.class );
//                intent.putExtra("product","transferCheck");
//                startActivity(intent);
//
//            }
//        });
        //实际内倒
        TextView btn_submit6 = (TextView) getActivity().findViewById(R.id.click_transferlocation);

        btn_submit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanSelfCheckActivity.class);
                intent.putExtra("product","transferLocation");
                startActivity(intent);

            }
        });

        //发货计划
        TextView btn_submit7 = (TextView) getActivity().findViewById(R.id.click_deliverplan);
        btn_submit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , PlanDeliverFirstActivity.class);
                intent.putExtra("product","deliverPlan");
                startActivity(intent);

            }
        });

        //实际发货，发货的验收登记
        TextView btn_submit8 = (TextView) getActivity().findViewById(R.id.click_deliverlogin);
        btn_submit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanSelfCheckActivity.class);
                intent.putExtra("product","deliverLogin");
                startActivity(intent);
            }
        });

        //实际收货，收货的验收登记
        TextView btn_submit9 = (TextView) getActivity().findViewById(R.id.click_getgoods);
        btn_submit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanSelfCheckActivity.class);
                intent.putExtra("product","getGood");
                startActivity(intent);
            }
            });

        //构件报废
        TextView btn_submit10 = (TextView) getActivity().findViewById(R.id.click_badgoods);

        btn_submit10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanSelfCheckActivity.class);
                intent.putExtra("product","drop");
                startActivity(intent);
            }
        });

        //权限
        switch(role){
            case "生产管理部操作员":
                btn_submit.setEnabled(true);
                break;
            default:
                //btn_submit.setEnabled(false);
                break;
        }

    }
}







