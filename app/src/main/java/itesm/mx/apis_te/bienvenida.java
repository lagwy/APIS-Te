package itesm.mx.apis_te;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        // Declaración de variables de interfaz
        TextView mensajeTV = (TextView) findViewById(R.id.bienvenidaMensajeTV);
        final Button avanzaBtn = (Button) findViewById(R.id.avanzaBienvenidaBtn);
        String sNombre = "";

        // Obtener informacion del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            sNombre = extras.getString("nombre");
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
                    startActivity(intent);
                }
            }
        };

        avanzaBtn.setOnClickListener(buttonListener);
    }
}
