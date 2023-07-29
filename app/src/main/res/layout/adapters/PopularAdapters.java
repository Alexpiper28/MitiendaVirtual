package com.example.tiendatecno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiendatecno.R;
import com.example.tiendatecno.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int  position){
        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModelList.get(position).getName());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.descrition.setText(popularModelList.get(position).getDescripcion());
        holder.discount.setText(popularModelList.get(position).getDiscount());
    }
    @Override
    public int getItemCount(){
        return popularModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView popImg;
        TextView name,descrition,rating,discount;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            popImg=itemView.findViewById(R.id.pop_img);
            name=itemView.findViewById(R.id.pop_name);
            descrition=itemView.findViewById(R.id.pop_des);
            discount=itemView.findViewById(R.id.pop_discount);
            name=itemView.findViewById(R.id.pop_name);
            rating=itemView.findViewById(R.id.pop_rating);
        }
    }
}
