package com.example.admin.androidweather;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.androidweather.Activity.ResultActivity;
import com.example.admin.androidweather.Activity.ResultSelfCheckActivity;
import com.example.admin.androidweather.Activity.ScanSelfCheckActivity;
import com.example.admin.androidweather.Activity.TranferLocationActivity;

public class MyFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content2, container, false);
        return view;
    }

    //    /*
//    * 按钮点击事件
//    * */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //钢筋登记
        Button btn_submit0 = (Button) getActivity().findViewById(R.id.button_);
        //自检
        Button btn_submit = (Button) getActivity().findViewById(R.id.button_checkbymyself);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(),  ScanSelfCheckActivity.class );
                Log.d("AAAA", "onClick: jjj");
                startActivity(intent);
            }
        });

        //抽检
        Button btn_submit2 = (Button) getActivity().findViewById(R.id.button_randomcheck);
        btn_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ThirdActicity.class );
                startActivity(intent);

            }
        });


        //模具检查
        Button btn_submit3 = (Button) getActivity().findViewById(R.id.button_templatecheck);
        btn_submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , ScanSelfCheckActivity.class );

                startActivity(intent);

            }
        });
        //内倒计划
        Button btn_submit4 = (Button) getActivity().findViewById(R.id.button_tranferplan);
        //内倒审核
        Button btn_submit5 = (Button) getActivity().findViewById(R.id.button_tranferplan_check);
        //实际内倒
        Button btn_submit6 = (Button) getActivity().findViewById(R.id.button_transferMap);
        btn_submit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity() , TranferLocationActivity.class);
                intent.putExtra("componentCode","componentCode1");
                startActivity(intent);

            }
        });

        Button btn_submit7 = (Button) getActivity().findViewById(R.id.button_deliverPlan);

        Button btn_submit8 = (Button) getActivity().findViewById(R.id.button_deliverLogin);

        Button btn_submit9 = (Button) getActivity().findViewById(R.id.button_getGood);

        Button btn_submit10 = (Button) getActivity().findViewById(R.id.button_drop);



    }
}







/*
 * 1.构件自检按钮
 * 2.构件抽检
 *
 * 3.模具检查
 *
 * 4.收货登记[GET]
 * 5.发货登记[GET]

 * 6.内倒计划
 * 7.内倒审核[GET]  /  工程部内倒审核[POST]
 *
 * 8.实际内倒[GET]
 *
 * 9.发货计划
 *
 * 10.构件报废[GET]
 *
 * 11.钢筋排产[GET]
 * */








