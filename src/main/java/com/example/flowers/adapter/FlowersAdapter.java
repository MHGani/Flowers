package com.example.flowers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowers.R;
import com.example.flowers.model.FlowersModel;
import com.example.flowers.network.Constants;

import java.net.URL;
import java.util.List;

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.ViewHolder> {
    List<FlowersModel> flowersModelList;
    int row_flowers;
    Context applicationContext;
    private Object IMAGE_URL;

    public FlowersAdapter(List<FlowersModel> flowersModelList, int row_flowers, Context applicationContext){
        this.flowersModelList = flowersModelList;
        this.row_flowers = row_flowers;
        this.applicationContext = applicationContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( row_flowers,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FlowersModel flowersModel = flowersModelList.get(position);
        holder.flowerName.setText( flowersModel.getName() );
        holder.flowerPrice.setText( flowersModel.getPrice() );

        String ImageUrl = Constants.IMAGE_URL + flowersModel.getImage();


       Glide.with(applicationContext)
              .load( ImageUrl )
               .placeholder(R.drawable.ic_launcher_background )
               .into( holder.flowerImg );

    }

    @Override
    public int getItemCount() {
        return flowersModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView flowerName;
        TextView flowerPrice;
        ImageView flowerImg;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            flowerName = itemView.findViewById( R.id.tvFlowerName );
            flowerPrice = itemView.findViewById( R.id.tvPrice );
            flowerImg = itemView.findViewById( R.id.imgFlower );
        }
    }
}
