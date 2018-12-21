package zenonideas.zshop_android;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class Order_View_S2 extends AppCompatActivity {

    ListView list1;
    String iid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__view__s2);

        list1=findViewById(R.id.vo2_list1);
        Intent intent = getIntent();
         iid = intent.getExtras().getString("iid");
//        Toast.makeText(getApplicationContext(),iid,Toast.LENGTH_LONG).show();
        LoadOrder1(iid);
    }

    private void LoadOrder1(String iid) {
        try {
            List<String> l1 = new ArrayList<>();

            String msg = ZShop_Web_Services.getConnection("Mobile_Load_Order_Details", iid).toString();

//            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

            JSONObject reader = new JSONObject(msg);

            for (int i1 = 1; i1 <= reader.length(); i1++) {

                l1.add(reader.get(i1 + "")+"");
            }

            ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,l1);
            list1.setAdapter(adapter);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
