package zenonideas.zshop_android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.HttpGet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class Adapter_Cart_View extends RecyclerView.Adapter<Adapter_Cart_View.MyViewHolder> {

    static String id1, qty1, type1;
    private Context mContext;
    private List<C_Products> mData;


    public Adapter_Cart_View(Context mContext, List<C_Products> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Adapter_Cart_View.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater minflater = LayoutInflater.from(mContext);
        view = minflater.inflate(R.layout.listview_cart, viewGroup, false);
        return new Adapter_Cart_View.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_Cart_View.MyViewHolder myViewHolder, final int i) {

        String w_url = mContext.getString(R.string.web_url);
        myViewHolder.txt1.setText(mData.get(i).getPname());
        myViewHolder.vqty.setText("Qty : " + mData.get(i).getQty());
        myViewHolder.qty1.setText("1");
        Glide.with(mContext).load(w_url + mData.get(i).getImg()).into(myViewHolder.img1);

        myViewHolder.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String uid = "";
                    DB_Helper db = new DB_Helper(mContext);
                    SQLiteDatabase db1 = db.getReadableDatabase();
                    String sql = "SELECT * FROM login1";
                    Cursor mCursor = db1.rawQuery(sql, null);
                    if (mCursor.getCount() > 0) {
                        mCursor.moveToFirst();
                        uid = mCursor.getString(mCursor.getColumnIndex(DB_Helper.Column_UID));
                    }
                    mCursor.close();


                    HashMap hm = new HashMap();
                    hm.put("pid", mData.get(i).getPid());
                    hm.put("uid", uid);
                    hm.put("qty1", myViewHolder.qty1.getText()+"");

                    String res = ZShop_Web_Services.getConnection("Add_to_Cart", hm).toString();

                    if (res.equalsIgnoreCase("e")) {
                        Toast.makeText(mContext, "Error..!", Toast.LENGTH_LONG).show();
                    } else if (res.equalsIgnoreCase("sw")) {
                        Toast.makeText(mContext, "Something Wrong..!", Toast.LENGTH_LONG).show();
                    } else if (res.equalsIgnoreCase("s")) {
                        Toast.makeText(mContext, "Success..!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }



            }
        });

        myViewHolder.rem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String uid = "";
                    DB_Helper db = new DB_Helper(mContext);
                    SQLiteDatabase db1 = db.getReadableDatabase();
                    String sql = "SELECT * FROM login1";
                    Cursor mCursor = db1.rawQuery(sql, null);
                    if (mCursor.getCount() > 0) {
                        mCursor.moveToFirst();
                        uid = mCursor.getString(mCursor.getColumnIndex(DB_Helper.Column_UID));
                    }
                    mCursor.close();


                    HashMap hm = new HashMap();
                    hm.put("pid", mData.get(i).getPid());
                    hm.put("uid", uid);

                    String res = ZShop_Web_Services.getConnection("Mobile_Remove_Cart_Item", hm).toString();

                    if (res.equalsIgnoreCase("e")) {
                        Toast.makeText(mContext, "Error..!", Toast.LENGTH_LONG).show();
                    } else if (res.equalsIgnoreCase("sw")) {
                        Toast.makeText(mContext, "Something Wrong..!", Toast.LENGTH_LONG).show();
                    } else if (res.equalsIgnoreCase("s")) {
                        Toast.makeText(mContext, "Success..!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt1, vqty;
        ImageView img1;
        EditText qty1;
        Button add1, rem1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.cv_img1);
            txt1 = itemView.findViewById(R.id.cv_pname1);
            qty1 = itemView.findViewById(R.id.cv_qty);
            vqty = itemView.findViewById(R.id.cv_vqty);

            add1 = itemView.findViewById(R.id.cv_add);
            rem1 = itemView.findViewById(R.id.cv_rem);

        }
    }



}
