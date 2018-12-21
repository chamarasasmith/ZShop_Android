package zenonideas.zshop_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search_Products extends AppCompatActivity {

    Spinner sp1,sp2;
    EditText t1;
    Button next1,prev,btn1;
    RecyclerView res;
    ImageButton home1;
    static int si = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__products);

        btn1=findViewById(R.id.sp_search);
        t1=findViewById(R.id.sp_t1);
        res=findViewById(R.id.sp_resview1);
        prev = findViewById(R.id.sp_btnprev);
        next1 = findViewById(R.id.sp_btnnext);


        home1=findViewById(R.id.sp_pv_home);

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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    HashMap hm=new HashMap();
                    hm.put("s1",si);
                    hm.put("t1",t1.getText());

                    String res1 = ZShop_Web_Services.getConnection("Load_Adv_Products", hm).toString();

                    JSONObject reader = new JSONObject(res1);




                    List<C_Products> l = new ArrayList<>();

                    for (int i1 = 1; i1 <= reader.length(); i1++) {
                        JSONObject r = (JSONObject) reader.get(i1 + "");

                        String img = r.get("img1").toString().replace('\\', '/');

                        l.add(new C_Products("" + r.get("pid"), "" + r.get("pname"), "" + r.get("qty"), "" + r.get("sp"), "" + r.get("cat"), "" + r.get("bname"), img,"" + r.get("des"),"" + r.get("min"), "" + r.get("st")));
                    }


                    Adapter_Res_View myadpter = new Adapter_Res_View(getApplicationContext(), l);
                    res.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    res.setAdapter(myadpter);

                }catch (Exception e){
                    System.out.println(e);
                }


            }
        });



    }
}
