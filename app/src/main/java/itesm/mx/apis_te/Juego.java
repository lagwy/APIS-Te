package itesm.mx.apis_te;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Juego extends AppCompatActivity {
    ImageView anisIV;
    ImageView camelliaIV;
    ImageView ginsengIV;
    ImageView jazminIV;
    ImageView limonIV;
    ImageView manzanillaIV;
    int iCerrarSesion; // Variable para cuando se presiona el botón de back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

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
                Intent intent = new Intent(Juego.this, Pregunta.class);
                String sTe = "";
                if (anisIV.isPressed()){
                    sTe = "Anis";
                }
                if (camelliaIV.isPressed()){
                    sTe = "Camellia Sinensis";
                }
                if (ginsengIV.isPressed()){
                    sTe = "Ginseng";
                }
                if (jazminIV.isPressed()){
                    sTe = "Jazmin";
                }
                if (limonIV.isPressed()){
                    sTe = "Limon";
                }
                if (manzanillaIV.isPressed()){
                    sTe = "Manzanilla";
                }
                intent.putExtra("tipo",sTe);
                startActivity(intent);
            }
        };

        anisIV.setOnClickListener(listener);
        camelliaIV.setOnClickListener(listener);
        ginsengIV.setOnClickListener(listener);
        jazminIV.setOnClickListener(listener);
        limonIV.setOnClickListener(listener);
        manzanillaIV.setOnClickListener(listener);
    }

    public void onBackPressed(){
        // No cerrar la actividad
        iCerrarSesion++;
        if (iCerrarSesion > 2){
            // Mandar dialogo de confirmación
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Cerrar sesión")
                    .setIcon(R.mipmap.teaville_logo)
                    .setMessage("¿Estás seguro que deseas cerrar la sesión?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            iCerrarSesion = 0;
                        }
                    })
                    .show();
        }
    }
}
