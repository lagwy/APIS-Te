package itesm.mx.apis_te;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Variables de interfaz
        final Button registroBtn = (Button) findViewById(R.id.btnMenuRegistro);
        final Button inicioSesionBtn = (Button) findViewById(R.id.btnMenuInicioSesion);

        // Clics al boton
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registroBtn.isPressed()){
                    Intent intent = new Intent(MainActivity.this, Registro.class);
                    startActivity(intent);
                }
                if (inicioSesionBtn.isPressed()){
                    Toast.makeText(getApplicationContext(),"Not implemented yet",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        registroBtn.setOnClickListener(buttonListener);
    }
}
