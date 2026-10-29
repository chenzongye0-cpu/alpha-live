package io.github.chenzongye0cpu.alphalive;
import android.app.Activity; import android.os.Bundle; import android.webkit.*; import androidx.webkit.WebViewAssetLoader;
public class MainActivity extends Activity {
  WebView w;
  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    final WebViewAssetLoader l = new WebViewAssetLoader.Builder().addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this)).build();
    w = new WebView(this);
    WebSettings s = w.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true);
    w.setWebViewClient(new WebViewClient() {
      @Override public WebResourceResponse shouldInterceptRequest(WebView v, WebResourceRequest r) { return l.shouldInterceptRequest(r.getUrl()); }
    });
    setContentView(w);
    if (b == null) w.loadUrl("https://appassets.androidplatform.net/assets/index.html"); else w.restoreState(b);
  }
  @Override protected void onSaveInstanceState(Bundle o) { super.onSaveInstanceState(o); w.saveState(o); }
  @Override public void onBackPressed() { if (w.canGoBack()) w.goBack(); else super.onBackPressed(); }
}
