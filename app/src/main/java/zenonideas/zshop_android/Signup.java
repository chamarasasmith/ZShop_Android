package zenonideas.zshop_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    Button btn1;
    EditText fn,un,email1,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn1=findViewById(R.id.su_btn1);
        fn=findViewById(R.id.su_fn);
        un=findViewById(R.id.su_un);
        email1=findViewById(R.id.su_email);
        pw=findViewById(R.id.su_pw);
    }
}
