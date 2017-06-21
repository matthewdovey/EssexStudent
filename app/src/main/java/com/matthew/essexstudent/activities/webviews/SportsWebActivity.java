package com.matthew.essexstudent.activities.webviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.matthew.essexstudent.R;
import com.matthew.essexstudent.activities.activities.EventActivity;

/**
 * Created by matthew on 18/12/15.
 */
public class SportsWebActivity extends AppCompatActivity {

    public WebView eventWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        eventWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = eventWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //String sendTo = EventActivity.eventLink;

        //Test website please delete!
        //eventWebView.loadUrl(sendTo);

    }
}
