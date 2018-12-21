package zenonideas.zshop_android;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

public class Adapter_Admin_Account extends RecyclerView.Adapter<Adapter_Admin_Account.MyViewHolder> {

    private Context mContext;
    private List<C_User> mData;


    public Adapter_Admin_Account(Context mContext, List<C_User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Adapter_Admin_Account.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater minflater=LayoutInflater.from(mContext);
        view=minflater.inflate(R.layout.admin_accounts_list,viewGroup,false);
        return new Adapter_Admin_Account.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_Admin_Account.MyViewHolder myViewHolder, final int i) {

        try {
            String w_url = mContext.getString(R.string.web_url);
            myViewHolder.txt1.setText(mData.get(i).getFn()+" "+mData.get(i).getLn());

            if (mData.get(i).getSt().equalsIgnoreCase("1")){
                myViewHolder.btn1.setText("Deactivate");
                myViewHolder.txt2.setText("Status : Active");
                myViewHolder.btn1.setBackgroundColor(Color.RED);
                myViewHolder.btn1.setTextColor(Color.WHITE);
            }else{
                myViewHolder.btn1.setText("Activate");
                myViewHolder.txt2.setText("Status : Deactive");
                myViewHolder.btn1.setBackgroundColor(Color.GREEN);
                myViewHolder.btn1.setTextColor(Color.BLACK);
            }

            Glide.with(mContext).load(w_url + mData.get(i).getImg1()).into(myViewHolder.img1);
            myViewHolder.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        if(myViewHolder.btn1.getText().toString().equalsIgnoreCase("Activate")){
                            HashMap<String,String> hm=new HashMap<>();
                            hm.put("tb","a");
                            hm.put("id1",mData.get(i).getLid());
                            hm.put("st","1");
                            String msg = ZShop_Web_Services.getConnection("Admin_Change_Status",hm).toString();
//                            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
                            if (msg.equalsIgnoreCase("s")){
                                myViewHolder.txt2.setText("Status : Active");
                                myViewHolder.btn1.setText("Deactivate");
                                myViewHolder.btn1.setBackgroundColor(Color.RED);
                                myViewHolder.btn1.setTextColor(Color.WHITE);
                                mData.get(i).setSt("1");
                            }
                        }else{

                            HashMap<String,String>hm=new HashMap<>();
                            hm.put("tb","a");
                            hm.put("id1",mData.get(i).getLid());
                            hm.put("st","0");
                            String msg = ZShop_Web_Services.getConnection("Admin_Change_Status",hm).toString();
//                            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
                            if (msg.equalsIgnoreCase("s")){
                                myViewHolder.txt2.setText("Status : Deactive");
                                myViewHolder.btn1.setText("Activate");
                                myViewHolder.btn1.setBackgroundColor(Color.GREEN);
                                myViewHolder.btn1.setTextColor(Color.BLACK);
                                mData.get(i).setSt("0");
                            }

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            System.out.println(e);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt1,txt2;
        ImageView img1;
        Button btn1,btn2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.apl_img2);
            txt1=itemView.findViewById(R.id.apl_un2);
            txt2=itemView.findViewById(R.id.apl_st2);
            btn1=itemView.findViewById(R.id.apl_ac2);

        }
    }
}
