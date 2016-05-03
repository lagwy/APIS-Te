package itesm.mx.apis_te;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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

public class Juego extends AppCompatActivity {
    ImageView anisIV;
    ImageView camelliaIV;
    ImageView ginsengIV;
    ImageView jazminIV;
    ImageView limonIV;
    ImageView manzanillaIV;
    Button anisBtn;
    Button camelliaBtn;
    Button ginsengBtn;
    Button jazminBtn;
    Button limonBtn;
    Button manzanillaBtn;
    String sEmail;
    Button settingsBtn;
    ProgressDialog pDialog;
    int score1, score2, score3, score4, score5, score6;
    private final static int ACTUALIZA = 7;
    int iCerrarSesion; // Variable para cuando se presiona el botón de back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        sEmail = "";
        // Variables de interfaz
        anisBtn = (Button) findViewById(R.id.anisScoreBtn);
        camelliaBtn = (Button) findViewById(R.id.camelliaScoreBtn);
        ginsengBtn = (Button) findViewById(R.id.ginsengScoreBtn);
        jazminBtn = (Button) findViewById(R.id.jazminScoreBtn);
        limonBtn = (Button) findViewById(R.id.limonScoreBtn);
        manzanillaBtn = (Button) findViewById(R.id.manzanillaScoreBtn);
        settingsBtn = (Button) findViewById(R.id.settingsBtn);

        // Obtener informacion del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sEmail = extras.getString("email");
        }
        new obtenScore(Juego.this, sEmail).execute();
        // Inicializar variables en threads
        iCerrarSesion = 0;
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                anisIV = (ImageView) findViewById(R.id.masetaIV1);      // Anis
                camelliaIV = (ImageView) findViewById(R.id.masetaIV2);  // Camellia
                ginsengIV = (ImageView) findViewById(R.id.masetaIV3);   // Ginseng
            }
        });

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                jazminIV = (ImageView) findViewById(R.id.masetaIV4);    // Jazmin
                limonIV = (ImageView) findViewById(R.id.masetaIV5);     // Limon
                manzanillaIV = (ImageView) findViewById(R.id.masetaIV6);// Manzanilla
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (anisIV.isPressed()) {
                    if (score1 != 100){
                        Intent intent = new Intent(Juego.this, Pregunta.class);
                        String sTe = "";
                        sTe = "Anis";
                        intent.putExtra("tipo", sTe);
                        intent.putExtra("email", sEmail);
                        startActivityForResult(intent, ACTUALIZA);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has completado este té", Toast.LENGTH_SHORT).show();
                    }
                }
                if (camelliaIV.isPressed()) {
                    if (score2 != 100){
                        Intent intent = new Intent(Juego.this, Pregunta.class);
                        String sTe = "";
                        sTe = "Camellia Sinensis";
                        intent.putExtra("tipo", sTe);
                        intent.putExtra("email", sEmail);
                        startActivityForResult(intent, ACTUALIZA);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has completado este té", Toast.LENGTH_SHORT).show();
                    }
                }
                if (ginsengIV.isPressed()) {
                    if (score3 != 100){
                        Intent intent = new Intent(Juego.this, Pregunta.class);
                        String sTe = "";
                        sTe = "Ginseng";
                        intent.putExtra("tipo", sTe);
                        intent.putExtra("email", sEmail);
                        startActivityForResult(intent, ACTUALIZA);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has completado este té", Toast.LENGTH_SHORT).show();
                    }
                }
                if (jazminIV.isPressed()) {
                    if (score4 != 100){
                        Intent intent = new Intent(Juego.this, Pregunta.class);
                        String sTe = "";
                        sTe = "Jazmin";
                        intent.putExtra("tipo", sTe);
                        intent.putExtra("email", sEmail);
                        startActivityForResult(intent, ACTUALIZA);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has completado este té", Toast.LENGTH_SHORT).show();
                    }
                }
                if (limonIV.isPressed()) {
                    if (score5 != 100){
                        Intent intent = new Intent(Juego.this, Pregunta.class);
                        String sTe = "";
                        sTe = "Limon";
                        intent.putExtra("tipo", sTe);
                        intent.putExtra("email", sEmail);
                        startActivityForResult(intent, ACTUALIZA);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has completado este té", Toast.LENGTH_SHORT).show();
                    }
                }
                if (manzanillaIV.isPressed()) {
                    if (score6 != 100){
                        Intent intent = new Intent(Juego.this, Pregunta.class);
                        String sTe = "";
                        sTe = "Manzanilla";
                        intent.putExtra("tipo", sTe);
                        intent.putExtra("email", sEmail);
                        startActivityForResult(intent, ACTUALIZA);
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has completado este té", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        View.OnClickListener listenerSettings = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Juego.this, Cuenta.class);
                intent.putExtra("email", sEmail);
                startActivity(intent);
            }
        };

        anisIV.setOnClickListener(listener);
        camelliaIV.setOnClickListener(listener);
        ginsengIV.setOnClickListener(listener);
        jazminIV.setOnClickListener(listener);
        limonIV.setOnClickListener(listener);
        manzanillaIV.setOnClickListener(listener);
        settingsBtn.setOnClickListener(listenerSettings);
    }

    public void onBackPressed() {
        // No cerrar la actividad
        iCerrarSesion++;
        if (iCerrarSesion > 2) {
            // Mandar dialogo de confirmación
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Cerrar sesión")
                    .setIcon(R.mipmap.teaville_logo)
                    .setMessage("¿Estás seguro que deseas cerrar la sesión?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            iCerrarSesion = 0;
                        }
                    })
                    .show();
        }
    }


    private String buscaScores(String sCorreo) {
        HttpClient httpClient;
        List<NameValuePair> nameValuePairList;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost("http://lagwy.com/teaville/obtenScore.php");
        // Añadir los datos
        nameValuePairList = new ArrayList<NameValuePair>(1);
        nameValuePairList.add(new BasicNameValuePair("email", sCorreo));

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if (resultCode == RESULT_OK){
            new obtenScore(Juego.this, sEmail).execute();
        }
    }

    class obtenScore extends AsyncTask<String, String, String> {
        private Activity context;
        private String sCorreo;

        obtenScore(Activity context, String sCorreo) {
            this.context = context;
            this.sCorreo = sCorreo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Juego.this);
            pDialog.setMessage("Cargando...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final String resultado = buscaScores(sCorreo);
            if (!resultado.matches("false"))
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Toast.makeText(context, resultado,  Toast.LENGTH_SHORT).show();
                        String[] sScores = resultado.split("#");
                        // 6 datos en el arreglo, todos enteros
                        // Descrip, R1, R2, R3, R4, RC
                        score1 = (int) Double.parseDouble(sScores[0]);
                        score2 =  (int)Double.parseDouble(sScores[1]);
                        score3 = (int)Double.parseDouble(sScores[2]);
                        score4 =  (int)Double.parseDouble(sScores[3]);
                        score5 = (int)Double.parseDouble(sScores[4]);
                        score6 = (int)Double.parseDouble(sScores[5]);
                        anisBtn.setText("Anis\n" + score1 + "%");
                        camelliaBtn.setText("Camellia\n" + score2 + "%");
                        ginsengBtn.setText("Ginseng\n" + score3 + "%");
                        jazminBtn.setText("Jazmin\n" + score4 + "%");
                        limonBtn.setText("Limon\n" + score5 + "%");
                        manzanillaBtn.setText("Manzanilla\n" + score6 + "%");
                        pDialog.dismiss();
                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "No fue posible cargar los puntajes.",
                                Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                        finish();
                    }
                });
            return null;
        }
    }
}
