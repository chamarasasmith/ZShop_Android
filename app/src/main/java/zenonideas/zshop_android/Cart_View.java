package zenonideas.zshop_android;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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

public class Cart_View extends AppCompatActivity {
    DB_Helper db;
    RecyclerView res;
    List<C_Products> l;
    String uid="0";
    Button cv_checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart__view);

        res = findViewById(R.id.resview2);

        cv_checkout=findViewById(R.id.cv_checkout);

        cv_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Checkout_View.class);
                startActivity(i1);
            }
        });

        db=new DB_Helper(getApplicationContext());
        SQLiteDatabase db1 = db.getReadableDatabase();
        String sql = "SELECT * FROM login1";
        Cursor mCursor = db1.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
                uid = mCursor.getString(mCursor.getColumnIndex(DB_Helper.Column_UID));
        }
        mCursor.close();

        new CartService().execute();
    }


    public class CartService extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... Args) {
            String output = null;
            try {
                String p_url = getString(R.string.cart_url);
                URIBuilder builder = new URIBuilder(p_url);   //.net servlet url here
                builder.setParameter("si", uid);
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
        protected void onPostExecute(String s) {
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            try {
                if (s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Check Network Your Connection", Toast.LENGTH_SHORT).show();
                } else {

                    if (s.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
                    } else if (s.equalsIgnoreCase("1")) {
                        Toast.makeText(getApplicationContext(), "No Items", Toast.LENGTH_SHORT).show();
                    } else {


                        JSONObject reader = new JSONObject(s);

                        l = new ArrayList<>();

                        for (int i1 = 1; i1 <= reader.length(); i1++) {
                            JSONObject r = (JSONObject) reader.get(i1 + "");

                            String img = r.get("img1").toString().replace('\\', '/');

                            l.add(new C_Products("" + r.get("pid"), "" + r.get("pname"), "" + r.get("qty"), "" + r.get("sp"), "" + r.get("cat"), "" + r.get("bname"), img,"" + r.get("des"),"" + r.get("min"),"" + r.get("st")));
                        }


                        Adapter_Cart_View myadpter = new Adapter_Cart_View(getApplicationContext(), l);
                        res.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                        res.setAdapter(myadpter);
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
