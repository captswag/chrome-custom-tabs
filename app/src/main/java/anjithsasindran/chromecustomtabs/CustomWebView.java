package anjithsasindran.chromecustomtabs;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Anjith Sasindran
 * on 16-Sep-15.
 */
public class CustomWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        class MyWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals(getIntent().getStringExtra("url")))
                    return false;
                else
                    return super.shouldOverrideUrlLoading(view, url);
            }
        }

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra("url"));
        webView.setWebViewClient(new MyWebViewClient());
    }
}