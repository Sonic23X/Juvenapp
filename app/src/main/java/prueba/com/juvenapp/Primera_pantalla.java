package prueba.com.juvenapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Primera_pantalla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primera_pantalla);
        getSupportActionBar().hide();
        cerrar();
    }

    public void cerrar()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                siguiente();
            }
        }, 16500);
    }

    public void siguiente()
    {
        Intent i = new Intent(Primera_pantalla.this, Segunda_pantalla.class);
        startActivity(i);

        finish();
    }
}
