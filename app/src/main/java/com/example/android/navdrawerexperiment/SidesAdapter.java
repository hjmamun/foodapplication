package com.example.android.navdrawerexperiment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SidesAdapter extends RecyclerView.Adapter<SidesAdapter.SidesViewHolder> {


    List<SideItem> sideItemList;

    public SidesAdapter(List<SideItem> sideItemList) {
        this.sideItemList = sideItemList;
    }

    @NonNull
    @Override
    public SidesAdapter.SidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new SidesAdapter.SidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SidesAdapter.SidesViewHolder holder, int position) {
        SideItem foodItem = sideItemList.get(position);
        holder.foodName.setText(foodItem.getName());
        holder.foodDescription.setText(foodItem.getDescription());
        // Load image using Glide or Picasso library
        Glide.with(holder.foodImage.getContext()).load(foodItem.getImageUrl()).into(holder.foodImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Add To Cart"+foodItem.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                DataHelper.addToCart(foodItem.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sideItemList.size();
    }

    public class SidesViewHolder extends RecyclerView.ViewHolder {


        private TextView foodName;
        private TextView foodDescription;
        private ImageView foodImage;
        public SidesViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodDescription = itemView.findViewById(R.id.food_description);
            foodImage = itemView.findViewById(R.id.food_image);
        }
    }
}
