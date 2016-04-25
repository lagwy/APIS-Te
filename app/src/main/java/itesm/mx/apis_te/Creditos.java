package itesm.mx.apis_te;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Creditos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        // Variables de interfaz
        TextView descripcionCreditosTV = (TextView) findViewById(R.id.descripcionCreditosTV);
        final Button backCreditosBtn = (Button) findViewById(R.id.backCreditosBtn);

        descripcionCreditosTV.setMovementMethod(new ScrollingMovementMethod());
        descripcionCreditosTV.setText(
                "Desarrollador/Tester del producto:\n"+
                        "Jorge Luis Marquez Sanchez"+  getString(R.string.tab) + "A01139543\n"+
                        "Luis Ángel Martínez García"+  getString(R.string.tab) + "A00813485\n"+
                        "\nDocumentación del proyecto:\n"+
                        "Carlos Rodríguez Jasso"+  getString(R.string.tab) + "A01281074\n"+
                        "Eduardo Alejandro Garza"+  getString(R.string.tab) + "A00816836\n"+
                        "\nRecursos de apoyo:\n"+
                        "Alejandra Medrano Banda"+  getString(R.string.tab) + "A00516474\n"+
                        "José Enrique Rivas Mata"+  getString(R.string.tab) + "A00814757"
        );

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backCreditosBtn.isPressed()){
                    finish();
                }
            }
        };

        backCreditosBtn.setOnClickListener(buttonListener);
    }
}