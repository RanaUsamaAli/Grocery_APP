package com.example.mainactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<User_fetch, myadapter.myviewholder> {
    public myadapter(@NonNull FirebaseRecyclerOptions<User_fetch> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull User_fetch model)
    {
        holder.price.setText(model.getPrice());
        holder.product_Des.setText(model.getProduct_Des());
        holder.product_ID.setText(model.getProduct_ID());
        holder.product_name.setText(model.getProduct_Name());
        Glide.with(holder.img.getContext()).load(model.getImage_URL()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_data,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView price;
        TextView product_Des;
        TextView product_ID;
        TextView product_name;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.R_Image_View);
            price=(TextView)itemView.findViewById(R.id.U_Product_Price);
            product_Des=(TextView)itemView.findViewById(R.id.U_Description);
            product_ID=(TextView)itemView.findViewById(R.id.U_Product_ID);
            product_name=(TextView)itemView.findViewById(R.id.title_View);
        }
    }

}
