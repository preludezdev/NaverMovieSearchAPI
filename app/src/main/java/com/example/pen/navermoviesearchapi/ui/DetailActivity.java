package com.example.pen.navermoviesearchapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.pen.navermoviesearchapi.R;

public class DetailActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ImageButton btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btBack = findViewById(R.id.btBack);
        webView = findViewById(R.id.webView);

        //웹뷰 설정
        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String url = intent.getStringExtra("link");
        Log.d("test",url);
        webView.loadUrl(url);

        //백버튼 이벤트 처리
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
