package com.qihoo.okhttpTest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class requestTest {
    public void okhttpTest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("").build();

        Call call = client.newCall(request);
        try {
            call.execute(); // 同步请求
        } catch (IOException e) {
            e.printStackTrace();
        }

        call.enqueue(new Callback() { // 异步请求
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
