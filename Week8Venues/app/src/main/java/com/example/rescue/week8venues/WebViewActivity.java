package com.example.rescue.week8venues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    Bundle bundle;

    /**
     * Creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        bundle = getIntent().getExtras();

        // setup a webview object and assign various parameters to it //
        WebView wv = (WebView) findViewById(R.id.wbvWebView);
        WebSettings webSettings = wv.getSettings();
        webSettings.setBuiltInZoomControls(true);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(bundle.getString("cafeWebView"));
    }
}
