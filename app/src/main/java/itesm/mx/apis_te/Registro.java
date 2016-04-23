package itesm.mx.apis_te;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Declaración de variables de interfaz
        final EditText nombreET = (EditText) findViewById(R.id.nombreRegistroET);
        final EditText emailET = (EditText) findViewById(R.id.correoRegistroET);
        final EditText passwordET = (EditText) findViewById(R.id.passwordRegistroET);
        final EditText confirmaPwdET = (EditText) findViewById(R.id.confirmapwdRegistroET);
        final Button registraBtn = (Button) findViewById(R.id.registroBtn);
        final Button regresaBtn = (Button) findViewById(R.id.backRegistroBtn);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registraBtn.isPressed()) {
                    String sNombreRegistro = nombreET.getText().toString();
                    String sEmailRegistro = emailET.getText().toString();
                    String sPasswordRegistro = passwordET.getText().toString();
                    String sConfirmaPwdRegistro = confirmaPwdET.getText().toString();
                    if (revisaVacio(sNombreRegistro) && revisaVacio(sEmailRegistro)
                            && revisaVacio(sPasswordRegistro) && revisaVacio(sConfirmaPwdRegistro)) {
                        if (samePwd(sPasswordRegistro, sConfirmaPwdRegistro)) {
                            // Aquí se llama el registro de la base de datos
                            new Insertar(Registro.this, sNombreRegistro, sEmailRegistro, sPasswordRegistro).execute();
                        } else {
                            Toast.makeText(getApplicationContext(), "Los campos de contraseña no" +
                                            " coinciden.",
                                    Toast.LENGTH_SHORT).show();
                        }

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

        registraBtn.setOnClickListener(buttonListener);
        regresaBtn.setOnClickListener(buttonListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    Boolean revisaVacio(String string) {
        if (string.trim().matches(""))
            return false;
        return true;
    }

    Boolean samePwd(String pwd, String confirm) {
        if (pwd.matches(confirm))
            return true;
        return false;
    }

    private String inserta(String sNombre, String sEmail, String sPassword) {
        HttpClient httpClient;
        List<NameValuePair> nameValuePairList;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost("http://lagwy.com/teaville/inserta.php");
        // Añadir los datos
        nameValuePairList = new ArrayList<NameValuePair>(3);
        nameValuePairList.add(new BasicNameValuePair("nombre", sNombre));
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Registro Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://itesm.mx.apis_te/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Registro Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://itesm.mx.apis_te/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class Insertar extends AsyncTask<String, String, String> {
        private Activity context;
        private String sNombre;
        private String sEmail;
        private String sPassword;

        Insertar(Activity context, String sNombre, String sEmail, String sPassword) {
            this.context = context;
            this.sNombre = sNombre;
            this.sEmail = sEmail;
            this.sPassword = sPassword;
        }

        @Override
        protected String doInBackground(String... params) {
            final String resultado = inserta(sNombre, sEmail, sPassword);
            if (resultado.matches("true"))
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Usuario añadido con éxito",  Toast.LENGTH_SHORT).show();
                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "No fue posible añadir el usuario", Toast.LENGTH_SHORT).show();
                    }
                });
            return null;
        }
    }
}
