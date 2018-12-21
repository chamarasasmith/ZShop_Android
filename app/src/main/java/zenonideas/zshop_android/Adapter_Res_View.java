package zenonideas.zshop_android;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class Adapter_Res_View extends RecyclerView.Adapter<Adapter_Res_View.MyViewHolder> {

    private Context mContext;
    private List<C_Products> mData;


    public Adapter_Res_View(Context mContext, List<C_Products> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater minflater=LayoutInflater.from(mContext);
        view=minflater.inflate(R.layout.cardview_products,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        String w_url = mContext.getString(R.string.web_url);
        myViewHolder.txt1.setText(mData.get(i).getPname());
        Glide.with(mContext).load(w_url + mData.get(i).getImg()).into(myViewHolder.img1);
        myViewHolder.cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Product_Details.class);
                intent.putExtra("pid", mData.get(i).getPid());
                intent.putExtra("pname", mData.get(i).getPname());
                intent.putExtra("cat", mData.get(i).getCat());
                intent.putExtra("brand", mData.get(i).getBname());
                intent.putExtra("des", mData.get(i).getDes());
                intent.putExtra("img", mData.get(i).getImg());
                intent.putExtra("sp", mData.get(i).getSp());
                intent.putExtra("qty", mData.get(i).getQty());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt1;
        ImageView img1;
        CardView cv1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.img_img1);
            txt1=itemView.findViewById(R.id.txt_pname1);
            cv1 = itemView.findViewById(R.id.cv1);

        }
    }

}
