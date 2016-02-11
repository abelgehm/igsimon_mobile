package teste.teste2;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import teste.teste2.MainA;
/**
 * Created by A1 on 10/02/2016.
 */
public class myWebView extends WebViewClient{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().endsWith(MainA.servidor)) {

            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //view.getContext().startActivity(intent);
        return true;
    }
}
