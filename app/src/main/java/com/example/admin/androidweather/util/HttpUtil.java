package com.example.admin.androidweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.admin.androidweather.MainActivity;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;


public class HttpUtil {
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");


    private  SharedPreferences preferences;

    public static void sendOkHttpRequest(String address,String sessionid, okhttp3.Callback callback) {


        String cookie = "jeesite.session.id=" + sessionid;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).
                header("Cookie",cookie)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //加一个头部
    public  static void  sendPostRequest(String address , RequestBody requestBody,String cookie ,okhttp3.Callback callback ){


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .header("Cookie",cookie)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
