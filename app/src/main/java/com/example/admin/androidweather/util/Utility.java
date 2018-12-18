package com.example.admin.androidweather.util;

import android.text.TextUtils;

import com.example.admin.androidweather.db.Component;
import com.example.admin.androidweather.db.Province;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.gson.ProductLineGson;
import com.example.admin.androidweather.gson.SessionGson;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    /*
    *
    * */
    public static ProductLineGson handleProductLineResponse(String response){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static MobileGson handleMobileResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);

            return new Gson().fromJson(response, MobileGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static SessionGson handleUserResponse(String response){
            try {
                JSONObject jsonObject = new JSONObject(response);

                return  new Gson().fromJson(response,SessionGson.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            return  null;
    }
    public static ComponentGson handleComponentResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);

            return  new Gson().fromJson(response,ComponentGson.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }




    public static ComponentGson handleScanResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);

            return  new Gson().fromJson(response,ComponentGson.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }



}



