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
