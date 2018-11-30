package com.example.admin.androidweather.util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static  void  sendPostRequest(String address , okhttp3.Callback callback,RequestBody requestBody){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).
                post(requestBody).
                build();
        client.newCall(request).enqueue(callback);
    }

}
