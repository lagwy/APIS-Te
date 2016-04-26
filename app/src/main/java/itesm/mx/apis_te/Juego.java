package itesm.mx.apis_te;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        final ImageView anisIV = (ImageView) findViewById(R.id.masetaIV1);      // Anis
        final ImageView camelliaIV = (ImageView) findViewById(R.id.masetaIV2);  // Camellia
        final ImageView ginsengIV = (ImageView) findViewById(R.id.masetaIV3);   // Ginseng
        final ImageView jazminIV = (ImageView) findViewById(R.id.masetaIV4);    // Jazmin
        final ImageView limonIV = (ImageView) findViewById(R.id.masetaIV5);     // Limon
        final ImageView manzanillaIV = (ImageView) findViewById(R.id.masetaIV6);// Manzanilla

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
}
