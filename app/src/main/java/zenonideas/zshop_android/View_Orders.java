package zenonideas.zshop_android;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class View_Orders extends AppCompatActivity {
    DB_Helper db;
    ListView list1;
    EditText edt1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__orders);

        btn1=findViewById(R.id.vo_btn1);
        edt1=findViewById(R.id.vo_edit1);
        list1=findViewById(R.id.vo_list1);

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ii1=new Intent(getApplicationContext(),Order_View_S2.class);
                ii1.putExtra("iid",list1.getAdapter().getItem(i).toString().split(" - ")[0]);
                startActivity(ii1);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadOrder1(edt1.getText().toString());
            }
        });

        LoadOrder1(edt1.getText().toString());

    }

    private void LoadOrder1(String t1) {
        try {
            List<String> l1 = new ArrayList<>();

            HashMap hm=new HashMap();
            String uid="0";
            db=new DB_Helper(getApplicationContext());
            SQLiteDatabase db1 = db.getReadableDatabase();
            String sql = "SELECT * FROM login1";
            Cursor mCursor = db1.rawQuery(sql, null);
            if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                uid = mCursor.getString(mCursor.getColumnIndex(DB_Helper.Column_UID));
                hm.put("uid",uid);
            }else{
                hm.put("uid",null);
            }

            hm.put("t1",t1);
            mCursor.close();

            String msg = ZShop_Web_Services.getConnection("Mobile_Load_Order", hm).toString();

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
