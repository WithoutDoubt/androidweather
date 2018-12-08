package com.example.admin.androidweather.util;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");


    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    //加一个头部
    public static  void  sendPostRequest(String address , RequestBody requestBody,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address).
                post(requestBody)
                .header("Cookie","jeesite.session.id=ccb0551e7344437f8df04f230bb9451d; Hm_lvt_82116c626a8d504a5c0675073362ef6f=1543924860,1544065175,1544102720,1544233138; JSESSIONID=39D118C73A3FCCAEA1F018AA57EFA9D8; Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1544234095")
                .build();
        client.newCall(request).enqueue(callback);
    }

}
