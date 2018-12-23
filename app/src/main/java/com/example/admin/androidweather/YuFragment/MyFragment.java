package com.example.admin.androidweather.YuFragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.androidweather.Activity.PlanDeliverFirstActivity;
import com.example.admin.androidweather.Activity.PlanFirstActivity;
import com.example.admin.androidweather.Activity.ResultSelfCheckActivity;
import com.example.admin.androidweather.Activity.ScanActivity;
import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.SelfCheckRemarks;

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


        //动态生成权限
        /*#################################################################
        对于每一个view


        判断权限

        选择让它显示
        选择不同的图片  （）
        选择不同的标题
        选择不同的点击事件




        * ################################################################*/

        //#####################################初始化
        TextView btn_submit0 = (TextView) getActivity().findViewById(R.id.click_rebar);
        TextView btn_submit = (TextView) getActivity().findViewById(R.id.click_selfcheck);
        TextView btn_submit2 = (TextView) getActivity().findViewById(R.id.click_randomcheck);
        TextView btn_submit3 = (TextView) getActivity().findViewById(R.id.click_templatecheck);
        TextView btn_submit4 = (TextView) getActivity().findViewById(R.id.click_tranferplan);
        TextView btn_submit5 = (TextView) getActivity().findViewById(R.id.click_transfercheck);
        TextView btn_submit6 = (TextView) getActivity().findViewById(R.id.click_transferlocation);
        TextView btn_submit7 = (TextView) getActivity().findViewById(R.id.click_deliverplan);
        TextView btn_submit8 = (TextView) getActivity().findViewById(R.id.click_deliverlogin);
        TextView btn_submit9 = (TextView) getActivity().findViewById(R.id.click_getgoods);
        TextView btn_submit10 = (TextView) getActivity().findViewById(R.id.click_badgoods);
        //#######################################


        //判断#####################################








        //##########################################


        //TextView btn_submit0 = (TextView) getActivity().findViewById(R.id.click_rebar);
        //################## 设置图片
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_a_rebar);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        btn_submit0.setCompoundDrawables(null,drawable,null,null);
        //################## 设置图片


        btn_submit0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(),  ScanActivity.class );

//                intent.putExtra("product","rebar");
//                intent.putExtra("componentId","0395c752fc624ce59fd4b9b810bacb65");
//                intent.putExtra("name"," ");
//                intent.putExtra("componentCode"," ");
//                intent.putExtra("componentTypeCode"," ");
//                intent.putExtra("weight"," ");
//                intent.putExtra("dimension"," ");
//                intent.putExtra("floor"," ");
//                intent.putExtra("blockName"," ");
//                intent.putExtra("spell"," ");

                intent.putExtra("product","rebar");



                startActivity(intent);
            }
        });

        //自检
        //TextView btn_submit = (TextView) getActivity().findViewById(R.id.click_selfcheck);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ScanActivity.class );
                //                intent.putExtra("product","rebar");
//                intent.putExtra("componentId","0395c752fc624ce59fd4b9b810bacb65");
//                intent.putExtra("name"," ");
//                intent.putExtra("componentCode"," ");
//                intent.putExtra("componentTypeCode"," ");
//                intent.putExtra("weight"," ");
//                intent.putExtra("dimension"," ");
//                intent.putExtra("floor"," ");
//                intent.putExtra("blockName"," ");
//                intent.putExtra("spell"," ");

                intent.putExtra("product","selfCheck");
                startActivity(intent);
            }
        });

        //抽检
        //TextView btn_submit2 = (TextView) getActivity().findViewById(R.id.click_randomcheck);
//        btn_submit2.setEnabled(false);
        btn_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ScanActivity.class );
                intent.putExtra("product","randomCheck");
                //intent.putExtra("componentId","KLY000701");
                startActivity(intent);

            }
        });


        //模具检查
        //TextView btn_submit3 = (TextView) getActivity().findViewById(R.id.click_templatecheck);
        //btn_submit3.setEnabled(false);
        btn_submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanActivity.class );





//                intent.putExtra("componentId","d55590fba0dd487c898af86da2d1cb29");
//                intent.putExtra("name"," ");
//                intent.putExtra("componentCode"," ");
//                intent.putExtra("componentTypeCode"," ");
//                intent.putExtra("weight"," ");
//                intent.putExtra("dimension"," ");
//                intent.putExtra("floor"," ");
//                intent.putExtra("blockName"," ");
//                intent.putExtra("spell"," ");
//                intent.putExtra("name","");
//                intent.putExtra("makeData","");
//                intent.putExtra("projectName","");
//                intent.putExtra("typeName","");






                intent.putExtra("product","templateCheck");
                startActivity(intent);

            }
        });
        //内倒计划
        //TextView btn_submit4 = (TextView) getActivity().findViewById(R.id.click_tranferplan);

        btn_submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , PlanFirstActivity.class );
                intent.putExtra("product","transferPlan");
                startActivity(intent);

            }
        });

        //内倒审核
        //TextView btn_submit5 = (TextView) getActivity().findViewById(R.id.click_transfercheck);

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
        //TextView btn_submit6 = (TextView) getActivity().findViewById(R.id.click_transferlocation);

        btn_submit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanActivity.class);
                intent.putExtra("product","transferLocation");
                startActivity(intent);

            }
        });

        //发货计划
       // TextView btn_submit7 = (TextView) getActivity().findViewById(R.id.click_deliverplan);
        btn_submit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , PlanDeliverFirstActivity.class);
                intent.putExtra("product","deliverPlan");
                startActivity(intent);

            }
        });

        //实际发货，发货的验收登记
       // TextView btn_submit8 = (TextView) getActivity().findViewById(R.id.click_deliverlogin);
        btn_submit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanActivity.class);
                intent.putExtra("product","deliverLogin");
                startActivity(intent);
            }
        });

        //实际收货，收货的验收登记
        //TextView btn_submit9 = (TextView) getActivity().findViewById(R.id.click_getgoods);
        btn_submit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanActivity.class);
                intent.putExtra("product","getGood");
                startActivity(intent);
            }
            });

        //构件报废
       // TextView btn_submit10 = (TextView) getActivity().findViewById(R.id.click_badgoods);

        btn_submit10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanActivity.class);
                intent.putExtra("product","drop");
                startActivity(intent);
            }
        });

//        //权限
//        switch(role){
//            case "生产管理部操作员":
//                btn_submit.setEnabled(true);
//                break;
//            default:
//                //btn_submit.setEnabled(false);
//                break;
//        }

    }
}







