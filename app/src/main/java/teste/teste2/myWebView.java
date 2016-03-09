package teste.teste2;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * C&oacute;digo respons&aacute;vel por limitar o acesso ao servidor selecionado.
 */
public class myWebView extends WebViewClient{
    /**
     * Subrotina respons&aacute;vel por limitar o acesso ao servidor apenas.
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //if (Uri.parse(url).getHost().endsWith(MainA.servidor)) {
        if (Uri.parse(url).getHost().startsWith(MainA.servidor)){

            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
}
