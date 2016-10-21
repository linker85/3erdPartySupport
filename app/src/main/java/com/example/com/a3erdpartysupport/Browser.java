package com.example.com.a3erdpartysupport;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Browser extends AppCompatActivity {

    private static final String TAG = "BrowserTAG_";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        // Disable strict mode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void open(View view) {
        try {
            showContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WebView webView;

    public void showContent() throws IOException {
        String url = "";
        EditText e = (EditText) findViewById(R.id.browser_address);
        url = e.getText().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        response = client.newCall(request).execute();
        String result = response.body().string();
        Log.d(TAG, "open: " + result);
        TextView txtResult = (TextView) findViewById(R.id.result);
        txtResult.setText(result);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(result);
    }
}
