package com.example.admin.androidweather.util;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    public String cookie2 = "Hm_lvt_82116c626a8d504a5c0675073362ef6f=1543924860,1544065175,1544102720,1544233138;" +
            " JSESSIONID=39D118C73A3FCCAEA1F018AA57EFA9D8; jeesite.session.id=7cc30613f86a4314b258dcce01aa3eee;" +
            " Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1544269727";

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
       String cookie2 = "jeesite.session.id=3c2f28e5912c44ff9bc72b97215e2079;" +
               " Hm_lvt_82116c626a8d504a5c0675073362ef6f=1544331481,1544419620,1544423915,1544514297;" +
               " Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1544514297; " +
               "JSESSIONID=D37E6BC744F27E613347FB9A3E25748D";

        String cookie = "Hm_lvt_82116c626a8d504a5c0675073362ef6f=1543924860,1544065175,1544102720,1544233138;" +
                " JSESSIONID=39D118C73A3FCCAEA1F018AA57EFA9D8; jeesite.session.id=7cc30613f86a4314b258dcce01aa3eee;" +
                " Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1544269727";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).
                header("Cookie",cookie2)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //加一个头部
    public static  void  sendPostRequest(String address , RequestBody requestBody,okhttp3.Callback callback){
        String cookie2 = "jeesite.session.id=2ec45d04fd694f14861371755705ecd0; " +
                "Hm_lvt_82116c626a8d504a5c0675073362ef6f=1544102720,1544233138,1544310834,1544331481; " +
                "JSESSIONID=07758E200A4D61591630F25E448351A5; Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1544331504";
        String cookie = "jeesite.session.id=3c2f28e5912c44ff9bc72b97215e2079;" +
                " Hm_lvt_82116c626a8d504a5c0675073362ef6f=1544331481,1544419620,1544423915,1544514297;" +
                " Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1544514297;" +
                " JSESSIONID=D37E6BC744F27E613347FB9A3E25748D";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .header("Cookie",cookie)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
