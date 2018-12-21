package zenonideas.zshop_android;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class Product_Details extends AppCompatActivity {
    TextView t1, t2, t3, vbrand, vdes,pv_sp,pv_aqty;
    ImageView img1;
    String w_url;
    Button pd_add_cart;
    DB_Helper db;
    EditText pd_qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);

        w_url = getString(R.string.web_url);

        t1 = findViewById(R.id.pv_title);
        t2 = findViewById(R.id.pv_cat);
        vbrand = findViewById(R.id.pv_brand);
        vdes = findViewById(R.id.pv_des);
        img1 = findViewById(R.id.pv_thum);
        pd_add_cart = findViewById(R.id.pd_add_cart);
        pd_qty = findViewById(R.id.pd_qty);
        pv_sp = findViewById(R.id.pv_sp);
        pv_aqty=findViewById(R.id.pv_aqty);

        pd_qty.setText("1");

        Intent intent = getIntent();
        final String pid = intent.getExtras().getString("pid");
        String pname = intent.getExtras().getString("pname");
        String cat = intent.getExtras().getString("cat");
        String brand = intent.getExtras().getString("brand");
        String img = intent.getExtras().getString("img");
        String des = intent.getExtras().getString("des");
        String sp = intent.getExtras().getString("sp");
        String qty1 = intent.getExtras().getString("qty");

        Glide.with(this).load(w_url + img).into(img1);
        t1.setText(pname);
        t2.setText("Category : " + cat);
        vbrand.setText("Brand : " + brand);
        vdes.setText(des);
        pv_sp.setText("Price : "+sp);
        pv_aqty.setText("Available qty : "+qty1);

        pd_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String uid = "";
                    db = new DB_Helper(getApplicationContext());
                    SQLiteDatabase db1 = db.getReadableDatabase();
                    String sql = "SELECT * FROM login1";
                    Cursor mCursor = db1.rawQuery(sql, null);
                    if (mCursor.getCount() > 0) {
                        mCursor.moveToFirst();
                        uid = mCursor.getString(mCursor.getColumnIndex(DB_Helper.Column_UID));
                    }
                    mCursor.close();


                    HashMap hm = new HashMap();
                    hm.put("pid", pid);
                    hm.put("uid", uid);
                    hm.put("qty1", pd_qty.getText().toString());

                    String res = ZShop_Web_Services.getConnection("Add_to_Cart", hm).toString();

                       if (res.equalsIgnoreCase("e")) {
                            Toast.makeText(getApplicationContext(), "Error..!", Toast.LENGTH_LONG).show();
                        } else if (res.equalsIgnoreCase("sw")) {
                            Toast.makeText(getApplicationContext(), "Something Wrong..!", Toast.LENGTH_LONG).show();
                        } else if (res.equalsIgnoreCase("s")) {
                            Toast.makeText(getApplicationContext(), "Success..!", Toast.LENGTH_LONG).show();
                        }else{
                           Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                       }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

    }
}
