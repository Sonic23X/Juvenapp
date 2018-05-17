package prueba.com.juvenapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class Memorama_Activity extends AppCompatActivity {

    ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24;
    Integer [] cardsarray={101,102,103,104,201,202,203,204};

    Button salir;

    int ima101,ima102,ima103,ima104,ima201,ima202,ima203,ima204;

    int firtscard, secondcard;
    int firtsclick, secondclick;
    int cardnumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorama_);
        getSupportActionBar().hide();

        salir = (Button) findViewById(R.id.btnSalirM);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_14 = (ImageView) findViewById(R.id.iv_14);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_24 = (ImageView) findViewById(R.id.iv_24);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");

        frontOfCardsResources();

        Collections.shuffle(Arrays.asList(cardsarray));

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_11, theCard);
            }
        });


        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_12, theCard);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_13, theCard);
            }
        });

        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_14, theCard);
            }
        });


        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_21, theCard);
            }
        });



        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_22, theCard);
            }
        });



        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_23, theCard);
            }
        });

        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(iv_24, theCard);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void doStuff(ImageView iv, int card){
        if (cardsarray[card]== 101){
            iv.setImageResource(ima101);
        }
        else if(cardsarray[card]== 102){
            iv.setImageResource(ima102);
        }
        else if(cardsarray[card]== 103){
            iv.setImageResource(ima103);
        }
        else if(cardsarray[card]== 104){
            iv.setImageResource(ima104);
        }
        else if(cardsarray[card]== 201){
            iv.setImageResource(ima201);
        }
        else if(cardsarray[card]== 202){
            iv.setImageResource(ima202);
        }
        else if(cardsarray[card]== 203){
            iv.setImageResource(ima203);
        }
        else if(cardsarray[card]== 204){
            iv.setImageResource(ima204);
        }

        if(cardnumber==1){
            firtscard=cardsarray[card];
            if (firtscard>200){
                firtscard=firtscard -100;
            }
            cardnumber=2;
            firtsclick=card;


            iv.setEnabled(false);
        }else if(cardnumber==2){
            secondcard=cardsarray[card];
            if(secondcard>200){
                secondcard=secondcard-100;
            }

            cardnumber=1;
            secondclick=card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);


            Handler Handler = new Handler();
            Handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //check if the selected images  are equal
                    calculate();
                }
            },1000);
        }
    }


    private void calculate() {

        //si las imagenes osn iguales remover y agregar
        if (firtscard == secondcard) {
            if (firtsclick == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (firtsclick == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            }


            if (secondclick == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (secondclick == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (secondclick == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (secondclick == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (secondclick == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (secondclick == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (secondclick == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (secondclick == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            }


        }else{
            iv_11.setImageResource(R.drawable.carta_tracera);
            iv_12.setImageResource(R.drawable.carta_tracera);
            iv_13.setImageResource(R.drawable.carta_tracera);
            iv_14.setImageResource(R.drawable.carta_tracera);
            iv_21.setImageResource(R.drawable.carta_tracera);
            iv_22.setImageResource(R.drawable.carta_tracera);
            iv_23.setImageResource(R.drawable.carta_tracera);
            iv_24.setImageResource(R.drawable.carta_tracera);

        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);

        checkEnd();

    }

    private void checkEnd(){
        if (iv_11.getVisibility()== View.INVISIBLE &&
                iv_11.getVisibility()== View.INVISIBLE &&
                iv_12.getVisibility()== View.INVISIBLE &&
                iv_13.getVisibility()== View.INVISIBLE &&
                iv_14.getVisibility()== View.INVISIBLE &&
                iv_21.getVisibility()== View.INVISIBLE &&
                iv_22.getVisibility()== View.INVISIBLE &&
                iv_23.getVisibility()== View.INVISIBLE &&
                iv_24.getVisibility()== View.INVISIBLE ){
            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(Memorama_Activity.this);
            alertDialogBuilder
                    .setMessage("Fin del Juego" )
                    .setCancelable(false)
                    .setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent Intent = new Intent(getApplicationContext(),Memorama_Activity.class);
                            startActivity(Intent);
                            finish();

                        }
                    })

                    .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });
            AlertDialog  alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }

    private void frontOfCardsResources(){

        ima101 =R.drawable.ima101;
        ima102 =R.drawable.ima102;
        ima103 =R.drawable.ima103;
        ima104 =R.drawable.ima104;
        ima201 =R.drawable.ima201;
        ima202 =R.drawable.ima202;
        ima203 =R.drawable.ima203;
        ima204 =R.drawable.ima204;
    }


}
