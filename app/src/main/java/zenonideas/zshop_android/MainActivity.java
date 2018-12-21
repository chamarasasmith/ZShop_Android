package zenonideas.zshop_android;

import android.app.UiAutomation;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    DB_Helper db;
    Button btn1, btn2, btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn1=findViewById(R.id.btn1);
//        btn2=findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
//        btn4 = findViewById(R.id.btn4);
//        btn5 = findViewById(R.id.btn5);

        db = new DB_Helper(this);





        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Login.class);
                startActivity(i1);
            }
        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1=new Intent(getApplicationContext(),Signup.class);
//                startActivity(i1);
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1 = new Intent(getApplicationContext(), Cart_View.class);
//                startActivity(i1);
//            }
//        });
//
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1 = new Intent(getApplicationContext(), Admin_Panel1.class);
//                startActivity(i1);
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(MainActivity.this,"Clicked",Toast.LENGTH_LONG).show();
//                HashMap hm = new HashMap();
//                hm.put("status","Login");
//                hm.put("un","cs");
//                hm.put("pw","123");
//
//                try {
//                    Object msg = ZShop_Web_Services.getConnection("Login_Test",hm);
//
//                    HashMap<String,String> h=(HashMap<String, String>)msg;
//
//                    Toast.makeText(MainActivity.this,h.get("a"),Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this,"Something Wrong",Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//                }
//            }
//        });

        setScreen();
    }

    private boolean checkTableExists(String table) {
        SQLiteDatabase db1 = db.getReadableDatabase();
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + table + "'";
        Cursor mCursor = db1.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }


    public void setScreen(){
        boolean b = checkTableExists("login1");

        if (b) {
            Cursor rs = db.getDataCount();

            int count = rs.getCount();

            if (count <= 0) {

            } else {

                Intent intent = new Intent(this, M_Menu.class);
                startActivity(intent);

//                rs.moveToFirst();
//                String un = rs.getString(rs.getColumnIndex(DB_Helper.Column_UN));
//                Toast.makeText(this,un,Toast.LENGTH_LONG).show();
            }


        } else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

}
