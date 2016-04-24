package itesm.mx.apis_te;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class bienvenida extends AppCompatActivity {

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

        mensajeTV.setText(sNombre + ": Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec " +
                "nisl sapien. Praesent a dapibus odio. Fusce at mauris vel orci sollicitudin " +
                "iaculis ac ut elit. Vestibulum placerat rhoncus finibus. Nullam vehicula elit " +
                "sit amet erat ornare bibendum. Aliquam porttitor dui nec luctus gravida. " +
                "Vestibulum scelerisque tellus quis maximus semper. ");

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (avanzaBtn.isPressed()){
                    Toast.makeText(getApplicationContext(),"Presionado",Toast.LENGTH_SHORT).show();
                }
            }
        };

        avanzaBtn.setOnClickListener(buttonListener);
    }
}
