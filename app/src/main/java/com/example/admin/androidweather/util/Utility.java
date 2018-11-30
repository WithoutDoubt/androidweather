package com.example.admin.androidweather.util;

import android.text.TextUtils;

import com.example.admin.androidweather.db.City;
import com.example.admin.androidweather.db.Component;
import com.example.admin.androidweather.db.Province;
import com.example.admin.androidweather.gson.ComponentGson;
import com.example.admin.androidweather.gson.MobileGson;
import com.example.admin.androidweather.gson.TestStudent;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {


    public static MobileGson handleMobileResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
        //    JSONArray jsonArray = jsonObject.getJSONArray();
        //    String responseData = response.body().string();
           return  new Gson().fromJson(response,MobileGson.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
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
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的Component类数据
     */

    public static ComponentGson handleComponentResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String ComponentContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(ComponentContent, ComponentGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    //测试
    public static TestStudent handleStudentResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String StudentContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(StudentContent, TestStudent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

}



