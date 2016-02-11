package teste.teste2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.preference.PreferenceManager;

public class MainA extends ActionBarActivity {

    private Button btnAbrirInterface;
    private EditText APIKEY;
    private String ApiKey = "";
    private EditText Servidor;
    public static String servidor = "";

    private WebView wvTelaOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIKEY = (EditText) findViewById(R.id.txtApiKey);
        Servidor = (EditText)findViewById(R.id.txtEndereco);
        btnAbrirInterface = (Button) findViewById(R.id.btnAbrir);

        SharedPreferences Opcoes = this.getPreferences(MODE_PRIVATE);

        servidor = Opcoes.getString("servidor", "");
        ApiKey = Opcoes.getString("apikey", "");

        Servidor.setText(servidor);
        APIKEY.setText(ApiKey);
        wvTelaOnline = (WebView) findViewById(R.id.wvOnline);

        btnAbrirInterface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EnderecoCompleto = "";
                servidor = Servidor.getText().toString();
                ApiKey = APIKEY.getText().toString();

                // Salva as configuracoes
                SharedPreferences Opcoes = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor Editor = Opcoes.edit();
                Editor.putString("servidor", servidor);
                Editor.putString("apikey", ApiKey);

                Editor.commit();


                // Coloca a "/" no final se precisar
                if (servidor.charAt(servidor.length() - 1) != '/'){
                    servidor += "/";
                }
                // Coloca "http://" no inicio, se precisar
                if (!servidor.startsWith("http")){
                    servidor = "http://" + servidor;
                }
                EnderecoCompleto = servidor + "mobile.php?apikey=" + ApiKey;
                ApiKey = APIKEY.getText().toString();

                // Force links and redirects to open in the WebView instead of in a browser
                wvTelaOnline.setWebViewClient(new WebViewClient());

                // Enable Javascript
                WebSettings ws = wvTelaOnline.getSettings();
                ws.setJavaScriptEnabled(true);

                // Deixa a tela web visivel (inicialmente está invisível)
                wvTelaOnline.setVisibility(View.VISIBLE);

                // Abre o endereço remoto
                wvTelaOnline.loadUrl(EnderecoCompleto);

                // Redireciona ao webBrowser links para outras páginas
                // wvTelaOnline.setWebViewClient(new myWebView());


                // btnAbrirInterface.setVisibility(View.GONE);

                // btnAbrirInterface.setText(servidor);
                // Abrir o webview:
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
