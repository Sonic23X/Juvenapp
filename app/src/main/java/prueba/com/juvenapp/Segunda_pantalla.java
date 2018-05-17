package prueba.com.juvenapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Segunda_pantalla extends AppCompatActivity {

    EditText name, direccion, localidad, municipio, tel, email;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_pantalla);
        getSupportActionBar().hide();

        name = (EditText) findViewById(R.id.etNombre);
        direccion = (EditText) findViewById(R.id.etDireccion);
        localidad = (EditText) findViewById(R.id.etDireccion);
        municipio = (EditText) findViewById(R.id.etMunicipio);
        tel = (EditText) findViewById(R.id.etNumero);
        email = (EditText) findViewById(R.id.etEmail);

        siguiente = (Button) findViewById(R.id.btnNextR);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || tel.getText().toString().isEmpty()
                        || direccion.getText().toString().isEmpty() || localidad.getText().toString().isEmpty() ||
                        municipio.getText().toString().isEmpty()))
                {
                    JSONObject json =  new JSONObject();
                    try
                    {
                        String enviar = direccion.getText().toString() + ", " + localidad.getText().toString() + ", "
                                + municipio.getText().toString();
                        json.put("nombre", name.getText().toString());
                        json.put("email", email.getText().toString());
                        json.put("telefono", tel.getText().toString());
                        json.put("direccion", enviar);
                    }
                    catch (JSONException ex)
                    {
                        Toast.makeText(Segunda_pantalla.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    new enviar().execute("http://uriangatoindependiente.com/Webservice/register.php", json.toString());

                }
                else
                {
                    Toast.makeText(Segunda_pantalla.this, "Todos los campos deben ser llenados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void avisar(String res)
    {
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean inicio = false;

        if(res.contains("Error, ya esta registrado en el sistema"))
        {
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
            inicio = true;
        }
        else
        {
            String [] parts = res.split("-");
            char num = res.charAt(0);
            if(num == '1')
            {
                Intent i = new Intent(Segunda_pantalla.this, Tercera_pantalla.class);
                i.putExtra("registro", true);
                i.putExtra("id" , parts[1]);
                startActivity(i);
                inicio = false;
            }
            else
            {
                Toast.makeText(this, res, Toast.LENGTH_LONG).show();
                Log.e("Error", res);
            }
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("inicio", inicio);
        editor.commit();

        finish();
    }

    private class enviar extends AsyncTask<String, Void, String>
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
