package teste.teste2;
// Bibliotecas
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

/**
 * Subrotina principal, respons&aacute;vel por apresentar a tela apresentar a tela inicial com os valores salvos previamente, ocultar a interface de configura&ccedil;&atilde;o e exibir a tela do servidor web.
 */
public class MainA extends ActionBarActivity {

    // Interface com o botão da interface principal
    /**
     * Bot&atilde;o virtual para abrir a interface.
     */
    private Button btnAbrirInterface;
    // Interface com a caixa de texto apikey da interface principal
    /**
     * Caixa de texto virtual para a chave de autentica&ccedil;&atilde;o.
     */
    private EditText APIKEY;
    // Nome do servidor fornecido pelo operador retirado da interface pricipal
    /**
     * String contendo a chave de autentica&ccedil;&atilde;o.
     */
    private String ApiKey = "";
    // Interface com a caixa de texto endereço do seridor da interface principal
    /**
     * Caixa de texto virtual para o endere&ccedil;o do servidor web.
     */
    private EditText Servidor;
    // Endereço do servidor fornecido pelo operador
    /**
     * Endere&ccdil;o do servidor web armazenado na mem&oacute;ria.
     */
    public static String servidor = "";
    // Tela do navegador web embutido no aplicativo
    /**
     * Navegador virtual para exibir os dados do servidor.
     */
    private WebView wvTelaOnline;
    /**
     * Apresenta a tela inicial do aplicativo, bem como configura os valores iniciais de endere&ccedil;o do servidor e chave de autentica&ccedil;&atilde;o.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cria uma interface entre os objetos da interface principal e os objetos manipulados pelo código
        APIKEY = (EditText) findViewById(R.id.txtApiKey);
        Servidor = (EditText)findViewById(R.id.txtEndereco);
        btnAbrirInterface = (Button) findViewById(R.id.btnAbrir);
        wvTelaOnline = (WebView) findViewById(R.id.wvOnline);

        // Inteface com o sistema de configurações
        SharedPreferences Opcoes = this.getPreferences(MODE_PRIVATE);
        // Carrega as configurações
        servidor = Opcoes.getString("servidor", "simon-gpsnetcms.rhcloud.com");
        ApiKey = Opcoes.getString("apikey", "c81bbccaea12ec521aa32528b8ce4b41");
        // Sincroniza as configurações com a interface gráfica
        Servidor.setText(servidor);
        APIKEY.setText(ApiKey);

        // Define a rotina do botão "abrir interface"
        btnAbrirInterface.setOnClickListener(new View.OnClickListener() {
            /**
             * Subtotina disparada ao clicar no bot&atilde;o abrir interface.
             */
            @Override
            public void onClick(View v) {
                // Endereço completo = servidor + script.php + argumentos (GET)
                String EnderecoCompleto = "";
                // Sincroniza novamente os elementos da interface principal com os objetos do código
                servidor = Servidor.getText().toString();
                ApiKey = APIKEY.getText().toString();

                // Salva as configuracoes
                SharedPreferences Opcoes = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor Editor = Opcoes.edit();

                Editor.putString("servidor", servidor);
                Editor.putString("apikey", ApiKey);
                Editor.commit();


                // Coloca a "/" no final se precisar (servidor/)
                if (servidor.charAt(servidor.length() - 1) != '/'){
                    servidor += "/";
                }
                // Coloca "http://" no inicio, se precisar (http://servidor/)
                if (!servidor.startsWith("http")){
                    servidor = "http://" + servidor;
                }
                // http://servidor/script.php?apikey=xxxx
                EnderecoCompleto = servidor + "mobile.php?apikey=" + ApiKey;

                // Os endereços serão abertos no webView e não no navegador
                wvTelaOnline.setWebViewClient(new WebViewClient());

                // Permite javascript (não tem javascript no script php atual)
                WebSettings ws = wvTelaOnline.getSettings();
                ws.setJavaScriptEnabled(true);

                // Oculta os outros elementos
                findViewById(R.id.txtEndereco).setVisibility(View.INVISIBLE);
                findViewById(R.id.txtApiKey).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnAbrir).setVisibility(View.INVISIBLE);
                findViewById(R.id.tvApiKey).setVisibility(View.INVISIBLE);
                findViewById(R.id.tvEndereco).setVisibility(View.INVISIBLE);
                findViewById(R.id.tvConfig).setVisibility(View.INVISIBLE);

                // Deixa a tela web visivel (inicialmente está invisível)
                wvTelaOnline.setVisibility(View.VISIBLE);

                // Abre o endereço remoto
                wvTelaOnline.loadUrl(EnderecoCompleto);

                // Redireciona ao webBrowser links para outras páginas
                //wvTelaOnline.setWebViewClient(new myWebView());
            }
        });
    }

    // Prevent the back-button from closing the app

    /**
     * Permite que o aplicativo seja encerrado ou o a tela do servidor apresente o valor anterior.
     */
    @Override
    public void onBackPressed() {
        if(wvTelaOnline.canGoBack()) {
            wvTelaOnline.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}

        //return super.onOptionsItemSelected(item);
    //}
}
