package zenonideas.zshop_android;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class M_Menu extends AppCompatActivity {

    DB_Helper db;
    CardView cv1,cv2,cv3,cv4;
    ImageButton sign_out,mmb_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m__menu);
        cv1=findViewById(R.id.cv1);
        cv2=findViewById(R.id.cv2);
        cv3=findViewById(R.id.cv3);
        cv4=findViewById(R.id.cv4);

        mmb_admin=findViewById(R.id.mmb_admin);



        sign_out=findViewById(R.id.m_sign_out);
        db=new DB_Helper(this);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i1 = db.signOut();
                if (i1==1){
                    Intent i=new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }
            }
        });

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Product_View.class);
                startActivity(i);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Cart_View.class);
                startActivity(i);
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),View_Orders.class);
                startActivity(i);
            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Search_Products.class);
                startActivity(i);
            }
        });

        mmb_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), Admin_Panel1.class);
                startActivity(i1);
            }
        });

        db=new DB_Helper(getApplicationContext());
        SQLiteDatabase db1 = db.getReadableDatabase();
        String sql = "SELECT * FROM login1";
        Cursor mCursor = db1.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            String rid = mCursor.getString(mCursor.getColumnIndex(DB_Helper.Column_RID));

            if (rid.equalsIgnoreCase("1")){
                mmb_admin.setVisibility(View.VISIBLE);
            }else{
                mmb_admin.setVisibility(View.INVISIBLE);
            }

        }
        mCursor.close();

    }
}
