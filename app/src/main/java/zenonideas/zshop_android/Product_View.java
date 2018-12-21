package zenonideas.zshop_android;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.HttpGet;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class Product_View extends AppCompatActivity {
    RecyclerView res;
    List<C_Products> l;
    static int si = 0;
    Button prev, next1;
    ImageButton home1;
    public SQLiteDatabase ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__view);

        res=findViewById(R.id.resview1);
        prev = findViewById(R.id.btnprev);
        next1 = findViewById(R.id.btnnext);

        home1=findViewById(R.id.pv_home);

        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), M_Menu.class);
                startActivity(intent);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                si -= 4;
                if (si < 0) {
                    si = 0;
                }
                Intent intent = new Intent(getApplicationContext(), Product_View.class);
                startActivity(intent);
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                si += 4;
                Intent intent = new Intent(getApplicationContext(), Product_View.class);
                startActivity(intent);
            }
        });

        new ProductService().execute();

//        Intent i = getIntent();

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this,"You Can't Use Back Button on this Screen",Toast.LENGTH_LONG).show();
    }

    public class ProductService extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... Args) {
            String output = null;
            try {
                String p_url = getString(R.string.product_url);
                URIBuilder builder = new URIBuilder(p_url);   //.net servlet url here
                builder.setParameter("si", si + "");
//                builder.setParameter("pw", pw.getText().toString());
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

                    if (s.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
                    } else if (s.equalsIgnoreCase("1")) {
//                        Toast.makeText(getApplicationContext(), "No Items", Toast.LENGTH_SHORT).show();
                        si = 0;
                        Intent intent = new Intent(getApplicationContext(), Product_View.class);
                        startActivity(intent);
                    } else {


                        JSONObject reader = new JSONObject(s);



                        l = new ArrayList<>();

                        for (int i1 = 1; i1 <= reader.length(); i1++) {
                            JSONObject r = (JSONObject) reader.get(i1 + "");

                            String img = r.get("img1").toString().replace('\\', '/');

                            l.add(new C_Products("" + r.get("pid"), "" + r.get("pname"), "" + r.get("qty"), "" + r.get("sp"), "" + r.get("cat"), "" + r.get("bname"), img,"" + r.get("des"),"" + r.get("min"), "" + r.get("st")));
                        }


                        Adapter_Res_View myadpter = new Adapter_Res_View(getApplicationContext(), l);
                        res.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                        res.setAdapter(myadpter);
                    }
                }
            } catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
