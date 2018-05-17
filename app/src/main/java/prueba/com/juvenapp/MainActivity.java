package prueba.com.juvenapp;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button opinion, tf, memorama, salir;

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
                Intent i = new Intent(MainActivity.this, VF_Activity.class);
                startActivity(i);
            }
        });

        memorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Memorama_Activity.class);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder
                        .setMessage("¿Realmente desea salir de la aplicación?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })

                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog  alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

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
