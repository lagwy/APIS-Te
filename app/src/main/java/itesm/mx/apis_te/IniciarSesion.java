package itesm.mx.apis_te;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 24/04/2016.
 */
public class IniciarSesion extends AppCompatActivity {
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        // Declaración de variables de interfaz
        final EditText emailET = (EditText) findViewById(R.id.correoIniciarET);
        final EditText passwordET = (EditText) findViewById(R.id.passwordIniciarET);
        final Button iniciarBtn = (Button) findViewById(R.id.iniciarSesionBtn);
        final Button regresaBtn = (Button) findViewById(R.id.backIniciarBtn);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iniciarBtn.isPressed()) {
                    String sEmailIniciar = emailET.getText().toString();
                    String sPasswordIniciar = passwordET.getText().toString();
                    if (revisaVacio(sEmailIniciar) && revisaVacio(sPasswordIniciar)) {
                        new Insertar(IniciarSesion.this,sEmailIniciar, sPasswordIniciar).execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "No están completos los campos",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                if (regresaBtn.isPressed()) {
                    finish();
                }
            }
        };

        iniciarBtn.setOnClickListener(buttonListener);
        regresaBtn.setOnClickListener(buttonListener);

    }

    Boolean revisaVacio (String string){
        if (string.trim().matches(""))
            return false;
        return true;
    }

    Boolean samePwd (String pwd, String confirm){
        if (pwd.matches(confirm))
            return true;
        return false;
    }

    private String busca(String sEmail, String sPassword) {
        HttpClient httpClient;
        List<NameValuePair> nameValuePairList;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost("http://lagwy.com/teaville/revisa.php");
        // Añadir los datos
        nameValuePairList = new ArrayList<NameValuePair>(3);
        nameValuePairList.add(new BasicNameValuePair("email", sEmail));
        nameValuePairList.add(new BasicNameValuePair("password", sPassword));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    class Insertar extends AsyncTask<String, String, String> {
        private Activity context;
        private String sEmail;
        private String sPassword;

        Insertar(Activity context, String sEmail, String sPassword) {
            this.context = context;
            this.sEmail = sEmail;
            this.sPassword = sPassword;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(IniciarSesion.this);
            pDialog.setMessage("Cargando...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final String resultado = busca(sEmail, sPassword);
            if (resultado.matches("true"))
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Iniciando sesión...",  Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IniciarSesion.this, Bienvenida.class);
                        intent.putExtra("nombre", sEmail);
                        pDialog.dismiss();
                        startActivity(intent);
                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "El usuario no se encuentra en la base de datos",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            return null;
        }
    }

}
