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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        // Variables de Buttons, TextView, EditText
        backBtn = (Button) findViewById(R.id.backCuentaBtn);
        creditosBtn = (Button) findViewById(R.id.creditosCuentaBtn);
        ayudaBtn = (Button) findViewById(R.id.ayudaCuentaBtn);
        guardarBtn = (Button) findViewById(R.id.guardarCuentaBtn);
        nombreTV = (TextView) findViewById(R.id.nombreCuentaTV);
        emailTV = (TextView) findViewById(R.id.emailCuentaTV);
        actualPwdET = (EditText) findViewById(R.id.passwordCuentaET);
        nuevaPwdET = (EditText) findViewById(R.id.nuevapwdCuentaET);
        confirmaPwdET = (EditText) findViewById(R.id.confirmapwdET);

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
                    Toast.makeText(getApplicationContext(), "Guardaaar", Toast.LENGTH_SHORT).show();
                }
            }
        };

        backBtn.setOnClickListener(listener);
        creditosBtn.setOnClickListener(listener);
        ayudaBtn.setOnClickListener(listener);
        guardarBtn.setOnClickListener(listener);

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