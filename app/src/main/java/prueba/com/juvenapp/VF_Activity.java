package prueba.com.juvenapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class VF_Activity extends AppCompatActivity {

    Button verdadero, falso, siguiente, salir, check;
    ImageView img;

    int ubicacion = 0;
    int malas = 0;

    Boolean[] respuestas = new Boolean[12];
    final int[] pregunta = new int[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vf_);
        getSupportActionBar().hide();

        respuestas[0] = false;
        respuestas[1] = true;
        respuestas[2] = true;
        respuestas[3] = true;
        respuestas[4] = true;
        respuestas[5] = true;
        respuestas[6] = false;
        respuestas[7] = true;
        respuestas[8] = true;
        respuestas[9] = true;
        respuestas[10] = false;
        respuestas[11] = true;

        pregunta[0] = R.drawable.uno;
        pregunta[1] = R.drawable.dos;
        pregunta[2] = R.drawable.tres;
        pregunta[3] = R.drawable.cuatro;
        pregunta[4] = R.drawable.cinco;
        pregunta[5] = R.drawable.seis;
        pregunta[6] = R.drawable.siete;
        pregunta[7] = R.drawable.ocho;
        pregunta[8] = R.drawable.nueve;
        pregunta[9] = R.drawable.diez;
        pregunta[10] = R.drawable.once;
        pregunta[11] = R.drawable.doce;

        verdadero = (Button) findViewById(R.id.btnTrue);
        falso = (Button) findViewById(R.id.btnFalse);
        salir = (Button) findViewById(R.id.btnExitTF);
        check = (Button) findViewById(R.id.btnCheck);
        siguiente = (Button) findViewById(R.id.btnSiguiente);

        img = (ImageView) findViewById(R.id.ivPregunta);

        verdadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contar(true);
            }
        });

        falso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contar(false);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ubicacion >= 11)
                {
                    int buenas = 12 - malas;
                    String o = "Suerte para la proxima";
                    if(buenas == 12)
                        o = "Felicidades";
                    Toast.makeText(VF_Activity.this, "Puntaje final: " + String.valueOf(buenas) + "\n" + o
                            , Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    ubicacion++;
                    Drawable pre = getResources().getDrawable(pregunta[ubicacion]);
                    img.setImageDrawable(pre);

                    check.setVisibility(View.INVISIBLE);
                    siguiente.setVisibility(View.INVISIBLE);
                    verdadero.setEnabled(true);
                    falso.setEnabled(true);
                }

            }
        });

    }

    public void contar(boolean res)
    {
        if (respuestas[ubicacion] == res)
        {
            Drawable d = getResources().getDrawable(R.drawable.bien);
            check.setBackground(d);
            check.setVisibility(View.VISIBLE);
            siguiente.setVisibility(View.VISIBLE);
            verdadero.setEnabled(false);
            falso.setEnabled(false);
        }
        else
        {
            Drawable d = getResources().getDrawable(R.drawable.mail);
            malas++;
            check.setBackground(d);
            check.setVisibility(View.VISIBLE);
            siguiente.setVisibility(View.VISIBLE);
            verdadero.setEnabled(false);
            falso.setEnabled(false);
        }
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
