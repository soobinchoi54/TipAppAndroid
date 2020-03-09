package com.example.myapplication.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

import com.example.myapplication.model.Restaurant;
import com.example.myapplication.R;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {
    private List<Restaurant> restaurantData;
    private RowClickListener listener;
    private String poster_path;
    public RestaurantListAdapter(List<Restaurant> data, RowClickListener listener) {
        this.restaurantData = data;
        this.listener = listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivRestaurant;
        TextView tvName;
        TextView tvAddress;
        TextView tvCategories;
        TextView tvPrice;


        ViewHolder(View itemView) {
            super(itemView);
            ivRestaurant = itemView.findViewById(R.id.ivRestaurant);
            tvName = itemView.findViewById(R.id.restaurantName);
            tvAddress = itemView.findViewById(R.id.restaurantAddress);
            tvCategories = itemView.findViewById(R.id.restaurantCategories);
            tvPrice = itemView.findViewById(R.id.restaurantPrice);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            System.out.println("Clicked");
            listener.onClick(v, getAdapterPosition(), tvName.getText().toString(), tvAddress.getText().toString(), tvCategories.getText().toString(), tvPrice.getText().toString());

        }
    }


    @Override
    public RestaurantListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_row, parent, false);
        return new RestaurantListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.ViewHolder holder, int position) {
        Restaurant restaurant = restaurantData.get(position);

        //get current poster path
        poster_path = restaurant.getImageURL();
        //set image view to full poster path image using picasso
        Picasso.get()
                .load(poster_path)
                .resize(150, 150)
                .centerCrop()
                .into(holder.ivRestaurant);

        //Set all text views with movie data
        holder.tvName.setText(restaurant.getName());
        holder.tvAddress.setText(restaurant.getAddress().split(",")[0]);
        holder.tvCategories.setText(restaurant.getCategories());
        holder.tvPrice.setText(restaurant.getPrice());


    }

    //amount of restaurants to list
    @Override
    public int getItemCount() { return restaurantData.size(); }
}
