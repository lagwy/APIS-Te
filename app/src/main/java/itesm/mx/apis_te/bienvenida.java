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
import android.widget.TextView;
import android.widget.Toast;

public class Bienvenida extends AppCompatActivity {
    String sEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        // Declaración de variables de interfaz
        TextView mensajeTV = (TextView) findViewById(R.id.bienvenidaMensajeTV);
        final Button avanzaBtn = (Button) findViewById(R.id.avanzaBienvenidaBtn);
        sEmail = "";

        // Obtener informacion del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            sEmail = extras.getString("email");
        }

        mensajeTV.setText("¡Bienvenido a Teaville!\n" +
                "\n" +
                "Teaville no es solo un juego, es una herramienta diseñada para poner en práctica" +
                " tus conocimientos sobre química. Además, pondremos a prueba todo lo que sepas" +
                " con respecto al té y sus múltiples propiedades.\n" +
                "\n" +
                "¿El reto? ¡Llenar tu jardín de los árboles y arbustos necesarios para poder " +
                "preparar tus propios tés!\n" +
                "\n" +
                "Te deseamos el mejor de los éxitos, ¡arma tu jardín!");

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (avanzaBtn.isPressed()){
                    // Toast.makeText(getApplicationContext(),"Presionado",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Bienvenida.this, Juego.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("email", sEmail);
                    startActivity(intent);
                    finish();
                }
            }
        };

        avanzaBtn.setOnClickListener(buttonListener);
    }

    public void onBackPressed(){
        // No cerrar la actividad
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