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

public class DrinkAdapter  extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {


    List<DrinkItem> drinkItemList;

    public DrinkAdapter(List<DrinkItem> drinkItemList) {
        this.drinkItemList = drinkItemList;
    }

    @NonNull
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, int position) {
        DrinkItem foodItem = drinkItemList.get(position);
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
        return drinkItemList.size();
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {

        private TextView foodName;
        private TextView foodDescription;
        private ImageView foodImage;
        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodDescription = itemView.findViewById(R.id.food_description);
            foodImage = itemView.findViewById(R.id.food_image);
        }
    }
}
