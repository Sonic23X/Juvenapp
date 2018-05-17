package prueba.com.juvenapp;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button opinion, tf, memorama, cuarto, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        opinion = (Button) findViewById(R.id.btnPR);
        tf = (Button) findViewById(R.id.btnTF);
        memorama = (Button) findViewById(R.id.btnMemorama);
        salir = (Button) findViewById(R.id.btnExit);

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        boolean inicio = preferences.getBoolean("inicio", false);

        if(!inicio)
        {
            Intent i = new Intent(MainActivity.this, Primera_pantalla.class);
            startActivity(i);
        }

        opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sexta_pantalla.class);
                startActivity(i);
            }
        });

        tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        memorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
