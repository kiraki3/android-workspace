package com.example.webviewexample;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://www.yahoo.co.jp/";

    // ***재형센세 강의
    // AppCompatActivity 클래스가 MainActivity 의 상위클래스이다.
    // 특별히 Override 하지 않는 이상 정상적으로 동작되는데 바꾸고 싶은 경우, Override 해서 새로 정의를 해줄 수있다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = (WebView)findViewById(R.id.webView);
        // WebView 셋팅
        webView.getSettings().setJavaScriptEnabled(true);
        // 셋팅한 주소를 열어라
        webView.loadUrl(url);
        // 쾌적하게 돌리기 위해서
        webView.setWebChromeClient(new WebChromeClient());
        // 처음엔 에러가 날거야
        webView.setWebViewClient(new WebViewClientClass());
    }
    // 뒤로가기를 눌렀을 때 원래 화면으로 돌아오기 위해서
    // ctrl+o
    // OnKeyDown android의 다양한 키를 눌렀을떄 그 동작을 해줘라
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        // 현재페이지에 url 을 읽어올 수 있는 메소드
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    }
}