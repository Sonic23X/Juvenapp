package prueba.com.juvenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Sexta_pantalla extends AppCompatActivity {

    Button trabajo, educacion, infraestructura, seguridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexta_pantalla);
        getSupportActionBar().hide();

        trabajo = (Button) findViewById(R.id.btnTrabajo);
        educacion = (Button) findViewById(R.id.btnEdu);
        infraestructura = (Button) findViewById(R.id.btnInfra);
        seguridad = (Button) findViewById(R.id.btnSeguridad);

        trabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente(1);
            }
        });

        educacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente(2);
            }
        });

        infraestructura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente(3);
            }
        });

        seguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente(4);
            }
        });

    }

    public void siguiente(int sel)
    {
        Intent i = new Intent(Sexta_pantalla.this, PropuestActivity.class);
        i.putExtra("sel", sel);
        startActivity(i);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        Intent i = new Intent(this, Sound.class);
        i.putExtra("action", Sound.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent i = new Intent(this, Sound.class);
        i.putExtra("action", Sound.START);
        startService(i);
    }
}
