package com.baynecorp.yoweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double latitude = 37.8267;
        double longitude = -122.4233;

        String foreCastURL = "https://api.darksky.net/forecast/" + getString(R.string.dark_sky_api_key) + "/" +latitude + "," + longitude;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(foreCastURL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("Jared", "Call failure: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        //In case the response doesn't have any text in the response for whatever reason
                        assert response.body() != null;

                        Log.v("Jared", response.body().string());
                    }
                } catch (IOException e) {
                    Log.e("Jared", "IO Exception caught: " + e.toString());
                }
            }
        });
    }
}
