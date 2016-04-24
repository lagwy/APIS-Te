package itesm.mx.apis_te;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jorge on 24/04/2016.
 */
public class IniciarSesion extends AppCompatActivity {
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
                        Toast.makeText(getApplicationContext(), "Datos completos",
                                Toast.LENGTH_SHORT).show();
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
}
