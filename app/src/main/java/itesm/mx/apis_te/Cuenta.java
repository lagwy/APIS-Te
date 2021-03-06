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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Cuenta extends AppCompatActivity {
    Button backBtn;
    Button creditosBtn;
    Button ayudaBtn;
    Button guardarBtn;
    TextView nombreTV;
    TextView emailTV;
    EditText actualPwdET;
    EditText nuevaPwdET;
    EditText confirmaPwdET;
    ProgressDialog pDialog;
    String sEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        // Variables de Buttons, TextView, EditText
        backBtn = (Button) findViewById(R.id.backCuentaBtn);
        creditosBtn = (Button) findViewById(R.id.creditosCuentaBtn);
        ayudaBtn = (Button) findViewById(R.id.ayudaCuentaBtn);
        guardarBtn = (Button) findViewById(R.id.guardarCuentaBtn);
        nombreTV = (TextView) findViewById(R.id.nombreTV);
        emailTV = (TextView) findViewById(R.id.emailTV);
        actualPwdET = (EditText) findViewById(R.id.passwordCuentaET);
        nuevaPwdET = (EditText) findViewById(R.id.nuevapwdCuentaET);
        confirmaPwdET = (EditText) findViewById(R.id.confirmapwdET);

        // Obtener informacion del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sEmail = extras.getString("email");
        }

        emailTV.setText(sEmail);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backBtn.isPressed()){
                    finish();
                }
                if (creditosBtn.isPressed()){
                    Intent intent = new Intent(Cuenta.this, Creditos.class);
                    startActivity(intent);
                }
                if (ayudaBtn.isPressed()){
                    Toast.makeText(getApplicationContext(), "Aún no se implementa la pantalla", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Cuenta.this, Ayuda.class);
                    startActivity(intent);
                }
                if (guardarBtn.isPressed()){
                    String passActual = actualPwdET.getText().toString();
                    String nuevaPass = nuevaPwdET.getText().toString();
                    String confirmaPass = confirmaPwdET.getText().toString();

                    if (revisaVacio(passActual) || revisaVacio(nuevaPass) || revisaVacio(confirmaPass)){
                        if (nuevaPass.matches(confirmaPass)){
                            if (nuevaPass.matches(passActual)){
                                Toast.makeText(getApplicationContext(), "La contraseña nueva y la actual son identicas",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                new Actualiza(Cuenta.this, sEmail, nuevaPass, passActual).execute();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "No hay campos completos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        backBtn.setOnClickListener(listener);
        creditosBtn.setOnClickListener(listener);
        ayudaBtn.setOnClickListener(listener);
        guardarBtn.setOnClickListener(listener);

    }

    private String actualizaPwd(String sEmail, String sPassword, String sPwdActual) {
        HttpClient httpClient;
        List<NameValuePair> nameValuePairList;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost("http://lagwy.com/teaville/actualizaPwd.php");
        // Añadir los datos
        nameValuePairList = new ArrayList<NameValuePair>(3);
        nameValuePairList.add(new BasicNameValuePair("email", sEmail));
        nameValuePairList.add(new BasicNameValuePair("password", sPassword));
        nameValuePairList.add(new BasicNameValuePair("actual", sPwdActual));

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

    class Actualiza extends AsyncTask<String, String, String> {
        private Activity context;
        private String sEmail;
        private String sPassword;
        private String sPwdActual;

        Actualiza(Activity context, String sEmail, String sPassword, String sPwdActual) {
            this.context = context;
            this.sEmail = sEmail;
            this.sPassword = sPassword;
            this.sPwdActual = sPwdActual;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(Cuenta.this);
            pDialog.setMessage("Cargando...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final String resultado = actualizaPwd(sEmail, sPassword, sPwdActual);
            if (resultado.matches("true"))
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Información actualizada correctamente.",
                                Toast.LENGTH_SHORT).show();
                        actualPwdET.setText("");
                        nuevaPwdET.setText("");
                        confirmaPwdET.setText("");
                        pDialog.dismiss();

                    }
                });
            else
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "No fue posible actualizar la información.",
                                Toast.LENGTH_SHORT).show();
                        actualPwdET.setText("");
                        nuevaPwdET.setText("");
                        confirmaPwdET.setText("");
                        pDialog.dismiss();
                    }
                });
            return null;
        }
    }

    Boolean revisaVacio(String string) {
        if (string.trim().matches(""))
            return false;
        return true;
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