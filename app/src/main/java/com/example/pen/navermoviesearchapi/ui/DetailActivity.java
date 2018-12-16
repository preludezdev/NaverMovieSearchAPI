package com.example.pen.navermoviesearchapi.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.pen.navermoviesearchapi.R;
import com.example.pen.navermoviesearchapi.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    WebSettings webSettings;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail);

        //웹뷰 설정
        binding.webView.setWebViewClient(new WebViewClient());
        webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String url = intent.getStringExtra("link");
        binding.webView.loadUrl(url);

        //백버튼 이벤트 처리
        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
