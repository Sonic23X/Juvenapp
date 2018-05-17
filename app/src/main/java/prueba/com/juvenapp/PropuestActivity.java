package prueba.com.juvenapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PropuestActivity extends AppCompatActivity {

    Button subir, salir;
    EditText opinion;

    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuest);
        getSupportActionBar().hide();

        num = getIntent().getIntExtra("sel", 3);

        opinion = (EditText) findViewById(R.id.etOpinion);
        subir = (Button) findViewById(R.id.btnSendO);
        salir = (Button) findViewById(R.id.btnBack);

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!opinion.getText().toString().isEmpty())
                {
                    JSONObject json =  new JSONObject();
                    try
                    {
                        json.put("categoria", num);
                        json.put("opinion", opinion.getText().toString());
                    }
                    catch (JSONException ex)
                    {
                        Toast.makeText(PropuestActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    new Register_Opinion().execute("http://uriangatoindependiente.com/Webservice/opination.php", json.toString());
                }
                else
                    Toast.makeText(PropuestActivity.this, "Escriba su opinion para enviar", Toast.LENGTH_LONG).show();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void fin(String res)
    {
        char a = res.charAt(0);
        if(a == '1')
        {
            Intent i = new Intent(PropuestActivity.this, ThankActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Error, intente más tarde", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private class Register_Opinion extends AsyncTask<String, Void, String>
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
            fin(result);
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
