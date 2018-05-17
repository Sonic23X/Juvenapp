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
        }, 16800);
    }

    public void siguiente()
    {
        Intent i = new Intent(Primera_pantalla.this, Segunda_pantalla.class);
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
