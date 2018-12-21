package zenonideas.zshop_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.HttpGet;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class Login extends AppCompatActivity {
    DB_Helper db;
    Button btn1;
    EditText un,pw;
    TextView sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn1=findViewById(R.id.login_btn1);
        un=findViewById(R.id.login_un1);
        pw=findViewById(R.id.login_pw1);
        sign_up=findViewById(R.id.l_signup);

        db = new DB_Helper(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginService().execute();
            }
        });
    }

    public class LoginService extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... Args) {
            String output = null;
            try {
                String l_url = getString(R.string.ip1)+"Login_Service";

                URIBuilder builder = new URIBuilder(l_url);   //.net servlet url here
                builder.setParameter("un", un.getText().toString());
                builder.setParameter("pw", pw.getText().toString());
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(builder.build());
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return output;
        }

        @Override
        protected void onPostExecute(String s)
        {
            try
            {
                if (s.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Check Network Your Connection", Toast.LENGTH_SHORT).show();
                }else {
//                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    if (s.equalsIgnoreCase("0")){
                        Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
                    }else{
                        JSONObject reader = new JSONObject(s);
                        int id = (int) reader.get("id");
                        String un = reader.get("un").toString();
                        String pw= reader.get("pw").toString();
                        String uid= reader.get("uid").toString();
                        String rid = reader.get("rid").toString();
                        String st1 = reader.get("st").toString();

//                        Toast.makeText(getApplicationContext(), id+" - "+un+" - "+pw+" - "+uid+" - "+rid+" - "+st1, Toast.LENGTH_SHORT).show();

                        boolean st = db.insertLogin(id, un, pw, uid, rid, st1);

                        if (st){
                            Intent i1=new Intent(getApplicationContext(),M_Menu.class);
                        startActivity(i1);
                        }else{
                            Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
                        }
//
                    }

                }
            } catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Check Network Your Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
