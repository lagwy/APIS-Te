package itesm.mx.apis_te;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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

public class Pregunta extends AppCompatActivity {
    ProgressDialog pDialog;
    TextView descripcionPreguntaTV;
    Button firstOptionBtn;
    Button secondOptionBtn;
    Button thirdOptionBtn;
    Button fourthOptionBtn;
    int iCorrecta = 0;
    String tipoDePregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        // Variables
        String sTitulo = "";
        tipoDePregunta = "";
        descripcionPreguntaTV = (TextView) findViewById(R.id.descripcionPreguntaTV);
        firstOptionBtn = (Button) findViewById(R.id.firstOptionBtn);
        secondOptionBtn = (Button) findViewById(R.id.secondOptionBtn);
        thirdOptionBtn = (Button) findViewById(R.id.thirdOptionBtn);
        fourthOptionBtn = (Button) findViewById(R.id.fourthOptionBtn);
        TextView tituloPreguntaTV = (TextView) findViewById(R.id.tituloPreguntaTV);

        // Obtener informacion del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            sTitulo = extras.getString("tipo");
        }

        tituloPreguntaTV.setText(sTitulo);
        new obtenPregunta(Pregunta.this, sTitulo).execute();

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstOptionBtn.isPressed()){
                    if (iCorrecta == 1){
                        Toast.makeText(getApplicationContext(), "Acertaste!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                if (secondOptionBtn.isPressed()){
                    if (iCorrecta == 2){
                        Toast.makeText(getApplicationContext(), "Acertaste!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                if (thirdOptionBtn.isPressed()){
                    if (iCorrecta == 3){
                        Toast.makeText(getApplicationContext(), "Acertaste!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                if (fourthOptionBtn.isPressed()){
                    if (iCorrecta == 4){
                        Toast.makeText(getApplicationContext(), "Acertaste!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        };

        firstOptionBtn.setOnClickListener(buttonListener);
        secondOptionBtn.setOnClickListener(buttonListener);
        thirdOptionBtn.setOnClickListener(buttonListener);
        fourthOptionBtn.setOnClickListener(buttonListener);
    }

    private String buscaPregunta(String sTipo) {
        HttpClient httpClient;
        List<NameValuePair> nameValuePairList;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost("http://lagwy.com/teaville/obtenPregunta.php");
        // Añadir los datos
        nameValuePairList = new ArrayList<NameValuePair>(1);
        nameValuePairList.add(new BasicNameValuePair("tipo", sTipo));

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

    class obtenPregunta extends AsyncTask<String, String, String> {
        private Activity context;
        private String sTipo;

        obtenPregunta(Activity context, String sTipo) {
            this.context = context;
            this.sTipo = sTipo;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(Pregunta.this);
            pDialog.setMessage("Cargando...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final String resultado = buscaPregunta(sTipo);
            if (!resultado.matches("false"))
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Toast.makeText(context, resultado,  Toast.LENGTH_SHORT).show();
                        String[] sPregunta = resultado.split("#");
                        // 6 datos en el arreglo
                        // Descrip, R1, R2, R3, R4, RC
                        descripcionPreguntaTV.setText(sPregunta[0]);
                        //Toast.makeText(context,"0"+ sPregunta[0],  Toast.LENGTH_SHORT).show();
                        firstOptionBtn.setText("A) " + sPregunta[1]);
                        //Toast.makeText(context,"1"+ sPregunta[1],  Toast.LENGTH_SHORT).show();
                        secondOptionBtn.setText("B) " + sPregunta[2]);
                        //Toast.makeText(context,"2"+ sPregunta[2],  Toast.LENGTH_SHORT).show();
                        thirdOptionBtn.setText("C) " + sPregunta[3]);
                        //Toast.makeText(context,"3"+ sPregunta[3],  Toast.LENGTH_SHORT).show();
                        fourthOptionBtn.setText("D) " + sPregunta[4]);
                        //Toast.makeText(context,"4"+ sPregunta[4],  Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context,"5"+ sPregunta[5],  Toast.LENGTH_SHORT).show();
                        iCorrecta = Integer.parseInt(sPregunta[5]);
                        tipoDePregunta = sPregunta[6];
                        //Toast.makeText(context,"6 "+ sPregunta[6],  Toast.LENGTH_SHORT).show();
                        if (sPregunta[6].matches("VF")){
                            // Ocultar los dos botones que no se utilizan por ser true o false
                            thirdOptionBtn.setVisibility(View.GONE);
                            fourthOptionBtn.setVisibility(View.GONE);
                        }
                        pDialog.dismiss();
                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "No hay preguntas disponibles",
                                Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                        finish();
                    }
                });
            return null;
        }
    }
}
