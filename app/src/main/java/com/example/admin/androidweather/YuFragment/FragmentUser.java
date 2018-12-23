package com.example.admin.androidweather.YuFragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.androidweather.R;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import static android.content.Context.MODE_PRIVATE;

public class FragmentUser extends Fragment {

    private SharedPreferences preferences;
//    private String role;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content_user, container, false);
        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        preferences = getActivity().getSharedPreferences("user",MODE_PRIVATE);
        init();

        //String role = preferences.getString("roleName","");

//        QMUICommonListItemView qmuiCommonListItemView_1 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_no);
//        qmuiCommonListItemView_1.setText("小李");
//
//        QMUICommonListItemView qmuiCommonListItemView_2 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_name);
//        qmuiCommonListItemView_2.setText("小李");
//
//        QMUICommonListItemView qmuiCommonListItemView_3 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_email);
//        qmuiCommonListItemView_3.setText("小李");
//
//        QMUICommonListItemView qmuiCommonListItemView_4 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_phone);
//        qmuiCommonListItemView_4.setText("小李");
//
//        QMUICommonListItemView qmuiCommonListItemView_5 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_roleName);
//        qmuiCommonListItemView_5.setText("小李");





    }

    private void init(){
        String name = new String();
        String email = new String();
        String phone = new String();
        String mobile = new String();
        String roleName = new String();
        String no = new String();
        //String no = new String();

        no = preferences.getString("no","");
        name = preferences.getString("name","");
        email = preferences.getString("email","无");
        phone = preferences.getString("phone","无");
        mobile = preferences.getString("mobile","无");



        roleName = preferences.getString("roleName","");


        if (email==""){
            email = "无";
        }
        if (phone==""){
            phone = "无";
        }
        if (mobile==""){
            mobile = "无";
        }

        QMUICommonListItemView qmuiCommonListItemView_1 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_no);
        qmuiCommonListItemView_1.setText(no);

        QMUICommonListItemView qmuiCommonListItemView_2 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_name);
        qmuiCommonListItemView_2.setText(name);


        QMUICommonListItemView qmuiCommonListItemView_3 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_email);
        qmuiCommonListItemView_3.setText(email);

        QMUICommonListItemView qmuiCommonListItemView_4 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_phone);
        qmuiCommonListItemView_4.setText(phone);

        QMUICommonListItemView qmuiCommonListItemView_5 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_roleName);
        qmuiCommonListItemView_5.setText(roleName);

        QMUICommonListItemView qmuiCommonListItemView_6 = (QMUICommonListItemView) getActivity().findViewById(R.id.user_mobile);
        qmuiCommonListItemView_6.setText(mobile);

    }
}
