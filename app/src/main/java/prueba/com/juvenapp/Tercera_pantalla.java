package prueba.com.juvenapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tercera_pantalla extends AppCompatActivity {

    Button op1, op2, op3, op4;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercera_pantalla);
        getSupportActionBar().hide();

        op1 = (Button) findViewById(R.id.btnGorra);
        op2 = (Button) findViewById(R.id.btnTaza);
        op3 = (Button) findViewById(R.id.btnCilindro);
        op4 = (Button) findViewById(R.id.btnDelantal);

        id = getIntent().getStringExtra("id");

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(3);
            }
        });

        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(4);
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(2);
            }
        });

        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(1);
            }
        });

    }

    public void guardar(int sel)
    {
        JSONObject json =  new JSONObject();
        try
        {
            json.put("usuario", id);
            json.put("beneficio", sel);
        }
        catch (JSONException ex)
        {
            Toast.makeText(Tercera_pantalla.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        new RegistrarBeneficio().execute("http://uriangatoindependiente.com/Webservice/benefic.php", json.toString());

    }

    public void avisar(String res)
    {
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean inicio;

        char num = res.charAt(0);
        if(num == '1')
        {
            inicio = true;
        }
        else
        {
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
            Log.e("Error", res);
            inicio = false;
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("inicio", inicio);
        editor.commit();

        finish();
    }

    private class RegistrarBeneficio extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String url = strings[0];
            String json = strings[1];
            BufferedReader in = null;

            try
            {

                HttpClient cliente = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
                nvp.add(new BasicNameValuePair("datos", json.toString()));

                post.setEntity(new UrlEncodedFormEntity(nvp));

                HttpResponse envio = cliente.execute(post);

                in = new BufferedReader(new InputStreamReader(envio.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                return sb.toString();

            }
            catch (IOException ex)
            {
                return "error: " + ex.getMessage();
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        protected void onPostExecute(String result) {
            avisar(result);
        }
    }

}
