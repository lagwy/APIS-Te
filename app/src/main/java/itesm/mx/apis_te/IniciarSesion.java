/*
Teaville - Proyecto de la materia de Administración de Proyectos de Ingeniería de Software
        Copyright (C) 2016 - ITESM
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.
        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.
        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.
        Authors:
        ITESM representatives
        Ing. Jakeline Marcos Abed <jakeline@itesm.mx>
        Ing. Mario de la Fuente <mario.delafuente@itesm.mx>
        ITESM students
        Luis Angel Martinez Garcia <a00813485@itesm.mx>
        Jorge Luis Marquez Sanchez <a01139543@itesm.mx>
*/
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
                        Toast.makeText(context, "Sesión iniciada.",  Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IniciarSesion.this, Bienvenida.class);
                        intent.putExtra("email", sEmail);
                        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        pDialog.dismiss();
                        startActivity(intent);
                        finish();
                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "El usuario no se encuentra en la base de datos",
                                Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                });
            return null;
        }
    }

}

/*
 * Copyright (C) 2016 ITESM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */