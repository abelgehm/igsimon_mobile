package teste.teste2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class MainA extends ActionBarActivity {

    private Button btnAbrirInterface;
    private EditText APIKEY;
    private EditText Servidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIKEY=(EditText) findViewById(R.id.txtApiKey);
        Servidor=(EditText)findViewById(R.id.txtEndereco);
        btnAbrirInterface=(Button) findViewById(R.id.btnAbrir);

        btnAbrirInterface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAbrirInterface.setText(Servidor.getText());
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

    public void OnClick (View v){
        if (v==btnAbrirInterface)
        {
            btnAbrirInterface.setText("OK");
        }
    }
}
